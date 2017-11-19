package no.pk.model;

import java.util.HashMap;

public class Setting {
    public static String AUTHENTICATE = "authenticate", USER_NAME = "username", PASSWORD = "password", PUSH_BULLET = "pushbullet", PUSH_BULLET_API_KEY = "pushbullet_api", DEVICE_NAME = "device_name",
            API_KEY = "api_key", PUSHALOT = "pushalot", PUSHALOT_API_KEY = "pushalot_api_key", PUSHOVER = "pushover", PUSHOVER_APP_TOKEN = "pushoverAppToken",
            PUSHOVER_USER_TOKEN = "pushoverUserToken", VOICE_RECOGNITION = "voiceRecognition", SEND_VOICE_RESPONSE_AS_NOTIFICATION = "voiceResponseNotification", SEND_VOICE_RESPONSE_AS_VOICE = "voiceResponse";

    public String name;
    public String value;

//    public static Finder<String, Setting> find = new Finder<>(Setting.class);
    public static HashMap<String, Setting> find = new HashMap<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String get(String key) {
        Setting setting = find.get(key);
        if (setting != null) {
            return setting.value;
        } else {
            return "";
        }
    }

    public static void set(String key, String value) {
        Setting setting = find.get(key);
        if (setting == null) {
            setting = new Setting();
            setting.setName(key);
        }

        setting.setValue(value);
//        setting.save();
    }
}
