package de.olotu.formeditor.service;

import de.olotu.formeditor.model.Component;
import de.olotu.formeditor.model.Form;

import javax.swing.text.html.FormView;

/**
 * Created by kevin on 13/08/2017.
 */
public class FormBuilder {

    private Form form;

    public FormBuilder builder(String name) {
        this.form = new Form();
        this.form.setName(name);
        return this;
    }

    public FormBuilder addComponent(Component component) {
        this.form.getComponents().add(component);
        return this;
    }

    public FormBuilder removeComponent(String name) {
        this.form.getComponents().stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .ifPresent(c -> form.getComponents().remove(c));
        return this;
    }

    public Form build() {
        return this.form;
    }

    public String getCurrentStateJson() {
        return form.toString();
    }

}
