package de.olotu.formeditor.vaadin.ui.elements;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import de.olotu.formeditor.vaadin.ui.validators.RequiredFieldValidator;
import de.olotu.formeditor.vaadin.ui.validators.StringLengthValidator;
import de.olotu.formeditor.vaadin.ui.validators.ValidationCondition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kevin on 31/10/2017.
 */
class QuestionValidationElement extends VerticalLayout {
    private String selectedValidator;
    private ValidationCondition selectedCondition;
    private List<String> refValues;
    private ComboBox<String> validatorSelect = new ComboBox<>("Validator");
    private ComboBox<ValidationCondition> conditionSelect = new ComboBox<>("Condition");
    private Map<Component, String> refValueMap = new HashMap<>();

    QuestionValidationElement() {
        super();
        initComboBoxes();
        addComponent(new Label("Validation rule:"));
        validatorSelect.setWidth("100%");
        validatorSelect.addValueChangeListener(e -> selectedValidator =  e.getValue());
        addComponent(validatorSelect);
        conditionSelect.addValueChangeListener(e -> selectedCondition = e.getValue());
        conditionSelect.setWidth("100%");
        addComponent(conditionSelect);
        addComponent(new Label("Reference value"));
        addFirstRefValueRow();
    }

    private void addNewRefValueRow() {
        HorizontalLayout hl = new HorizontalLayout();
        TextField refValueTextField = new TextField();
        refValueTextField.addValueChangeListener(e -> {
            refValueMap.put(e.getComponent(), e.getValue());
            updateRefValueList();
        });
        Button removeRowButton = new Button(" - ");
        removeRowButton.setSizeUndefined();
        hl.addComponent(refValueTextField);
        removeRowButton.addClickListener(e -> {
            refValueTextField.setValue("");
            refValueMap.remove(refValueTextField);
            removeComponent(hl);
        });
        hl.addComponent(removeRowButton);
        addComponent(hl);
    }

    private void updateRefValueList() {
        refValues = refValueMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    private void addFirstRefValueRow() {
        HorizontalLayout hl = new HorizontalLayout();
        TextField refValueTextField = new TextField();
        refValueTextField.addValueChangeListener(ev -> {
            refValueMap.put(ev.getComponent(), ev.getValue());
            updateRefValueList();
        });
        Button addRefValueButton = new Button(" + ");
        addRefValueButton.addClickListener(e -> addNewRefValueRow());
        addRefValueButton.setSizeUndefined();
        hl.addComponent(refValueTextField);
        hl.addComponent(addRefValueButton);
        addComponent(hl);
    }

    private void initComboBoxes() {
        ListDataProvider<String> validatorNames = new ListDataProvider<>(Stream.of(
                StringLengthValidator.class.getSimpleName(),
                RequiredFieldValidator.class.getSimpleName()
        ).collect(Collectors.toList()));
        validatorSelect.setDataProvider(validatorNames);
        ListDataProvider<ValidationCondition> conditionData = new ListDataProvider<>(Arrays.asList(ValidationCondition.values()));
        conditionSelect.setDataProvider(conditionData);
    }

    public String getSelectedValidator() {
        return selectedValidator;
    }

    public ValidationCondition getSelectedCondition() {
        return selectedCondition;
    }

    public List<String> getRefValues() {
        return refValues;
    }
}
