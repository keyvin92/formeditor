package de.olotu.formeditor.vaadin.ui.utils;

import de.olotu.formeditor.vaadin.ui.validators.QuestionValidator;
import de.olotu.formeditor.vaadin.ui.validators.RequiredFieldValidator;
import de.olotu.formeditor.vaadin.ui.validators.StringLengthValidator;
import de.olotu.formeditor.vaadin.ui.validators.ValidationCondition;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kevin on 01/11/2017.
 */
public class FormValidationProvider {

    private static final Set<QuestionValidator> validators = Stream
            .of(
                new RequiredFieldValidator(),
                new StringLengthValidator())
            .collect(Collectors.toSet());

    private static final Map<String, QuestionValidator> validatorMap = validators.stream()
            .collect(Collectors.toMap(e -> e.getClass().getSimpleName(), e -> e));

    public static boolean validate(String validator, Object value, List<?> refValues, ValidationCondition condition) {
        final Object[] refArray = toRefArray(refValues);
        return Optional.ofNullable(validatorMap.get(validator))
                .map(validatorInst -> validatorInst.validate(value, refArray, condition))
                .orElse(false);
    }

    private static Object[] toRefArray(List<?> refValues) {
        Object[] refArray = null;
        if (Objects.nonNull(refValues) && !refValues.isEmpty()) {
            refArray = refValues.toArray();
        }
        return refArray;
    }
}
