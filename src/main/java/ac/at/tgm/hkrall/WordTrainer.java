package ac.at.tgm.hkrall;

public class WordTrainer {

    private WordList wordlist;


    private WordEntry curWord;


    int correctTrys=0;
    int allTrys=0;

    public WordTrainer(WordList wordlist) {
        this.wordlist = wordlist;
    }


  /*  public WordEntry getRandomWord(){
        WordEntry r= this.wordlist.getWord((int) (Math.random() * (this.wordlist.getLength())));
        this.curWord=r.getWord();
        return r;
    }*/

    public WordEntry getRandomWord() {
        if (this.wordlist.getLength() == 0) {
            this.curWord = null; // Kein Eintrag vorhanden
            return null;
        }

        WordEntry r = this.wordlist.getWordEntries()
                .get((int) (Math.random() * this.wordlist.getLength()));
        this.curWord = r; // Speichert das gesamte WordEntry
        return r;
    }

    public boolean checkWord(String word) {
        boolean r= curWord != null && curWord.getWord().equalsIgnoreCase(word);
        this.allTrys++;
        if(r){
            this.correctTrys++;
        }
        return r;
    }

    public WordEntry getCurWord() {
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
