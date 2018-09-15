package de.olotu.formeditor.vaadin.ui.components;

import com.vaadin.ui.*;
import de.olotu.formeditor.vaadin.ui.events.ChangeTabCaptionListener;

/**
 * Created by kevin on 30/10/2017.
 */
public class FormTabSheet extends TabSheet {
    private String name;

    public FormTabSheet() {
        VerticalLayout vl = new VerticalLayout();
        vl.setCaption(" + ");
        addTab(vl);
        addSelectedTabChangeListener(e -> {
            if (getSelectedTab().equals(vl)) {
                FormTab newTab = new FormTab(this);
                addTab(newTab);
                setSelectedTab(getTab(newTab));
            }
        });
        FormTab firstTab = new FormTab(this);
        addTab(firstTab);
        setSelectedTab(getTab(firstTab));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
