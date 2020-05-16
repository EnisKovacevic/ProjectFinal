package com.example.project3;

public class Vinyl {
    private String name;
    private String songtitle;
    private int thumbnail;
    private int prices;

    public Vinyl() {
    }

    public Vinyl(String name, String songtitle, int prices, int thumbnail) {
        this.setName(name);
        this.setSongtitle(songtitle);
        this.setPrices(prices);
        this.setThumbnail(thumbnail);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }
}