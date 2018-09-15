package de.olotu.formeditor.model;

import java.util.List;

/**
 * Created by kevin on 13/08/2017.
 */
public class Component<T> {

    private String name;

    private String label;

    private ComponentType type;

    private List<Validator> validators;

    private String value;

    private T typedValue;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public T getTypedValue() {
        return typedValue;
    }

    public void setTypedValue(T typedValue) {
        this.typedValue = typedValue;
    }
}
