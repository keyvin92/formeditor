package de.olotu.formeditor.vaadin.ui.utils;

import de.olotu.formeditor.vaadin.ui.components.FormTab;
import de.olotu.formeditor.vaadin.ui.components.FormTabSheet;
import de.olotu.formeditor.vaadin.ui.serializable.Questionnaire;
import de.olotu.formeditor.vaadin.ui.serializable.QuestionnairePage;

import java.util.stream.Collectors;

/**
 * Created by kevin on 31/10/2017.
 */
public class FormTabSheetQuestionnaireMapper {

    public static Questionnaire toQuestionnaire(FormTabSheet tabSheet) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(tabSheet.getName());
        tabSheet.forEach(tab -> {
            if (tab instanceof FormTab) {
                FormTab formTab = (FormTab) tab;
                QuestionnairePage page = new QuestionnairePage();
                page.setTitle(formTab.getName());
                page.setQuestions(formTab.getQuestions().stream().map(QuestionComponentMapper::toQuestion).collect(Collectors.toList()));
                final LoopCounter counter = new LoopCounter();
                page.getQuestions().forEach(question -> {
                    question.setSortOrder(counter.getCount());
                    counter.increment();
                });
                questionnaire.getPages().add(page);
            }
        });
        final LoopCounter counter = new LoopCounter();
        questionnaire.getPages().forEach(page -> {
            page.setNumber(counter.getCount());
            counter.increment();
        });
        return questionnaire;
    }
}
