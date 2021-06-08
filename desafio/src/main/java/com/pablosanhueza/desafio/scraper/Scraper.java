package com.pablosanhueza.desafio.scraper;

import com.pablosanhueza.desafio.entities.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


@Component
public class Scraper{

    public News getData(String nameUrl) throws IOException {
        Document doc = Jsoup.connect(nameUrl)
                .get();
        Element title = doc.getElementsByClass("page-title-1").first();
        Element subtitle = doc.getElementsByClass("article-lead").first();
        Element author = doc.getElementsByClass("author-name").select("a").first();

        String date = doc.select("time").first().attr("datetime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter.ISO_DATE_TIME);

        Elements paragraphs = doc.getElementsByClass("article-content").select("p");
        String stringParagraphs = paragraphs.stream()
                .map(x -> x.text()).collect(Collectors.joining("\n"));

        News news = new News(
                null,
                nameUrl,
                String.valueOf(title.text()),
                String.valueOf(subtitle.text()),
                String.valueOf(author.text()),
                dateTime,
                stringParagraphs
                );

        return news;
    }
}
