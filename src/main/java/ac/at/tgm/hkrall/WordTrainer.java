package ac.at.tgm.hkrall;

/**
 * Die Klasse WordTrainer ermöglicht das zufällige Abrufen von Wörtern aus einer WordList und überprüft,
 * ob die eingegebenen Antworten korrekt sind. Sie verfolgt die Anzahl der korrekten Versuche und alle Versuche insgesamt.
 * @author Helena Krall
 * @version 09.01.2025
 */
public class WordTrainer {

    private WordList wordlist;
    private WordEntry curWord;

    int correctTrys = 0;  // Anzahl der korrekten Versuche
    int allTrys = 0;      // Gesamtzahl der Versuche

    /**
     * Konstruktor der Klasse WordTrainer. Initialisiert den Trainer mit einer gegebenen WordList.
     *
     * @param wordlist Die WordList, aus der Wörter ausgewählt werden.
     */
    public WordTrainer(WordList wordlist) {
        this.wordlist = wordlist;
    }

    /**
     * Gibt ein zufällig ausgewähltes WordEntry aus der WordList zurück.
     * Wenn keine Wörter in der Liste vorhanden sind, wird null zurückgegeben.
     *
     * @return Ein zufällig ausgewähltes WordEntry oder null, wenn die Liste leer ist.
     */
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

    /**
     * Überprüft, ob das eingegebene Wort mit dem aktuellen Wort übereinstimmt.
     * Erhöht die Zähler für die Versuche und, falls korrekt, auch für die richtigen Versuche.
     *
     * @param word Das eingegebene Wort.
     * @return true, wenn das eingegebene Wort mit dem aktuellen Wort übereinstimmt, andernfalls false.
     */
    public boolean checkWord(String word) {
        boolean r = curWord != null && curWord.getWord().equalsIgnoreCase(word);
        this.allTrys++;
        if (r) {
            this.correctTrys++;
        }
        return r;
    }

    /**
     * Gibt das derzeitige WordEntry zurück.
     *
     * @return Das aktuelle WordEntry.
     */
    public WordEntry getCurWord() {
        return curWord;
    }

    /**
     * Gibt die WordList des WordTrainers zurück.
     *
     * @return Die WordList, die im WordTrainer verwendet wird.
     */
    public WordList getWordlist() {
        return wordlist;
    }

    /**
     * Gibt die Anzahl der korrekten Versuche zurück.
     *
     * @return Die Anzahl der korrekten Versuche.
     */
    public int getCorrectTrys() {
        return correctTrys;
    }

    /**
     * Gibt die Gesamtzahl der Versuche zurück.
     *
     * @return Die Gesamtzahl der Versuche.
     */
    public int getAllTrys() {
        return allTrys;
    }

    /**
     * Setzt die Anzahl der korrekten Versuche.
     *
     * @param correctTrys Die neue Anzahl der korrekten Versuche.
     */
    public void setCorrectTrys(int correctTrys) {
        this.correctTrys = correctTrys;
    }

    /**
     * Setzt die Gesamtzahl der Versuche.
     *
     * @param allTrys Die neue Gesamtzahl der Versuche.
     */
    public void setAllTrys(int allTrys) {
        this.allTrys = allTrys;
    }
}
