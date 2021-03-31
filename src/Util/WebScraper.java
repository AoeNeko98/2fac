/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import entity.Book;
import java.io.IOException;
import javafx.scene.control.Label;

/**
 *
 * @author Aoe Neko 98
 */
public class WebScraper {

    public void webScrapper(Book b, Label c) {

        String searchQuery = b.getIsbn();
        String baseUrl = "https://openlibrary.org/";
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = null;
        try {
            String searchUrl = baseUrl + "search?isbn=" + searchQuery;
            System.out.println(searchUrl);
            try {

                page = client.getPage(searchUrl);
            } catch (FailingHttpStatusCodeException | IOException e) {
                e.printStackTrace();
            }
            HtmlElement spanNote = ((HtmlElement) page.getFirstByXPath("//li/span[@itemprop='ratingValue']"));
            try {
                System.out.println(spanNote.asText());
                c.setText(spanNote.asText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
