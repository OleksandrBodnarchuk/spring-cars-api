package pl.alex;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TempExporter {
    public static void main(String[] args) throws IOException {
        List<Post> posts = new ArrayList<>();
        System.out.println("Connecting to URL...");
        Document doc = Jsoup.connect("https://4pda.to/").get();
        Elements postElements = doc.getElementsByAttributeValue("itemprop", "url");

        for (Element element : postElements) {
            String detailsLink = element.attr("href");
            Post post = new Post();
            post.setDetailsLink(detailsLink);
            post.setTitle(element.attr("title"));

            // Connecting to detailsLink
            System.out.println("Connecting to post details: "+ detailsLink);
            Document postDetailsDoc = Jsoup.connect(detailsLink).get();
            try {
                Element authorElement = postDetailsDoc.getElementsByClass("name").first().child(0);
                post.setAuthor(authorElement.text());
                post.setAuthorDetails(authorElement.attr("href"));
            }catch (NullPointerException e){
                post.setAuthor("No author");
                post.setAuthorDetails("No Link");
            }
            post.setDateOfCreation(postDetailsDoc.getElementsByClass("date").first().text());

            posts.add(post);
        }


        posts.forEach(System.out::println);

    }
}

class Post {
    private String title;
    private String detailsLink;
    private String author;
    private String authorDetails;
    private String dateOfCreation;

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", detailsLink='" + detailsLink + '\'' +
                ", author='" + author + '\'' +
                ", authorDetails='" + authorDetails + '\'' +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(String authorDetails) {
        this.authorDetails = authorDetails;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}