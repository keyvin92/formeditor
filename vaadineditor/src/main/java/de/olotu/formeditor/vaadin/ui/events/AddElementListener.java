package de.olotu.formeditor.vaadin.ui.events;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import de.olotu.formeditor.vaadin.ui.components.FormTab;

/**
 * Created by kevin on 19/09/2017.
 */
public class AddElementListener extends CustomComponent implements Component.Listener {

    private FormTab tab;

    public AddElementListener(FormTab tab) {
        this.tab = tab;
    }

    @Override
    public void componentEvent(Event event) {
        if (event instanceof AddElementEvent) {
            AddElementEvent addElementEvent = (AddElementEvent) event;
            tab.addQuestion(addElementEvent.getComponent());
        }
    }
}
