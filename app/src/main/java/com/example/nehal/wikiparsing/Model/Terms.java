package com.example.nehal.wikiparsing.Model;

import java.util.List;

/**
 * Created by nehal on 28/7/17.
 */

public class Terms {

    private List<String> alias = null;
    private List<String> label = null;
    private List<String> description = null;

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
