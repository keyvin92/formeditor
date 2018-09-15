package de.olotu.formeditor.vaadin.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.ui.*;
import com.vaadin.ui.dnd.DropTargetExtension;
import de.olotu.formeditor.vaadin.ui.components.FormTab;
import de.olotu.formeditor.vaadin.ui.components.FormTabSheet;
import de.olotu.formeditor.vaadin.ui.elements.HorizontalSeparator;
import de.olotu.formeditor.vaadin.ui.elements.SaveQuestionnaireButton;
import de.olotu.formeditor.vaadin.ui.events.ChangeTabCaptionListener;

import javax.servlet.annotation.WebServlet;
import java.util.Iterator;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private VerticalLayout editorLayout = new VerticalLayout();
    private DropTargetExtension<VerticalLayout> dropTarget = new DropTargetExtension<>(editorLayout);
    private TextField formNameField = new TextField("Questionnaire name: ");
    private FormTabSheet formTabSheet;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initEditorMode();
    }

    private void initEditorMode() {
        dropTarget.setDropEffect(DropEffect.MOVE);
        formTabSheet = new FormTabSheet();
        editorLayout.addComponent(formNameField);
        editorLayout.addComponent(new HorizontalSeparator());
        Panel panel = new Panel();
        panel.setContent(formTabSheet);
        addListener(new ChangeTabCaptionListener(formTabSheet));
        editorLayout.addComponent(panel);
        editorLayout.addComponent(new HorizontalSeparator());
        Button saveQuestionnaireButton = new SaveQuestionnaireButton(formTabSheet, "Save questionnaire");
        Button previewModeButton = new Button("Preview");
        previewModeButton.addClickListener(e -> enterPreviewMode());
        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponent(previewModeButton);
        hl.addComponent(saveQuestionnaireButton);
        editorLayout.addComponent(hl);
        setContent(editorLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private void enterPreviewMode() {
        VerticalLayout previewModeLayout = new VerticalLayout();
        TabSheet tabSheet = new TabSheet();
        Iterator<Component> tabs = formTabSheet.iterator();
        boolean isFirst = true;
        while (tabs.hasNext()) {
            Component component = tabs.next();
            if (!(component instanceof FormTab)) {
                continue;
            }
            FormTab c = (FormTab) component;
            VerticalLayout questions = new VerticalLayout();
            c.getQuestions().forEach(questions::addComponent);
            HorizontalLayout hl = new HorizontalLayout();
            if (!isFirst) {
                Button previousPageButton = new Button("Previous");
                previousPageButton.addClickListener(e -> previousPage(tabSheet, questions));
                hl.addComponent(previousPageButton);
            }
            if (tabs.hasNext()) {
                Button nextPageButton = new Button("Next");
                nextPageButton.addClickListener(e -> nextPage(tabSheet, questions));
                hl.addComponent(nextPageButton);
            } else {
                Button finishButton = new Button("Finish");
                finishButton.addClickListener(e -> {
                    Notification notification = new Notification("Success");
                    notification.show(getPage());
                    enterEditorMode();
                });
                hl.addComponent(finishButton);
            }
            questions.addComponent(hl);
            tabSheet.addTab(questions, c.getCaption());
            isFirst = false;
        }
        previewModeLayout.addComponent(tabSheet);
        Button editorModeButton = new Button("Editor");
        editorModeButton.addClickListener(e -> enterEditorMode());
        previewModeLayout.addComponent(editorModeButton);
        setContent(previewModeLayout);
    }

    private void previousPage(TabSheet tabSheet, VerticalLayout questions) {
        tabSheet.setSelectedTab(tabSheet.getTabPosition(tabSheet.getTab(questions)) - 1);
    }

    private void nextPage(TabSheet tabSheet, VerticalLayout questions) {
        tabSheet.setSelectedTab(tabSheet.getTabPosition(tabSheet.getTab(questions)) + 1);
    }

    private void enterEditorMode() {
        setContent(editorLayout);
    }
}
