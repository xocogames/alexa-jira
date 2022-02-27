package com.xococode.alexa.jiraissue.intents.workintents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.xococode.alexa.jiraissue.MainStreamHandler;
import com.xococode.alexa.jiraissue.intents.BaseIntent;

import java.util.Map;
import java.util.Optional;


public class MatchSubjectIntent extends BaseIntent {

    public MatchSubjectIntent(String intent) {
        super(intent);
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        boolean manage = handlerInput.matches(Predicates.intentName(intent));
        if (manage)
        {
            // return checkPersistentAttributeStep(handlerInput, Intents.YANOUSOCABALLOS_INTENT);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Map<String, Slot> mapSlots = getSlots(handlerInput);
        String jiraIssueSubject = (String) mapSlots.get(SLOT_PHRASE).getValue();

        String jiraPrj = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_PRJ);
        String jiraIssueType = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_ISSUE_TYPE);

        Map<String, Object> mapSessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        mapSessionAttributes.put(SLOT_PHRASE, jiraIssueSubject);
        handlerInput.getAttributesManager().setSessionAttributes(mapSessionAttributes);

        String speech = MainStreamHandler.getIntentMessage(intent, "speechText");
        String cardTitle = MainStreamHandler.getIntentMessage(intent, "cardTitle");

        speech = String.format(speech, jiraPrj, jiraIssueType, jiraIssueSubject);

        // MainStreamHandler.clearPersistentAttributes(handlerInput);

        return handlerInput.getResponseBuilder()
                .withSpeech(speech)
                .withSimpleCard(cardTitle, speech)
                .withShouldEndSession(false)
                .build();
    }
}
