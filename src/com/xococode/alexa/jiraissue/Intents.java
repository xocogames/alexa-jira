package com.xococode.alexa.jiraissue;

public final class Intents {

    private Intents() {
    }

    /* Nuestro Intent creado */
    public static final String LAUNCH_INTENT = "LaunchIntent";
    public static final String RESTART_INTENT = "RestartIntent";
    public static final String CONFIRMOK_INTENT = "ConfirmOkIntent";
    public static final String CONFIRMKO_INTENT = "ConfirmKoIntent";
    public static final String CONFIRMERROR_INTENT = "ConfirmErrorIntent";
    public static final String MATCHPROJECT_INTENT = "MatchProjectIntent";
    public static final String MATCHSUBJECT_INTENT = "MatchSubjectIntent";


    /* Intents provided by Amazon */
    public static final String AMAZON_HELP = "AMAZON.HelpIntent";
    public static final String AMAZON_STOP = "AMAZON.StopIntent";
    public static final String AMAZON_CANCEL = "AMAZON.CancelIntent";
    public static final String AMAZON_REPEAT = "AMAZON.RepeatIntent";
    public static final String AMAZON_YES = "AMAZON.YesIntent";
    public static final String AMAZON_NO = "AMAZON.NoIntent";
    public static final String AMAZON_FALLBACK = "AMAZON.FallbackIntent";

}
