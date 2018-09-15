package de.olotu.formeditor.vaadin.ui.elements;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

/**
 * Created by kevin on 31/10/2017.
 */
public class HorizontalSeparator extends Label {

    public HorizontalSeparator() {
        super("<hr/>", ContentMode.HTML);
        setWidth("100%");
    }


}
