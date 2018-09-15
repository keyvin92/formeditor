package de.olotu.formeditor.vaadin.ui.components;

import com.vaadin.server.UserError;
import com.vaadin.ui.TextField;
import de.olotu.formeditor.vaadin.ui.model.FormComponentType;
import de.olotu.formeditor.vaadin.ui.model.FormComponentValidation;
import de.olotu.formeditor.vaadin.ui.utils.FormValidationProvider;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by kevin on 30/10/2017.
 */
public class FormTextField extends TextField implements CustomFormComponent {
    private String name;
    private String label;
    private String infoText;
    private FormComponentType type;
    private FormComponentValidation validation;

    public FormTextField() {
        addValueChangeListener(e -> validate());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
        setCaption(label);
    }

    @Override
    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    @Override
    public String getInfoText() {
        return this.infoText;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public FormComponentType getType() {
        return this.type;
    }

    @Override
    public void setType(FormComponentType type) {
        this.type = type;
    }

    @Override
    public FormComponentValidation getValidation() {
        return this.validation;
    }

    @Override
    public void setValidation(FormComponentValidation validation) {
        this.validation = validation;
    }

    @Override
    public boolean validate() {
        return Optional.ofNullable(getValidation())
                .map(v -> FormValidationProvider.validate(v.getValidator(), getValue(),
                        v.getRefValues(),
                        v.getCondition()))
                .map(isValid -> {
                    if (!isValid) {
                        setComponentError(new UserError("Validation failed"));
                    } else {
                        setComponentError(null);
                    }
                    return isValid;
                })
                .orElse(true);
    }
}
