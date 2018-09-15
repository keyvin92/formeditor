package de.olotu.formeditor.vaadin.ui.components;

import com.vaadin.server.UserError;
import com.vaadin.ui.ComboBox;
import de.olotu.formeditor.vaadin.ui.model.FormComponentType;
import de.olotu.formeditor.vaadin.ui.model.FormComponentValidation;
import de.olotu.formeditor.vaadin.ui.utils.FormValidationProvider;

import java.util.Optional;

/**
 * Created by kevin on 30/10/2017.
 */
public class FormSelectBox<T> extends ComboBox<T> implements CustomFormComponent {

    public FormSelectBox(String caption) {
        super(caption);
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public void setInfoText(String infoText) {

    }

    @Override
    public String getInfoText() {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public FormComponentType getType() {
        return null;
    }

    @Override
    public void setType(FormComponentType type) {

    }

    @Override
    public FormComponentValidation getValidation() {
        return null;
    }

    @Override
    public void setValidation(FormComponentValidation validation) {

    }

    public boolean validate() {
        return Optional.ofNullable(getValidation())
                .map(v -> FormValidationProvider.validate(v.getValidator(), getValue(), v.getRefValues(), v.getCondition()))
                .map(isValid -> {
                    if (isValid) {
                        setComponentError(new UserError("Validation failed"));
                    }
                    return isValid;
                })
                .orElse(true);
    }
}
