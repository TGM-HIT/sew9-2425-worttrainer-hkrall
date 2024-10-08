package ac.at.tgm.hkrall;
import java.net.MalformedURLException;
import java.net.URL;
public class WordEntry {
    private String word;
    private String url;

    public WordEntry(String word, String url) {
        if(this.checkUrl(url)){
            this.word = word;
            this.url = url;
        }else{this.word="invalid Word";this.url="invalid URL";}
    }


    public boolean checkUrl(String urlString) {
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return "WordEntry: " +
                "word='" + word + '\'' +
                ", url='" + url + '\'' ;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public String getUrl() {
        return url;
    }
}
