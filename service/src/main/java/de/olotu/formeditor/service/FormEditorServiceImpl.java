package de.olotu.formeditor.service;

import de.olotu.formeditor.model.Form;

/**
 * Created by kevin on 13/08/2017.
 */
public class FormEditorServiceImpl implements FormEditorService {

    private static final FormBuilder builder = new FormBuilder();

    public String toJSON(Form form) {
        return "";
    }

    @Override
    public boolean save(String jsonForm) {
        return false;
    }

    @Override
    public String createNewForm(String name) {
        return builder.builder(name).getCurrentStateJson();
    }

    @Override
    public String addComponent(String type, String name) {
        return null;
    }

    @Override
    public String removeComponent(String name) {
        return null;
    }
}
