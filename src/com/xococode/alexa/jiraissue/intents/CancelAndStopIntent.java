package com.xococode.alexa.jiraissue.intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.xococode.alexa.jiraissue.Intents;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * Se lanza cuando decimos "Alexa detente" o "Alexa para"
 * */
public class CancelAndStopIntent implements RequestHandler {

    private static String TEXT_GOODBY_CARD_TITLE = "Adeu";
    private static String TEXT_GOODBY = "Doncs vale, doncs adeu, eh!";

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(Intents.AMAZON_STOP).or(intentName(Intents.AMAZON_CANCEL)));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech(TEXT_GOODBY)
                .withSimpleCard(TEXT_GOODBY_CARD_TITLE, TEXT_GOODBY)
                .build();
    }
}
