package de.olotu.formeditor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 13/08/2017.
 */
public class Form {

    private String name;

    private List<Component> components = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getComponents() {
        return components;
    }

}
