package pl.alex.cars.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.ConnectException;

/*
 * CLass that get our connections
 */
public class ConnectionUtil {
    public static final String MAIN_URL = "http://www.automobile-data.com/";
    // URL CONNECTION
    protected static Document getHtmlDocFromUrl(String url) throws IOException {
        Document document;
        try {
            document = Jsoup.connect(url).timeout(10 * 50000).get();
        } catch (ConnectException e) {
            document = Jsoup.connect(url).timeout(10 * 50000).get();
        }
        return document;
    }
}
