package de.olotu.formeditor.vaadin.ui.validators;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Created by kevin on 31/10/2017.
 */
public class RequiredFieldValidator implements QuestionValidator {

    @Override
    public boolean validate(Object value, Object[] refValues, ValidationCondition condition) {
        if (value instanceof String) {
            return StringUtils.isNotBlank((String) value);
        }
        return Objects.nonNull(value);
    }
}
