package de.olotu.formeditor.commandline;

import de.olotu.formeditor.service.FormEditorService;
import de.olotu.formeditor.service.FormEditorServiceImpl;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by kevin on 13/08/2017.
 */
public class Main {

    private static final FormEditorService service = new FormEditorServiceImpl();

    private static final Scanner scanner = new Scanner(System.in);

    private static String form;

    public static void main(String... args) throws IOException {
        System.out.println("Initializing FormEditorService");
        System.out.println("Enter form title: ");
        String formTitle = scanner.next();
        System.out.println("Form title: " + formTitle);
        form = service.createNewForm(formTitle);
        System.out.println("Current form: " + form);
        System.out.println("");
        boolean done = false;
        while (!done) {
            String input = scanner.next();
            done = readInput(input, form);
        }
    }

    private static boolean readInput(String input, String formJson) {
        if ("save".equals(input)) {
            service.save(formJson);
            return true;
        }
        if ("add".equals(input)) {
            System.out.println("Enter component type: ");
            String component = scanner.next();
            System.out.println("Enter component name: ");
            String name = scanner.next();
            service.addComponent(component, name);
        }
        if ("remove".equals(input)) {
            System.out.println("Enter name of component to be removed: ");
            String name = scanner.next();
            service.removeComponent(name);
        }
        return false;
    }
}
