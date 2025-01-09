package ac.at.tgm.hkrall;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class WordTrainerGUI {

    private static final String FILE_NAME = "wordTrainerDataGUI.txt";
    private static final int MAX_WIDTH = 820;
    private static final int MAX_HEIGHT = 400;

    public static void main(String[] args) {
        WordTrainer wordTrainer;

        // Daten laden oder neue Liste erstellen
        try {
            wordTrainer = SaveLoad.laden(FILE_NAME);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Keine gespeicherten Daten gefunden. Erstelle neuen Trainer.", "Info", JOptionPane.INFORMATION_MESSAGE);
            WordList defaultList = new WordList();
            defaultList.addWord(new WordEntry("Hund", "https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3"));
            defaultList.addWord(new WordEntry("Katze", "https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg"));
            defaultList.addWord(new WordEntry("Vogel", "https://image.geo.de/30140936/t/bF/v3/w1440/r1/-/schuhschnabel-vogel-geolino2-jpg--80202-.jpg"));
            wordTrainer = new WordTrainer(defaultList);
        }

        boolean isRunning = true;
        boolean lastAttemptCorrect = false;

        while (isRunning) {
            WordEntry currentWord = wordTrainer.getRandomWord();

            // Anzeige der Statistik und des Bildes
            StringBuilder message = new StringBuilder();
            message.append("<html>Statistik:<br>");
            message.append("Richtige Versuche: ").append(wordTrainer.getCorrectTrys()).append("<br>");
            message.append("Gesamtversuche: ").append(wordTrainer.getAllTrys()).append("<br><br>");


            //TODO
            if (wordTrainer.getAllTrys() > 0) {
                message.append("Letzter Versuch: ").append(lastAttemptCorrect ? "Richtig!" : "Falsch!").append("<br><br>");
            }

            message.append("Was ist das fuer ein Tier?</html>");

            // Bild laden und skalieren
            ImageIcon scaledImageIcon = null;
            try {
                URL imageUrl = new URL(currentWord.getUrl());
                Image image = scaleImageToMaxSize(new ImageIcon(imageUrl).getImage(), MAX_WIDTH, MAX_HEIGHT);
                scaledImageIcon = new ImageIcon(image);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Fehler beim Laden des Bildes: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }

            // Anzeige der Eingabeaufforderung mit Bild
            JPanel panel = new JPanel(new BorderLayout());
            JLabel imageLabel = new JLabel(scaledImageIcon);
            JLabel textLabel = new JLabel(message.toString());
            JTextField inputField = new JTextField();

            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(textLabel, BorderLayout.NORTH);
            panel.add(inputField, BorderLayout.SOUTH);


            //TODO
            int result = JOptionPane.showConfirmDialog(null, panel, "WortTrainer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Eingabe prüfen
            if (result == JOptionPane.CANCEL_OPTION || inputField.getText().trim().isEmpty()) {
                isRunning = false; // Abbruch
            } else {
                String input = inputField.getText().trim();
                boolean isCorrect = wordTrainer.checkWord(input);
                lastAttemptCorrect = isCorrect;
                JOptionPane.showMessageDialog(null, isCorrect ? "Richtig!" : "Falsch!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // Daten speichern
        try {
            SaveLoad.save(wordTrainer, FILE_NAME);
            JOptionPane.showMessageDialog(null, "Daten wurden erfolgreich gespeichert.", "Speichern", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Daten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }


        // Zeige den aktuellen Zustand an, bevor das Programm beendet wird
        JOptionPane.showMessageDialog(null, getWordTrainerSummary(wordTrainer), "Aktueller Zustand", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Skaliert ein Bild auf die maximale Breite und Höhe.
     */
    private static Image scaleImageToMaxSize(Image image, int maxWidth, int maxHeight) {
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        if (originalWidth <= maxWidth && originalHeight <= maxHeight) {
            return image; // Keine Skalierung notwendig
        }

        double widthScale = (double) maxWidth / originalWidth;
        double heightScale = (double) maxHeight / originalHeight;
        double scale = Math.min(widthScale, heightScale);

        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    private static String getWordTrainerSummary(WordTrainer wordTrainer) {
        StringBuilder summary = new StringBuilder();
        summary.append("Aktueller Zustand des WortTrainers:\n\n");

        summary.append("Wörter und URLs:\n");
        for (WordEntry entry : wordTrainer.getWordlist().getWordEntries()) {
            summary.append("Wort: ").append(entry.getWord())
                    .append(" | URL: ").append(entry.getUrl()).append("\n");
        }

        summary.append("\nStatistik:\n");
        summary.append("Richtige Versuche: ").append(wordTrainer.getCorrectTrys()).append("\n");
        summary.append("Gesamtversuche: ").append(wordTrainer.getAllTrys()).append("\n");

        return summary.toString();
    }


}


