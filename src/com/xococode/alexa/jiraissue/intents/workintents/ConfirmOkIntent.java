package com.xococode.alexa.jiraissue.intents.workintents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.xococode.alexa.jiraissue.MainStreamHandler;
import com.xococode.alexa.jiraissue.intents.BaseIntent;

import java.util.Map;
import java.util.Optional;


public class ConfirmOkIntent extends BaseIntent {

    public ConfirmOkIntent(String intent) {
        super(intent);
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        String jiraPrj = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_PRJ);
        String jiraIssueType = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_ISSUE_TYPE);
        String jiraIssueSubject = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_PHRASE);

        if (jiraPrj == null || jiraIssueType == null || jiraIssueSubject == null) return false;

        return handlerInput.matches(Predicates.intentName(intent));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        String jiraPrj = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_PRJ);
        String jiraIssueType = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_ISSUE_TYPE);
        String jiraIssueSubject = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_PHRASE);

        String speech = MainStreamHandler.getIntentMessage(intent, "speechText");
        String cardTitle = MainStreamHandler.getIntentMessage(intent, "cardTitle");

        // savePersistentAttributeStep(handlerInput);

        return handlerInput.getResponseBuilder()
                .withSpeech(speech)
                .withSimpleCard(cardTitle, speech)
                .build();
    }
}
