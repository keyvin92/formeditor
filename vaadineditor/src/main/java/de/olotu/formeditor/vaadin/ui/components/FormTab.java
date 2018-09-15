package de.olotu.formeditor.vaadin.ui.components;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import de.olotu.formeditor.vaadin.ui.elements.AddNewElementWindow;
import de.olotu.formeditor.vaadin.ui.elements.HorizontalSeparator;
import de.olotu.formeditor.vaadin.ui.events.AddElementListener;
import de.olotu.formeditor.vaadin.ui.events.ChangeTabCaptionEvent;
import de.olotu.formeditor.vaadin.ui.events.ChangeTabCaptionListener;
import de.olotu.formeditor.vaadin.ui.serializable.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 30/10/2017.
 */
public class FormTab extends VerticalLayout {
    private String name;
    private List<CustomFormComponent> questions = new ArrayList<>();

    FormTab(FormTabSheet tabSheet) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(createAddButton());
        setCaption("New Page");
        TextField tabNameField = new TextField("Page Name: ");
        addListener(new ChangeTabCaptionListener(tabSheet));
        tabNameField.addValueChangeListener(e -> {
            fireEvent(new ChangeTabCaptionEvent(this, e.getValue()));
            this.name = e.getValue();
        });
        addComponent(tabNameField);
        addComponent(new HorizontalSeparator());
        addComponent(horizontalLayout);
    }

    private Button createAddButton() {
        final Button addButton = new Button("Add new question...");
        addButton.addClickListener(e -> {
            final AddElementListener listener = new AddElementListener(this);
            final Window subWindow = new AddNewElementWindow("Add new question");
            subWindow.addListener(listener);
            subWindow.center();
            getUI().addWindow(subWindow);
        });
        return addButton;
    }

    public void addQuestion(CustomFormComponent component) {
        questions.add(component);
        addComponent(component);
    }

    public List<CustomFormComponent> getQuestions() {
        return this.questions;
    }

    public String getName() {
        return this.name;
    }

}
