package de.olotu.formeditor.vaadin.ui.components;

import com.vaadin.ui.Component;
import de.olotu.formeditor.vaadin.ui.model.FormComponentType;
import de.olotu.formeditor.vaadin.ui.model.FormComponentValidation;

/**
 * Created by kevin on 30/10/2017.
 */
public interface CustomFormComponent extends Component {
    String getName();

    void setName(String name);

    void setLabel(String label);

    void setInfoText(String infoText);

    String getInfoText();

    String getLabel();

    FormComponentType getType();

    void setType(FormComponentType type);

    FormComponentValidation getValidation();

    void setValidation(FormComponentValidation validation);

    boolean validate();

}