package ac.at.tgm.hkrall;

public class WordTrainer {

    private WordList wordlist;

    //TODO evtl curWord statt string zu einem WordEntry machen
    private String curWord="invalid";


    int correctTrys=0;
    int allTrys=0;

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

    public String getCurWord() {
        return curWord;
    }

    public WordList getWordlist() {
        return wordlist;
    }


    public int getCorrectTrys() {
        return correctTrys;
    }

    public int getAllTrys() {
        return allTrys;
    }

    public void setCorrectTrys(int correctTrys) {
        this.correctTrys = correctTrys;
    }

    public void setAllTrys(int allTrys) {
        this.allTrys = allTrys;
    }
}
