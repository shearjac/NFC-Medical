package com.example.nfcmedical;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    //Variables
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static String IS_LOGIN = "IsLoggedIn";

    public static String KEY_EMAIL = "email";
    public static String KEY_FIRST_NAME = "firstName";
    public static String KEY_LAST_NAME = "last_name";
    public static String KEY_DATE = "date";

    public SessionManager(Context context) {
        this.context = context;
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(String email, String firstName, String lastName, String date) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.putString(KEY_LAST_NAME, lastName);
        editor.putString(KEY_DATE, date);

        editor.commit();
    }

    public HashMap<String, String> getUserDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
        userData.put(KEY_FIRST_NAME, userSession.getString(KEY_FIRST_NAME, null));
        userData.put(KEY_LAST_NAME, userSession.getString(KEY_LAST_NAME, null));
        userData.put(KEY_DATE, userSession.getString(KEY_DATE, null));

        return userData;
    }

    public boolean checkLogin() {
        if (userSession.getBoolean(IS_LOGIN, false)) {//2nd param doesnt matter
            return true;
        } else {
            return false;
        }
    }

    public void logoutSession() {
        editor.clear();
        editor.commit();
    }

}
