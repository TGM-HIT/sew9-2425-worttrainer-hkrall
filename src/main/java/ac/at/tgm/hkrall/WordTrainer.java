package ac.at.tgm.hkrall;

public class WordTrainer {

    private WordList wordlist;
    private String curWord="invalid";

    public WordTrainer(WordList wordlist) {
        this.wordlist = wordlist;
    }


    public WordEntry getRandomWord(){
        WordEntry r= this.wordlist.getWord((int) (Math.random() * (this.wordlist.getLength())));
        this.curWord=r.getWord();
        return r;
    }


    public boolean checkWord(String word){
        return this.curWord.equalsIgnoreCase(word);
    }
}
