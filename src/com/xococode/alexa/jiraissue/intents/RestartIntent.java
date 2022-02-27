package com.xococode.alexa.jiraissue.intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.xococode.alexa.jiraissue.MainStreamHandler;

import java.util.Optional;


public class RestartIntent extends BaseIntent {

    public RestartIntent(String intent) {
        super(intent);
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(intent));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        // MainStreamHandler.clearPersistentAttributes(handlerInput);

        String speech = MainStreamHandler.getIntentMessage(intent, "speechText");
        String cardTitle = MainStreamHandler.getIntentMessage(intent, "cardTitle");

        return handlerInput.getResponseBuilder()
                .withSpeech(speech)
                .withSimpleCard(cardTitle, speech)
                .build();
    }
}
