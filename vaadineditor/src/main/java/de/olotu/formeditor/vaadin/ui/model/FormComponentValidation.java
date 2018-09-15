package de.olotu.formeditor.vaadin.ui.model;

import de.olotu.formeditor.vaadin.ui.validators.ValidationCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 19/09/2017.
 */
public class FormComponentValidation {

    private String validator;

    private ValidationCondition condition;

    private List<String> refValues = new ArrayList<>();

    public List<String> getRefValues() {
        return refValues;
    }

    public void setRefValues(List<String> refValues) {
        this.refValues = refValues;
    }

    public ValidationCondition getCondition() {
        return condition;
    }

    public void setCondition(ValidationCondition condition) {
        this.condition = condition;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }
}
