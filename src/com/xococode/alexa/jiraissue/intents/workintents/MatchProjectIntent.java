package com.xococode.alexa.jiraissue.intents.workintents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.xococode.alexa.jiraissue.MainStreamHandler;
import com.xococode.alexa.jiraissue.intents.BaseIntent;

import java.util.Map;
import java.util.Optional;


public class MatchProjectIntent extends BaseIntent {

    public MatchProjectIntent(String intent) {
        super(intent);
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        boolean manage = handlerInput.matches(Predicates.intentName(intent));
        if (manage)
        {
            // return checkPersistentAttributeStep(handlerInput, Intents.SINMISOLAMILADO_INTENT);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Map<String, Slot> mapSlots = getSlots(handlerInput);
        String jiraPrj = (String) mapSlots.get(SLOT_PRJ).getValue();
        String jiraIssueType = (String) mapSlots.get(SLOT_ISSUE_TYPE).getValue();

        Map<String, Object> mapSessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        mapSessionAttributes.put(SLOT_PRJ, normalizeJiraPrj(jiraPrj));
        mapSessionAttributes.put(SLOT_ISSUE_TYPE, normalizeJiraIssueType(jiraIssueType));
        handlerInput.getAttributesManager().setSessionAttributes(mapSessionAttributes);

        // jiraPrj = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_PRJ);
        // jiraIssueType = (String) handlerInput.getAttributesManager().getSessionAttributes().get(SLOT_ISSUE_TYPE);

        String speech = MainStreamHandler.getIntentMessage(intent, "speechText");
        String cardTitle = MainStreamHandler.getIntentMessage(intent, "cardTitle");

        speech = String.format(speech, jiraPrj, jiraIssueType);

        // savePersistentAttributeStep(handlerInput);

        return handlerInput.getResponseBuilder()
                .withSpeech(speech)
                .withSimpleCard(cardTitle, speech)
                .withShouldEndSession(false)
                .build();
    }

    private String normalizeJiraPrj(String jiraPrj) {
        jiraPrj = jiraPrj.toLowerCase();

        if (jiraPrj.startsWith("jota e")) return "LJEE";
        if (jiraPrj.startsWith("jota dos")) return "LJEE";
        if (jiraPrj.startsWith("jota 2")) return "LJEE";
        if (jiraPrj.startsWith("jota ee")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("dos")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("2")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("ee")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("e e")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("e.e")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("enterprise")) return "LJEE";

        if (jiraPrj.startsWith("jota ese")) return "LSEE";
        if (jiraPrj.startsWith("jota es")) return "LSEE";
        if (jiraPrj.startsWith("jota se")) return "LSEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("estandar")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("standard")) return "LJEE";
        if (jiraPrj.contains("java") && jiraPrj.contains("standar")) return "LJEE";

        if (jiraPrj.startsWith("de be")) return "DB";
        if (jiraPrj.contains("base") && jiraPrj.contains("datos")) return "DB";
        if (jiraPrj.contains("data") && jiraPrj.contains("base")) return "DB";

        return null;
    }

    private String normalizeJiraIssueType(String jiraIssueType) {
        jiraIssueType = jiraIssueType.toLowerCase();

        if (jiraIssueType.startsWith("bug")) return "BUG";
        return null;
    }
}
