package de.olotu.formeditor.vaadin.ui.utils;

/**
 * Created by kevin on 31/10/2017.
 */
public class LoopCounter {
    private int i = 0;

    public void increment() {
        i = i + 1;
    }

    public int getCount() {
        return i;
    }
}
