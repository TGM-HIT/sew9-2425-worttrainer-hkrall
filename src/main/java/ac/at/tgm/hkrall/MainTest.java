package ac.at.tgm.hkrall;

public class MainTest {

    public static void main(String[] args) {

        WordEntry x= new WordEntry("Hund","https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3");
        System.out.println(x.toString());

        WordEntry x2= new WordEntry("Hund","hundUrl");
        System.out.println(x2.toString()+"\n");


        //List tests
        System.out.println("List Test:");
        WordList list1=new WordList();
        list1.addWord(x);
        list1.addWord(x2);

        System.out.println(list1.getWord(0).toString());
        System.out.print(list1.toString());
        System.out.println("Delete Word: "+list1.deleteWord(1).toString());


        System.out.println("WordTrainer Test:");
        WordTrainer trainer1= new WordTrainer(list1);
        System.out.println(trainer1.getRandomWord().toString());



        System.out.println("\n\nNew bigger List");
        WordEntry entry1= new WordEntry("Hund","https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3");
        WordEntry entry2 = new WordEntry("Katze", "https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg");
        WordEntry entry3 = new WordEntry("Vogel", "https://image.geo.de/30140936/t/bF/v3/w1440/r1/-/schuhschnabel-vogel-geolino2-jpg--80202-.jpg");
        WordEntry entry4 = new WordEntry("Fisch", "https://blog.the-british-shop.at/fileadmin/user_upload/fishy.jpg");
        WordEntry entry5 = new WordEntry("Maus", "https://media.os.fressnapf.com/cms/2020/07/Ratgeber_Kleintier_maus_1200x527.jpg?t=seoimg_703");

        WordList list= new WordList();
        list.addWord(entry1);list.addWord(entry2);list.addWord(entry3);list.addWord(entry4);list.addWord(entry5);
        System.out.print(list.toString());

        WordTrainer trainer= new WordTrainer(list);
        System.out.println("\nRandom Word: \n"+trainer.getRandomWord().toString());

        System.out.println("checkWord: \n"+trainer.checkWord("Hund"));
    }

}
