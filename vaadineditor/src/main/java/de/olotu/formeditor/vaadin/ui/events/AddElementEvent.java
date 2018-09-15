package de.olotu.formeditor.vaadin.ui.events;

import com.vaadin.ui.Component;
import de.olotu.formeditor.vaadin.ui.components.CustomFormComponent;

/**
 * Created by kevin on 19/09/2017.
 */
public class AddElementEvent extends Component.Event {

    private CustomFormComponent source;

    public AddElementEvent(CustomFormComponent source) {
        super(source);
        this.source = source;
    }

    @Override
    public CustomFormComponent getComponent() {
        return source;
    }
}
