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

    public WordEntry deleteWord(String word) {
        for (WordEntry entry : wordList) {
            if (entry.getWord().equalsIgnoreCase(word)) {
                wordList.remove(entry);
                return entry;
            }
        }
        return null; // Falls das Wort nicht gefunden wurde
    }

    public WordEntry getWord(String word) {
        for (WordEntry entry : wordList) {
            if (entry.getWord().equalsIgnoreCase(word)) {
                return entry;
            }
        }
        return null; // Falls das Wort nicht gefunden wurde
    }

    @Override
    public String toString() {
        return "WordList: \n" + this.wordList +"\n";
    }

    public int getLength() {
        return this.wordList.size();
    }

    public ArrayList<WordEntry> getWordEntries() {
        return wordList;
    }
}
