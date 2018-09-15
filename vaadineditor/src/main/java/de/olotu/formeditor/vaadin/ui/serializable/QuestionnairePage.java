package de.olotu.formeditor.vaadin.ui.serializable;

import java.util.List;

/**
 * Created by kevin on 31/10/2017.
 */
public class QuestionnairePage {

    private int number;

    private String title;

    private List<Question> questions;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
