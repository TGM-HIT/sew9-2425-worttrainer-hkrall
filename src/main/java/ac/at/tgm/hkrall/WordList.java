package ac.at.tgm.hkrall;

import java.util.ArrayList;

/**
 * Die Klasse WordList verwaltet eine Liste von WordEntry-Objekten.
 * Sie ermöglicht das Hinzufügen, Löschen und Abrufen von Wörtern sowie das Abrufen der Länge der Liste.
 * @author Helena Krall
 * @version 09.01.2025
 */
public class WordList {
    ArrayList<WordEntry> wordList;

    /**
     * Konstruktor der Klasse WordList. Initialisiert die Liste der WordEntry-Objekte.
     */
    public WordList() {
        this.wordList = new ArrayList<>();
    }

    /**
     * Fügt ein neues WordEntry-Objekt zur Liste hinzu.
     *
     * @param word Das WordEntry-Objekt, das zur Liste hinzugefügt wird.
     */
    public void addWord(WordEntry word) {
        this.wordList.add(word);
    }

    /**
     * Löscht ein WordEntry-Objekt aus der Liste anhand des angegebenen Wortes.
     * Wenn das Wort gefunden wird, wird das zugehörige WordEntry entfernt.
     *
     * @param word Das Wort, dessen zugehöriges WordEntry gelöscht werden soll.
     * @return Das gelöschte WordEntry, oder null, wenn das Wort nicht gefunden wurde.
     */
    public WordEntry deleteWord(String word) {
        for (WordEntry entry : wordList) {
            if (entry.getWord().equalsIgnoreCase(word)) {
                wordList.remove(entry);
                return entry;
            }
        }
        return null; // Falls das Wort nicht gefunden wurde
    }

    /**
     * Gibt das WordEntry-Objekt zurück, das mit dem angegebenen Wort übereinstimmt.
     *
     * @param word Das Wort, nach dem gesucht werden soll.
     * @return Das gefundene WordEntry-Objekt, oder null, wenn das Wort nicht gefunden wurde.
     */
    public WordEntry getWord(String word) {
        for (WordEntry entry : wordList) {
            if (entry.getWord().equalsIgnoreCase(word)) {
                return entry;
            }
        }
        return null; // Falls das Wort nicht gefunden wurde
    }

    /**
     * Gibt eine String-Darstellung der gesamten WordList zurück.
     *
     * @return Eine String-Repräsentation der WordList.
     */
    @Override
    public String toString() {
        return "WordList: \n" + this.wordList + "\n";
    }

    /**
     * Gibt die Länge der WordList zurück (Anzahl der enthaltenen WordEntry-Objekte).
     *
     * @return Die Anzahl der WordEntry-Objekte in der Liste.
     */
    public int getLength() {
        return this.wordList.size();
    }

    /**
     * Gibt die Liste der WordEntry-Objekte zurück.
     *
     * @return Die ArrayList der WordEntry-Objekte.
     */
    public ArrayList<WordEntry> getWordEntries() {
        return wordList;
    }
}
