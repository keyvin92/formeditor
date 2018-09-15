package de.olotu.formeditor.vaadin.ui.events;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import de.olotu.formeditor.vaadin.ui.components.FormTab;
import de.olotu.formeditor.vaadin.ui.components.FormTabSheet;

/**
 * Created by kevin on 31/10/2017.
 */
public class ChangeTabCaptionListener extends CustomComponent implements Component.Listener {

    private FormTabSheet tabsheet;

    public ChangeTabCaptionListener(FormTabSheet tabSheet) {
        this.tabsheet = tabSheet;
    }
    @Override
    public void componentEvent(Event event) {
        if (event instanceof ChangeTabCaptionEvent) {
            ChangeTabCaptionEvent tabCaptionEvent = (ChangeTabCaptionEvent) event;
            FormTab tab = (FormTab)event.getSource();
            tab.setCaption(tabCaptionEvent.getNewCaption());
            tabsheet.getTab(tab).setCaption(tabCaptionEvent.getNewCaption());
        }
    }
}
