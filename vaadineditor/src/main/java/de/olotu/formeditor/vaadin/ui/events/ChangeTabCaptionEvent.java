package de.olotu.formeditor.vaadin.ui.events;

import com.vaadin.ui.Component;

/**
 * Created by kevin on 31/10/2017.
 */
public class ChangeTabCaptionEvent extends Component.Event {

    private String newCaption;

    public ChangeTabCaptionEvent(Component source, String newCaption) {
        super(source);
        this.newCaption = newCaption;
    }

    public String getNewCaption() {
        return this.newCaption;
    }
}
