package de.olotu.formeditor.vaadin.ui.elements;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.olotu.formeditor.vaadin.ui.components.FormTabSheet;
import de.olotu.formeditor.vaadin.ui.serializable.Questionnaire;
import de.olotu.formeditor.vaadin.ui.serializer.GsonSerializer;
import de.olotu.formeditor.vaadin.ui.utils.FormTabSheetQuestionnaireMapper;

/**
 * Created by kevin on 31/10/2017.
 */
public class SaveQuestionnaireButton extends Button {

    public SaveQuestionnaireButton(FormTabSheet tabSheet, String caption) {
        super(caption);
        addClickListener(e -> {
            Questionnaire questionnaire = FormTabSheetQuestionnaireMapper.toQuestionnaire(tabSheet);
            System.out.print(new GsonSerializer().serialize(questionnaire));
            Notification notification = new Notification("Success");
            notification.show(getUI().getPage());
        });
    }



}
