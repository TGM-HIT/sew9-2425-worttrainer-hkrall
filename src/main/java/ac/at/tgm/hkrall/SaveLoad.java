package ac.at.tgm.hkrall;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

}
