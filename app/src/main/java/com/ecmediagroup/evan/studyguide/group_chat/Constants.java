package com.ecmediagroup.evan.studyguide.group_chat;

/**
 * Created by GleasonK on 6/8/15.
 *
 * Constants used by this chatting application.
 * TODO: Replace PUBLISH_KEY and SUBSCRIBE_KEY with your personal keys.
 * TODO: Register app for GCM and replace GCM_SENDER_ID
 */
public class Constants {
    public static final String PUBLISH_KEY   = "pub-c-f64c471f-8cc9-43db-b670-4fc1c7ccf5eb";
    public static final String SUBSCRIBE_KEY = "sub-c-226698d2-3aac-11e7-b860-02ee2ddab7fe";

    public static final String CHAT_PREFS    = "com.example.evan.comp296.SHARED_PREFS";
    public static final String CHAT_USERNAME = "com.example.evan.comp296.SHARED_PREFS.USERNAME";
    public static final String CHAT_ROOM     = "com.example.evan.comp296.CHAT_ROOM";

    public static final String JSON_GROUP = "groupMessage";
    public static final String JSON_DM    = "directMessage";
    public static final String JSON_USER  = "chatUser";
    public static final String JSON_MSG   = "chatMsg";
    public static final String JSON_TIME  = "chatTime";

    public static final String STATE_LOGIN = "loginTime";

    public static final String GCM_REG_ID    = "gcmRegId";
    public static final String GCM_SENDER_ID = "616707699367"; // Get this from
    public static final String GCM_POKE_FROM = "gcmPokeFrom"; // Get this from
    public static final String GCM_CHAT_ROOM = "gcmChatRoom"; // Get this from
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
}
