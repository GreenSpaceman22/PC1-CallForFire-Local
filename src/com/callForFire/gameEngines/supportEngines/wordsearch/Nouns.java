package com.callForFire.gameEngines.supportEngines.wordsearch;

import java.util.ArrayList;
import java.util.List;

public class Nouns {
    private List<String> nouns = new ArrayList<>();

    public Nouns(List<String> nouns) {
        this.setNouns(nouns);
    }

    public List<String> getNouns() {
        return nouns;
    }

    public void setNouns(List<String> nouns) {
        this.nouns = nouns;
    }
}