package de.olotu.formeditor.vaadin.ui.elements;

import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import de.olotu.formeditor.vaadin.ui.components.CustomFormComponent;
import de.olotu.formeditor.vaadin.ui.components.FormDateField;
import de.olotu.formeditor.vaadin.ui.components.FormSelectBox;
import de.olotu.formeditor.vaadin.ui.components.FormTextField;
import de.olotu.formeditor.vaadin.ui.events.AddElementEvent;
import de.olotu.formeditor.vaadin.ui.model.FormComponentType;
import de.olotu.formeditor.vaadin.ui.model.FormComponentValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by kevin on 19/09/2017.
 */
public class AddNewElementWindow extends Window {

    private final TextField nameField = new TextField("Name: ");
    private final TextField labelField = new TextField("Label: ");
    private final ComboBox<String> componentTypeSelector = new ComboBox<>("Type: ");
    private final VerticalLayout vl = new VerticalLayout();
    private final QuestionValidationElement validationElement = new QuestionValidationElement();
    private Binder<CustomFormComponent> nameBinding = new Binder<>();
    private Binder<CustomFormComponent> labelBinding = new Binder<>();
    private Binder<CustomFormComponent> typeBinding = new Binder<>();
    private OptionsComponent optionsComponent;
    private boolean optionsVisible = false;

    public AddNewElementWindow(String caption) {
        super(caption);
        final Button save = new Button("Add question to questionnaire", this::handleClick);
        VerticalLayout questionElement = getQuestionFields();
        questionElement.setWidth("75%");
        vl.addComponent(questionElement);
        validationElement.setWidth("75%");
        vl.addComponent(new HorizontalSeparator());
        Button addValidationButton = new Button("Toggle validation");
        validationElement.setVisible(false);
        addValidationButton.addClickListener(e -> validationElement.setVisible(!validationElement.isVisible()));
        vl.addComponent(addValidationButton);
        vl.addComponent(validationElement);
        vl.addComponent(new HorizontalSeparator());
        vl.addComponent(save);
        setContent(vl);
        setWidth("50%");
    }

    private VerticalLayout getQuestionFields() {
        nameBinding.forField(nameField)
                .asRequired("This field is required!")
                .bind(CustomFormComponent::getName, CustomFormComponent::setName);
        labelBinding.forField(labelField)
                .asRequired("This field is required!")
                .bind(CustomFormComponent::getLabel, CustomFormComponent::setLabel);
        typeBinding.forField(componentTypeSelector)
                .asRequired("This field is required!")
                .withConverter(FormComponentType::valueOf, FormComponentType::toString)
                .bind(CustomFormComponent::getType, CustomFormComponent::setType);

        List<String> types = new ArrayList<>();
        Arrays.stream(FormComponentType.values()).map(FormComponentType::name).forEach(types::add);
        componentTypeSelector.setDataProvider(new ListDataProvider<String>(types));
        componentTypeSelector.addValueChangeListener(this::toggleOptionsVisibility);
        nameField.setWidth("100%");
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(nameField);
        labelField.setWidth("100%");
        layout.addComponent(labelField);
        componentTypeSelector.setWidth("100%");
        layout.addComponent(componentTypeSelector);
        return layout;
    }

    private void toggleOptionsVisibility(HasValue.ValueChangeEvent<String> e) {
        if (isOptionType(e)) {
            showOptions();
        } else if (optionsVisible) {
            hideOptions();
        }
    }

    private void hideOptions() {
        vl.removeComponent(optionsComponent);
        optionsVisible = false;
    }

    private boolean isOptionType(HasValue.ValueChangeEvent<String> e) {
        return FormComponentType.SELECTBOX.name().equals(e.getValue())
                || FormComponentType.CHECKBOX.name().equals(e.getValue());
    }

    private void showOptions() {
        optionsComponent = new OptionsComponent();
        vl.addComponent(optionsComponent);
        optionsVisible = true;
    }

    private Optional<AddElementEvent> createNewElementEvent(String type) {
        FormComponentValidation validation = new FormComponentValidation();
        validation.setCondition(validationElement.getSelectedCondition());
        validation.setValidator(validationElement.getSelectedValidator());
        validation.setRefValues(validationElement.getRefValues());
        if (FormComponentType.TEXTFIELD.name().equals(type)) {
            FormTextField newComponent = new FormTextField();
            if (writeBeanAndValidate(newComponent)) {
                newComponent.setValidation(validation);
                return createOptionalOfEvent(newComponent);
            }
        }
        if (FormComponentType.CHECKBOX.name().equals(type)) {

        }
        if (FormComponentType.DATE.name().equals(type)) {
            FormDateField newComponent = new FormDateField();
            if (writeBeanAndValidate(newComponent)) {
                return createOptionalOfEvent(newComponent);
            }
        }
        if (FormComponentType.SELECTBOX.name().equals(type)) {
            FormSelectBox<String> comboBox = new FormSelectBox<>(nameField.getValue());
            ListDataProvider<String> dataProvider = new ListDataProvider<>(optionsComponent.getValues());
            comboBox.setDataProvider(dataProvider);
            return createOptionalOfEvent(comboBox);
        }
        return Optional.empty();
    }

    private boolean writeBeanAndValidate(CustomFormComponent component) {
        boolean valid = true;
        try {
            nameBinding.writeBean(component);
        } catch (ValidationException e) {
            displayErrorMessages(e);
            valid = false;
        }
        try {
            labelBinding.writeBean(component);
        } catch (ValidationException e) {
            displayErrorMessages(e);
            valid = false;
        }
        try {
            typeBinding.writeBean(component);
        } catch (ValidationException e) {
            displayErrorMessages(e);
            valid = false;
        }
        return valid;
    }

    private void displayErrorMessages(ValidationException e) {
        e.getFieldValidationErrors().forEach(error -> {
            if (error.getField().equals(nameField)) {
                nameField.setComponentError(new UserError(error.getMessage().orElse("Undefined error occurred")));
            }
            if (error.getField().equals(labelField)) {
                labelField.setComponentError(new UserError(error.getMessage().orElse("Undefined error occurred")));
            }
            if (error.getField().equals(componentTypeSelector)) {
                componentTypeSelector.setComponentError(new UserError(error.getMessage().orElse("Undefined error occurred")));
            }
        });
    }

    private Optional<AddElementEvent> createOptionalOfEvent(CustomFormComponent component) {
        return Optional.of(new AddElementEvent(component));
    }

    private void handleClick(Event event) {
        createNewElementEvent(componentTypeSelector.getValue()).ifPresent(c -> {
            fireEvent(c);
            close();
        });
    }
}
