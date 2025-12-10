package ru.teamscore.shipment;

import java.math.BigDecimal;

public class Item {
    private final String id;
    private final String article;
    private final String title;
    private final BigDecimal price;

    private Item(String id, String article, String title, BigDecimal price) {
        this.id = id;
        this.article = article;
        this.title = title;
        this.price = price;
    }

    public static Item valueOf(String id, String article, String title, BigDecimal price) {
        if (id.isBlank()) {
            throw new IllegalArgumentException("Id must be not blank");
        }
        if (article.isBlank()) {
            throw new IllegalArgumentException("Article must be not blank");
        }
        if (title.isBlank()) {
            throw new IllegalArgumentException("Title must be not blank");
        }
        if (price.compareTo(new BigDecimal("0")) < 1) {
            throw new IllegalArgumentException("Price must be positive");
        }

        return new Item(id, article, title, price);
    }

    public String getId() {
        return id;
    }

    public String getArticle() {
        return article;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
