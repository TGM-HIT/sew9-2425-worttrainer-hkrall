package ac.at.tgm.hkrall;

import java.io.*;

/**
 * Die Klasse SaveLoad stellt Methoden zum Speichern und Laden von Daten eines WordTrainer-Objekts bereit.
 * Sie ermöglicht es, eine WordList sowie Statistikdaten wie die Anzahl der richtigen und Gesamtversuche in einer Datei zu speichern und wiederherzustellen.
 * @author Helena Krall
 * @version 09.01.2025
 */
public class SaveLoad {

    /**
     * Speichert die Daten eines WordTrainer-Objekts in einer Datei.
     * Die Daten umfassen die Wort-Einträge und die Statistiken (richtige und Gesamtversuche).
     *
     * @param wordTrainer Das WordTrainer-Objekt, dessen Daten gespeichert werden sollen.
     * @param fileName Der Name der Datei, in der die Daten gespeichert werden.
     * @throws IOException Wenn ein Fehler beim Schreiben der Datei auftritt.
     */
    public static void save(WordTrainer wordTrainer, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Speichern der Wort-Einträge
            for (WordEntry eintrag : wordTrainer.getWordlist().getWordEntries()) {
                writer.write(eintrag.getWord() + "," + eintrag.getUrl());
                writer.newLine();
            }
            // Speichern der Statistik
            writer.write("RichtigeVersuche:" + 0); // vorübergehend 0, stattdessen wordTrainer.getCorrectTrys() verwenden
            writer.newLine();
            writer.write("GesamtVersuche:" + 0);  // vorübergehend 0, stattdessen wordTrainer.getAllTrys() verwenden
            writer.newLine();
        }
    }

    /**
     * Lädt die Daten eines WordTrainer-Objekts aus einer Datei.
     * Die Datei sollte Wort-Einträge und Statistikdaten wie die Anzahl der richtigen und Gesamtversuche enthalten.
     *
     * @param fileName Der Name der Datei, aus der die Daten geladen werden.
     * @return Ein neues WordTrainer-Objekt, das mit den geladenen Daten initialisiert wird.
     * @throws IOException Wenn ein Fehler beim Lesen der Datei auftritt.
     */
    public static WordTrainer laden(String fileName) throws IOException {
        WordList wortListe = new WordList();
        int richtigeVersuche = 0;
        int gesamtVersuche = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("RichtigeVersuche:")) {
                    richtigeVersuche = Integer.parseInt(line.substring("RichtigeVersuche:".length()));
                } else if (line.startsWith("GesamtVersuche:")) {
                    gesamtVersuche = Integer.parseInt(line.substring("GesamtVersuche:".length()));
                } else {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String wort = parts[0];
                        String url = parts[1];
                        wortListe.addWord(new WordEntry(wort, url));
                    }
                }
            }
        }

        WordTrainer wortTrainer = new WordTrainer(wortListe);
        wortTrainer.setCorrectTrys(richtigeVersuche);
        wortTrainer.setAllTrys(gesamtVersuche);
        return wortTrainer;
    }
}
