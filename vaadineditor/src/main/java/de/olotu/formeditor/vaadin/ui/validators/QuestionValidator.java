package de.olotu.formeditor.vaadin.ui.validators;

/**
 * Created by kevin on 31/10/2017.
 */
public interface QuestionValidator {

    boolean validate(Object value, Object[] refValues, ValidationCondition condition);

}
