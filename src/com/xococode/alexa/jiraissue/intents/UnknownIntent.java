package com.xococode.alexa.jiraissue.intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.xococode.alexa.jiraissue.MainStreamHandler;

import java.util.Optional;

public class UnknownIntent extends BaseIntent {

    public UnknownIntent(String refIntent) {
        super(refIntent);
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        String speech = MainStreamHandler.getIntentMessage(intent, "speechText");
        String cardTitle = MainStreamHandler.getIntentMessage(intent, "cardTitle");

        /*
        Map<String, Object> attribs = getPersistentAttributes(handlerInput);
        if (attribs.containsKey(ATTRIB_STEP))
        {
            speech += ". "+ attribs.get(ATTRIB_STEP);
        }
        else
        {
            speech += ". "+ "Y sin pistas...";
        }
        */

        return handlerInput.getResponseBuilder()
                .withSpeech(speech)
                .withSimpleCard(cardTitle, speech)
                .withShouldEndSession(false)
                .build();
    }
}
