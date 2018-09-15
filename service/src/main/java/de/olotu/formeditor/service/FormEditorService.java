package de.olotu.formeditor.service;

/**
 * Created by kevin on 13/08/2017.
 */
public interface FormEditorService {

    boolean save(String jsonForm);

    String createNewForm(String name);

    String addComponent(String type, String name);

    String removeComponent(String name);


}
