package br.com.thiengo.jaguarapp.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by viniciusthiengo on 27/02/17.
 */

public class SPLocalBase {
    private static final String PREF = "PREFERENCES";
    private static final String TIME_KEY = "time";
    private static final String VERSION_KEY = "version";
    private static final long DELAY = 24*60*60*1000;

    private static void saveTime(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putLong(TIME_KEY, System.currentTimeMillis() + DELAY).apply();
    }

    public static boolean is24hrsDelayed(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        Long time = sp.getLong(TIME_KEY, 0);
        Long timeCompare = System.currentTimeMillis();

        if( time < timeCompare ){
            saveTime(context);
            return true;
        }
        return false;
    }

    public static void saveVersion(Context context, int version){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putInt(VERSION_KEY, version).apply();
    }

    public static int getVersion(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getInt(VERSION_KEY, 0);
    }
}
