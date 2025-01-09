package ac.at.tgm.hkrall;

import java.io.IOException;

public class MainTest {

    public static void main(String[] args) {

        // Test WordEntry
        System.out.println("WordEntry Test:");
        WordEntry x = new WordEntry("Hund", "https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3");
        System.out.println(x.toString());

        WordEntry x2 = new WordEntry("Hund", "hundUrl");
        System.out.println(x2 + "\n");

        // Test WordList
        System.out.println("WordList Test:");
        WordList list1 = new WordList();
        list1.addWord(x);
        list1.addWord(x2);

        System.out.println("List after adding words:");
        System.out.println(list1.toString());

        System.out.println("Retrieve WordEntry by word 'Hund':");
        WordEntry retrievedWord = list1.getWord("Hund");
        System.out.println(retrievedWord != null ? retrievedWord : "Word not found");

        System.out.println("\nDelete Word 'Hund':");
        WordEntry deletedWord = list1.deleteWord("Hund");
        System.out.println(deletedWord != null ? "Deleted: " + deletedWord : "Word not found");
        System.out.println("List after deletion:");
        System.out.println(list1);

        // Test WordTrainer
        System.out.println("\nWordTrainer Test:");
        System.out.println("\nNew bigger List Test:");
        WordEntry entry1 = new WordEntry("Hund", "https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3");
        WordEntry entry2 = new WordEntry("Katze", "https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg");
        WordEntry entry3 = new WordEntry("Vogel", "https://image.geo.de/30140936/t/bF/v3/w1440/r1/-/schuhschnabel-vogel-geolino2-jpg--80202-.jpg");
        WordEntry entry4 = new WordEntry("Fisch", "https://blog.the-british-shop.at/fileadmin/user_upload/fishy.jpg");
        WordEntry entry5 = new WordEntry("Maus", "https://media.os.fressnapf.com/cms/2020/07/Ratgeber_Kleintier_maus_1200x527.jpg?t=seoimg_703");

        WordList biggerList = new WordList();
        biggerList.addWord(entry1);
        biggerList.addWord(entry2);
        biggerList.addWord(entry3);
        biggerList.addWord(entry4);
        biggerList.addWord(entry5);
        System.out.println(biggerList);

        WordTrainer biggerTrainer = new WordTrainer(biggerList);
        biggerTrainer.setCorrectTrys(5);
        biggerTrainer.setAllTrys(10);

        System.out.println("\nRandom Word from bigger list:");
        WordEntry randomBiggerWord = biggerTrainer.getRandomWord();
        System.out.println(randomBiggerWord != null ? randomBiggerWord : "No words available");

        System.out.println("Check word 'Hund':");
        System.out.println(biggerTrainer.checkWord("Hund") ? "Correct" : "Incorrect");

        System.out.println("Check word 'Katze':");
        System.out.println(biggerTrainer.checkWord("Katze") ? "Correct" : "Incorrect");

        // Test SaveLoad.save
        System.out.println("\nTesting SaveLoad.save...");
        String fileName = "wordTrainerData.txt";
        try {
            SaveLoad.save(biggerTrainer, fileName);
            System.out.println("Daten erfolgreich in '" + fileName + "' gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Daten: " + e.getMessage());
        }

        // Test SaveLoad.laden
        System.out.println("\nTesting SaveLoad.laden...");
        try {
            WordTrainer loadedTrainer = SaveLoad.laden(fileName);
            System.out.println("Daten erfolgreich aus '" + fileName + "' geladen.");
            System.out.println("Geladene Wortliste:");
            System.out.println(loadedTrainer.getWordlist());
            System.out.println("Richtige Versuche: " + loadedTrainer.getCorrectTrys());
            System.out.println("Gesamtversuche: " + loadedTrainer.getAllTrys());
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Daten: " + e.getMessage());
        }
    }
}
