package com.xococode.alexa.jiraissue;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.xococode.alexa.jiraissue.intents.*;
import com.xococode.alexa.jiraissue.intents.workintents.ConfirmOkIntent;
import com.xococode.alexa.jiraissue.intents.workintents.MatchSubjectIntent;
import com.xococode.alexa.jiraissue.intents.workintents.MatchProjectIntent;
import com.xococode.alexa.jiraissue.intents.workintents.LaunchIntent;

import java.io.InputStream;
import java.util.Properties;

/**
 * Esta es la clase principal para cuando se suba a Amazon Lambda, debemos indicar esta clase como la clase principal
 * */
public class MainStreamHandler extends SkillStreamHandler {

    // private static final String DYNAMODB_NAME = "SiraCumple";

    private static final String INTENTS_MESSAGES_RESOURCE = "intents/IntentsMessages.properties";

    private static Skill getSkill() {

        return Skills.standard()
                .addRequestHandlers(
                        new LaunchIntent(Intents.LAUNCH_INTENT),
                        new ConfirmOkIntent(Intents.CONFIRMOK_INTENT),
                        new MatchProjectIntent(Intents.MATCHPROJECT_INTENT),
                        new MatchSubjectIntent(Intents.MATCHSUBJECT_INTENT),
                        new RestartIntent(Intents.RESTART_INTENT),
                        new CancelAndStopIntent(),
                        new HelpIntent(),
                        new SessionEndedIntent(),
                        new RepeatIntent(),
                        new NoIntent(),
                        new UnknownIntent(Intents.AMAZON_FALLBACK))
                // .withTableName(DYNAMODB_NAME)
                // .withAutoCreateTable(true)
                .build();
    }

    /*
    public static Map<String, Object> getPersistentAttributes(HandlerInput handlerInput)
    {
        return handlerInput.getAttributesManager().getPersistentAttributes();
    }

    public static void savePersistentAttributes(HandlerInput handlerInput, Map<String, Object> persistentAttributes)
    {
        handlerInput.getAttributesManager().setPersistentAttributes(persistentAttributes);
        handlerInput.getAttributesManager().savePersistentAttributes();
    }

    public static void clearPersistentAttributes(HandlerInput handlerInput)
    {
        // handlerInput.getAttributesManager().deletePersistentAttributes();
        handlerInput.getAttributesManager().getPersistentAttributes().clear();
        handlerInput.getAttributesManager().savePersistentAttributes();
    }
    */

    public static String getIntentMessage(String intent, String key, String def)
    {
        String val = getIntentMessage(intent, key);
        if (val == null || val.trim().length() == 0) return def;
        return val;
    }

    public static String getIntentMessage(String intent, String key)
    {
        Properties props = readIntentsMessagesProperties();
        return props.getProperty(intent +"/"+ key);
    }

    private static Properties readIntentsMessagesProperties()
    {
        Properties props = new Properties();

        try {
            InputStream input = MainStreamHandler.class.getResourceAsStream(INTENTS_MESSAGES_RESOURCE);
            props.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("No se ha cargadp el fichero de recursos: "+ INTENTS_MESSAGES_RESOURCE, ex);
        }

        return props;
    }

    public MainStreamHandler() {
        super(getSkill());

    }
}