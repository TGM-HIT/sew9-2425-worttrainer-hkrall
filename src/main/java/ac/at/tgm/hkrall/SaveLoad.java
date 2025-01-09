package ac.at.tgm.hkrall;


import java.io.*;

public class SaveLoad {


    public static void save(WordTrainer wordTrainer, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Speichern der Wort-Einträge
            for (WordEntry eintrag : wordTrainer.getWordlist().getWordEntries()) {
                writer.write(eintrag.getWord() + "," + eintrag.getUrl());
                writer.newLine();
            }
            // Speichern der Statistik
            writer.write("RichtigeVersuche:" + wordTrainer.getCorrectTrys());
            writer.newLine();
            writer.write("GesamtVersuche:" + wordTrainer.getAllTrys());
            writer.newLine();
        }
    }

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
