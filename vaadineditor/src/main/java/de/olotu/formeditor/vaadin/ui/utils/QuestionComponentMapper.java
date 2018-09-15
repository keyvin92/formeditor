package de.olotu.formeditor.vaadin.ui.utils;

import com.google.gson.Gson;
import de.olotu.formeditor.vaadin.ui.components.CustomFormComponent;
import de.olotu.formeditor.vaadin.ui.model.FormComponentType;
import de.olotu.formeditor.vaadin.ui.serializable.Question;
import de.olotu.formeditor.vaadin.ui.serializable.QuestionType;

/**
 * Created by kevin on 31/10/2017.
 */
class QuestionComponentMapper {

    static Question toQuestion(CustomFormComponent component) {
        Question question = new Question();
        question.setName(component.getName());
        question.setLabel(component.getLabel());
        question.setInfoText(component.getInfoText());
        question.setValidation(new Gson().toJson(component.getValidation()));
        if (FormComponentType.TEXTFIELD.equals(component.getType())) {
            question.setType(QuestionType.TEXT);
        }

        return question;
    }
}
