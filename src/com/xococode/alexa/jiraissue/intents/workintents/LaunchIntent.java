package com.xococode.alexa.jiraissue.intents.workintents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.xococode.alexa.jiraissue.MainStreamHandler;
import com.xococode.alexa.jiraissue.intents.BaseIntent;

import java.util.Map;
import java.util.Optional;

/**
 * Se lanza cuando decimos "Alexa, crea issue"
 * */
public class LaunchIntent extends BaseIntent {
    public LaunchIntent(String intent) {
        super(intent);
    }

    // private static String  URL_AUDIO_INTRO = "https://alexamio.s3-eu-west-1.amazonaws.com/audio-storm01.mp3";

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        // Map<String, Object> attribsPersistens = MainStreamHandler.getPersistentAttributes(handlerInput);
        // if (!attribsPersistens.containsKey("intro")) return handleIntro(handlerInput);
        // else return handleClues(handlerInput);

        return handleIntro(handlerInput);
    }

    private Optional<Response> handleIntro(HandlerInput handlerInput)
    {
        /*
        Map<String, Object> persistentAttributes = MainStreamHandler.getPersistentAttributes(handlerInput);
        if(!persistentAttributes.containsKey("intro"))
        {
            persistentAttributes.put("intro", true);
            MainStreamHandler.savePersistentAttributes(handlerInput, persistentAttributes);
        }
        */

        String speech = MainStreamHandler.getIntentMessage(intent, "speechText");
        String cardTitle = MainStreamHandler.getIntentMessage(intent, "cardTitle");

        return handlerInput.getResponseBuilder()
                // .addAudioPlayerPlayDirective(PlayBehavior.REPLACE_ALL, 0l, null, "audioSkill", URL_AUDIO_INTRO)
                .withSpeech(speech)
                .withSimpleCard(cardTitle, speech)
                .withShouldEndSession(false)
                .build();
    }

    private Optional<Response> handleClues(HandlerInput handlerInput)
    {
        String speech = MainStreamHandler.getIntentMessage(intent, "speechExpectedClue");
        String cardTitle = MainStreamHandler.getIntentMessage(intent, "cardTitle");

        return handlerInput.getResponseBuilder()
                .withSpeech(speech)
                .withSimpleCard(cardTitle, speech)
                .withShouldEndSession(false)
                .build();
    }
}
