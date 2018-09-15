package de.olotu.formeditor.vaadin.ui.validators;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by kevin on 31/10/2017.
 */
public class StringLengthValidator implements QuestionValidator {

    @Override
    public boolean validate(Object value, Object[] refValues, ValidationCondition condition) {
        Optional<Integer> refValue = Arrays.stream(refValues)
                .filter(Objects::nonNull)
                .findFirst()
                .map(e -> (String)e)
                .map(Integer::parseInt);
        if (value instanceof String && refValue.isPresent()) {
            String valueStr = (String) value;
            return validate(valueStr, refValue.get(), condition);
        }
        return false;
    }

    private boolean validate(String value, int ref, ValidationCondition condition) {
        if (ValidationCondition.GREATER_THAN.equals(condition)) {
            return value.length() > ref;
        }
        if (ValidationCondition.SMALLER_THAN.equals(condition)) {
            return value.length() < ref;
        }
        if (ValidationCondition.EQUALS.equals(condition)) {
            return value.length() == ref;
        }
        if (ValidationCondition.GREATER_OR_EQUAL.equals(condition)) {
            return value.length() >= ref;
        }
        if (ValidationCondition.SMALLER_OR_EQUAL.equals(condition)) {
            return value.length() <= ref;
        }
        return false;
    }
}
