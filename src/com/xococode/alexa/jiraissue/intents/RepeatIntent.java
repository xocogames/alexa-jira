package com.xococode.alexa.jiraissue.intents;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.xococode.alexa.jiraissue.Intents;

import java.util.Map;
import java.util.Optional;


public class RepeatIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        boolean handle = handlerInput.matches(Predicates.intentName(Intents.AMAZON_REPEAT));
        if (handle)
        {
            Request request = handlerInput.getRequestEnvelope().getRequest();
            AttributesManager attributesManager = handlerInput.getAttributesManager();
            Map<String,Object> attributes = attributesManager.getSessionAttributes();

            return  (attributes.containsKey("tabla") && attributes.containsKey("num"));
        }
        return false;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        AttributesManager attributesManager = handlerInput.getAttributesManager();
        Map<String,Object> attributes = attributesManager.getSessionAttributes();

        int tabla = (int)attributes.get("tabla");
        int num = (int)attributes.get("num");

        return handlerInput.getResponseBuilder()
                .withSpeech("Que pesaooo!!! T´he preguntat " + tabla + " por " + num)
                .withSimpleCard("Repito", "¿Cuanto es " + tabla + " por " + num + "?")
                .withShouldEndSession(false)
                .build();
    }
}
