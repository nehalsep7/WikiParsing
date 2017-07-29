package com.example.nehal.wikiparsing.Model;

/**
 * Created by nehal on 28/7/17.
 */

public class WikiPage {

    private int pageid;
    private int ns;
    private String title;
    private Thumbnail thumbnail;
    private String pageimage;
    private Terms terms;


    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPageimage() {
        return pageimage;
    }

    public void setPageimage(String pageimage) {
        this.pageimage = pageimage;
    }

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }
}
