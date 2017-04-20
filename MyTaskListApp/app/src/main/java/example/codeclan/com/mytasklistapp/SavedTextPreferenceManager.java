package example.codeclan.com.mytasklistapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 20/04/2017.
 */

public class SavedTextPreferenceManager {
    private static final String PREF_SAVEDTEXT = "savedText";

    public static void setStoredText(Context context, String text){
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SAVEDTEXT, text)
                .apply();

    }

    public static String getStoredText(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PREF_SAVEDTEXT, null);
    }
}