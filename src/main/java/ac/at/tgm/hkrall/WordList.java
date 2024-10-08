package ac.at.tgm.hkrall;

import java.util.ArrayList;

public class WordList {
    ArrayList<WordEntry> wordList;


    public WordList() {
        this.wordList = new ArrayList<>();

    }

    public void addWord(WordEntry word){
        this.wordList.add(word);
    }

    public WordEntry deleteWord(int i){
        return this.wordList.remove(i);
    }

    public WordEntry getWord(int i){
        return this.wordList.get(i);
    }

    @Override
    public String toString() {
        return "WordList: \n" + this.wordList +"\n";
    }

    public int getLength() {
        return this.wordList.size();
    }

}
