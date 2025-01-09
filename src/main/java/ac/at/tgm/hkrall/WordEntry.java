package ac.at.tgm.hkrall;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Die Klasse WordEntry repräsentiert ein Wort und die zugehörige URL.
 * Sie ermöglicht die Speicherung eines Wortes und einer URL sowie die Validierung der URL.
 * @author Helena Krall
 * @version 09.01.2025
 */
public class WordEntry {
    private String word;
    private String url;

    /**
     * Konstruktor der Klasse WordEntry. Wenn die URL gültig ist, wird das Wort und die URL gespeichert.
     * Andernfalls wird das Wort als "invalid Word" und die URL als "invalid URL" gesetzt.
     *
     * @param word Das Wort, das gespeichert werden soll.
     * @param url Die URL, die dem Wort zugeordnet werden soll.
     */
    public WordEntry(String word, String url) {
        if (this.checkUrl(url)) {
            this.word = word;
            this.url = url;
        } else {
            this.word = "invalid Word";
            this.url = "invalid URL";
        }
    }

    /**
     * Überprüft, ob eine gegebene URL gültig ist.
     *
     * @param urlString Die URL, die überprüft werden soll.
     * @return true, wenn die URL gültig ist, andernfalls false.
     */
    public boolean checkUrl(String urlString) {
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Gibt eine String-Repräsentation des WordEntry-Objekts zurück.
     *
     * @return Eine String-Darstellung des Wortes und der URL.
     */
    @Override
    public String toString() {
        return "WordEntry: " +
                "word='" + word + '\'' +
                ", url='" + url + '\'';
    }

    /**
     * Setzt das Wort des WordEntry-Objekts.
     *
     * @param word Das neue Wort.
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Setzt die URL des WordEntry-Objekts.
     *
     * @param url Die neue URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gibt das gespeicherte Wort zurück.
     *
     * @return Das gespeicherte Wort.
     */
    public String getWord() {
        return word;
    }

    /**
     * Gibt die gespeicherte URL zurück.
     *
     * @return Die gespeicherte URL.
     */
    public String getUrl() {
        return url;
    }
}
