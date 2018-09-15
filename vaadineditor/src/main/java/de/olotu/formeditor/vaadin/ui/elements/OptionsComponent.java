package de.olotu.formeditor.vaadin.ui.elements;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kevin on 03/10/2017.
 */
public class OptionsComponent extends VerticalLayout {

    private List<TextField> textFields = new ArrayList<>();

    public OptionsComponent() {
        addComponent(getNewRow());
        setCaption("Add options:");
    }

    public List<String> getValues() {
        return textFields.stream().map(TextField::getValue).collect(Collectors.toList());
    }

    private HorizontalLayout getNewRow() {
        final HorizontalLayout hl = new HorizontalLayout();
        final TextField textField = new TextField();
        textFields.add(textField);
        final Button addRowButton = new Button("+", e -> addComponent(getNewRow()));
        hl.addComponent(textField);
        hl.addComponent(addRowButton);
        return hl;
    }
}
