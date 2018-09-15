package de.olotu.formeditor.vaadin.ui.serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 31/10/2017.
 */
public class Questionnaire {

    private List<QuestionnairePage> pages = new ArrayList<>();

    private String name;

    public List<QuestionnairePage> getPages() {
        return pages;
    }

    public void setPages(List<QuestionnairePage> pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
