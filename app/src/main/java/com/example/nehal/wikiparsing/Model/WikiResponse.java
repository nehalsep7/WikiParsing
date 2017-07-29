package com.example.nehal.wikiparsing.Model;

/**
 * Created by nehal on 28/7/17.
 */

public class WikiResponse {
    private Boolean batchcomplete;
    private WikiQuery query;

    public Boolean getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(Boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public WikiQuery getQuery() {
        return query;
    }

    public void setQuery(WikiQuery query) {
        this.query = query;
    }
}
