package com.xococode.alexa.jiraissue.intents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Slot;

import java.util.Map;

public abstract class BaseIntent implements RequestHandler {

    protected static final String SLOT_PRJ = "PRJ";
    protected static final String SLOT_ISSUE_TYPE = "ISSUE_TYPE";
    protected static final String SLOT_PHRASE = "phrase";

    protected String intent;

    protected static final String ATTRIB_STEP = "step";

    public BaseIntent(String intent)
    {
        this.intent = intent;
    }

    protected Map<String, Slot> getSlots(HandlerInput handlerInput) {
        IntentRequest intentRequest = (IntentRequest) handlerInput.getRequest();
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> mapSlots = intent.getSlots();
        return mapSlots;
    }

    public void savePersistentAttributeStep(HandlerInput handlerInput)
    {
        // Map<String, Object> attribs = getPersistentAttributes(handlerInput);
        // attribs.put(ATTRIB_STEP, intent);
        // savePersistentAttributes(handlerInput, attribs);
    }

    public boolean checkPersistentAttributeStep(HandlerInput handlerInput, String stepCheck)
    {
        // Map<String, Object> attribs = getPersistentAttributes(handlerInput);
        // if (attribs.containsKey(ATTRIB_STEP) && attribs.get(ATTRIB_STEP).equals(stepCheck)) return true;
        return false;
    }

    /*
    public Map<String, Object> getPersistentAttributes(HandlerInput handlerInput)
    {
        return MainStreamHandler.getPersistentAttributes(handlerInput);
    }

    public void savePersistentAttributes(HandlerInput handlerInput, Map<String, Object> persistentAttributes)
    {
        MainStreamHandler.savePersistentAttributes(handlerInput, persistentAttributes);
    }

    public void clearPersistentAttributes(HandlerInput handlerInput)
    {
        MainStreamHandler.clearPersistentAttributes(handlerInput);
    }
    */
}
