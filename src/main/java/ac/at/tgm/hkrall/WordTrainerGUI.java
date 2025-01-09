package ac.at.tgm.hkrall;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Die Klasse WordTrainerGUI stellt die Benutzeroberfläche für den Worttrainer bereit.
 * Sie ermöglicht es, ein zufälliges Wort anzuzeigen und den Benutzer zu fragen, welches Tier es darstellt.
 * Nach jeder Eingabe wird das Ergebnis (richtig oder falsch) angezeigt und die Statistiken werden aktualisiert.
 * Die Daten des Worttrainers werden in einer Datei gespeichert und beim Start des Programms geladen.
 *
 * @author Helena Krall
 * @version 09.01.2025
 */
public class WordTrainerGUI {

    private static final String FILE_NAME = "wordTrainerDataGUI.txt";  // Dateiname zum Speichern der Daten
    private static final int MAX_WIDTH = 820;  // Maximale Breite für die Bildskalierung
    private static final int MAX_HEIGHT = 400;  // Maximale Höhe für die Bildskalierung

    public static void main(String[] args) {
        WordTrainer wordTrainer;

        // Daten laden oder neue Liste erstellen, wenn keine gespeicherten Daten vorhanden sind
        try {
            wordTrainer = SaveLoad.laden(FILE_NAME);  // Versucht, die gespeicherten Daten zu laden
        } catch (IOException e) {
            // Wenn keine Daten gefunden wurden, wird ein neuer WordTrainer mit Standard-Wörtern erstellt
            JOptionPane.showMessageDialog(null, "Keine gespeicherten Daten gefunden. Erstelle neuen Trainer.", "Info", JOptionPane.INFORMATION_MESSAGE);
            WordList defaultList = new WordList();
            // Standard-Wörter werden zur Liste hinzugefügt
            defaultList.addWord(new WordEntry("Hund", "https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3"));
            defaultList.addWord(new WordEntry("Katze", "https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg"));
            defaultList.addWord(new WordEntry("Vogel", "https://image.geo.de/30140936/t/bF/v3/w1440/r1/-/schuhschnabel-vogel-geolino2-jpg--80202-.jpg"));
            wordTrainer = new WordTrainer(defaultList);  // Erstellt einen neuen WordTrainer mit der Standard-Wortliste
        }

        boolean isRunning = true;
        boolean lastAttemptCorrect = false;  // Speichert das Ergebnis des letzten Versuchs

        // Haupt-Loop für die Interaktion mit dem Benutzer
        while (isRunning) {
            WordEntry currentWord = wordTrainer.getRandomWord();  // Wählt ein zufälliges Wort aus

            // Erstellung der Nachricht, die statistische Informationen enthält
            StringBuilder message = new StringBuilder();
            message.append("<html>Statistik:<br>");
            message.append("Richtige Versuche: ").append(wordTrainer.getCorrectTrys()).append("<br>");
            message.append("Gesamtversuche: ").append(wordTrainer.getAllTrys()).append("<br><br>");

            // Zeigt das Ergebnis des letzten Versuchs an, falls vorhanden
            if (wordTrainer.getAllTrys() > 0) {
                message.append("Letzter Versuch: ").append(lastAttemptCorrect ? "Richtig!" : "Falsch!").append("<br><br>");
            }

            message.append("Was ist das fuer ein Tier?</html>");

            // Bild laden und skalieren
            ImageIcon scaledImageIcon = null;
            try {
                URL imageUrl = new URL(currentWord.getUrl());
                Image image = scaleImageToMaxSize(new ImageIcon(imageUrl).getImage(), MAX_WIDTH, MAX_HEIGHT);
                scaledImageIcon = new ImageIcon(image);  // Skalierung des Bildes auf die maximalen Maße
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Fehler beim Laden des Bildes: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }

            // Erstellung des Panels mit Bild, Text und Eingabefeld
            JPanel panel = new JPanel(new BorderLayout());
            JLabel imageLabel = new JLabel(scaledImageIcon);  // Label für das Bild
            JLabel textLabel = new JLabel(message.toString());  // Label für die Nachricht
            JTextField inputField = new JTextField();  // Eingabefeld für die Antwort

            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(textLabel, BorderLayout.NORTH);
            panel.add(inputField, BorderLayout.SOUTH);

            // Anzeige des Panels in einem Dialog mit OK/CANCEL-Option
            int result = JOptionPane.showConfirmDialog(null, panel, "WortTrainer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Überprüfen der Benutzereingabe und Aktualisieren der Statistik
            if (result == JOptionPane.CANCEL_OPTION || inputField.getText().trim().isEmpty()) {
                isRunning = false;  // Wenn der Benutzer abbricht oder keine Eingabe macht, wird das Programm beendet
            } else {
                String input = inputField.getText().trim();
                boolean isCorrect = wordTrainer.checkWord(input);  // Überprüft, ob die Eingabe korrekt ist
                lastAttemptCorrect = isCorrect;  // Speichert das Ergebnis des aktuellen Versuchs
                JOptionPane.showMessageDialog(null, isCorrect ? "Richtig!" : "Falsch!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // Speichern der Daten nach Beenden des Programms
        try {
            SaveLoad.save(wordTrainer, FILE_NAME);
            JOptionPane.showMessageDialog(null, "Daten wurden erfolgreich gespeichert.", "Speichern", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Daten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        // Anzeige des aktuellen Zustands des WordTrainers, bevor das Programm beendet wird
        JOptionPane.showMessageDialog(null, getWordTrainerSummary(wordTrainer), "Aktueller Zustand", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Skaliert ein Bild auf die maximal erlaubte Breite und Höhe.
     * Die Skalierung erfolgt proportional, sodass das Bild nicht verzerrt wird.
     *
     * @param image Das Bild, das skaliert werden soll.
     * @param maxWidth Die maximale Breite des Bildes.
     * @param maxHeight Die maximale Höhe des Bildes.
     * @return Das skalierte Bild.
     */
    private static Image scaleImageToMaxSize(Image image, int maxWidth, int maxHeight) {
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        // Keine Skalierung nötig, wenn das Bild bereits in der richtigen Größe ist
        if (originalWidth <= maxWidth && originalHeight <= maxHeight) {
            return image;
        }

        // Berechnung des Skalierungsfaktors für die Breite und Höhe
        double widthScale = (double) maxWidth / originalWidth;
        double heightScale = (double) maxHeight / originalHeight;
        double scale = Math.min(widthScale, heightScale);  // Verwendet den kleineren Skalierungsfaktor, um das Bild proportional zu skalieren

        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);  // Gibt das skalierte Bild zurück
    }

    /**
     * Gibt eine Zusammenfassung des aktuellen Zustands des WordTrainers zurück.
     * Diese umfasst alle Wörter, URLs sowie die statistischen Daten.
     *
     * @param wordTrainer Das WordTrainer-Objekt, dessen Zustand zusammengefasst werden soll.
     * @return Eine String-Zusammenfassung des aktuellen Zustands des WordTrainers.
     */
    private static String getWordTrainerSummary(WordTrainer wordTrainer) {
        StringBuilder summary = new StringBuilder();
        summary.append("Aktueller Zustand des WortTrainers:\n\n");

        // Auflistung der Wörter und URLs
        summary.append("Wörter und URLs:\n");
        for (WordEntry entry : wordTrainer.getWordlist().getWordEntries()) {
            summary.append("Wort: ").append(entry.getWord())
                    .append(" | URL: ").append(entry.getUrl()).append("\n");
        }

        // Anzeige der statistischen Daten
        summary.append("\nStatistik:\n");
        summary.append("Richtige Versuche: ").append(wordTrainer.getCorrectTrys()).append("\n");
        summary.append("Gesamtversuche: ").append(wordTrainer.getAllTrys()).append("\n");

        return summary.toString();
    }
}
