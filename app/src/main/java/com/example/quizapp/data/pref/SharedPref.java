package com.example.quizapp.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences prefs;

    public SharedPref(Context context){
        prefs=context.getSharedPreferences("Prefs",Context.MODE_PRIVATE);
    }
    public void setTheme(int value){
        prefs.edit().putInt("Theme",value).apply();
    }
    public int getTheme(int defaultValue){
        return prefs.getInt("Theme",defaultValue);
    }
    public void setChecked(boolean bool){
        prefs.edit().putBoolean("Check",bool).apply();
    }
    public boolean getChecked(boolean defBool){
        return prefs.getBoolean("Check",defBool);
    }

}
