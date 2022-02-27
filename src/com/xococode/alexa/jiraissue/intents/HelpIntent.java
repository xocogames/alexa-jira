package com.xococode.alexa.jiraissue.intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.xococode.alexa.jiraissue.Intents;

import java.util.Optional;

/**
 * Se lanza cuando decimos "ayuda" o "ayudame"
 * */
public class HelpIntent implements RequestHandler {

    private static String SPEECH_TEXT = "Amb Multiplica només has de dir, per exemple: pregunta´m la taula del 4";
    private static  String CARD_TEXT = "Multiplica";

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(Intents.AMAZON_HELP));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech(SPEECH_TEXT)
                .withSimpleCard("Calculadora", SPEECH_TEXT)
                .withReprompt(SPEECH_TEXT)
                .build();
    }
}
