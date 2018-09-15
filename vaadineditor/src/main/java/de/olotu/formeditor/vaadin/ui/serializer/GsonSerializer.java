package de.olotu.formeditor.vaadin.ui.serializer;

import com.google.gson.Gson;
import de.olotu.formeditor.vaadin.ui.serializable.Questionnaire;

/**
 * Created by kevin on 31/10/2017.
 */
public class GsonSerializer implements QuestionnaireSerializer {
    @Override
    public String serialize(Questionnaire questionnaire) {
        return new Gson().toJson(questionnaire);
    }
}
