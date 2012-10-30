package myamamic.tp.system;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceSettings {
    private static final String PREFS_REBOOTTP = "RebootTP_Preference";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_ENABLEFLASG = "rebootcycle_enableflag";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_ENABLELIMIT = "rebootcycle_enablelimit";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_LIMITCOUNT = "rebootcycle_limitcount";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_REBOOTEDCOUNT = "rebootcycle_rebootedcount";

    private static final int REPEAT_COUNT_LIMIT = 300;

    static public boolean getRebootCycleEnableFlag(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        boolean enableflag = settings.getBoolean(PREFS_REBOOTTP_REBOOTCYCLE_ENABLEFLASG, false);
        return enableflag;
    }

    static public void saveRebootCycleEnableFlag(Context context, boolean enable) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PREFS_REBOOTTP_REBOOTCYCLE_ENABLEFLASG, enable);
        editor.commit();
    }

    static public boolean getRebootCycleEnableLimit(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        boolean enableflag = settings.getBoolean(PREFS_REBOOTTP_REBOOTCYCLE_ENABLELIMIT, false);
        return enableflag;
    }

    static public void saveRebootCycleEnableLimit(Context context, boolean enable) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PREFS_REBOOTTP_REBOOTCYCLE_ENABLELIMIT, enable);
        editor.commit();
    }

    static public int getRebootCycleLimitCount(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        int limitCount = settings.getInt(PREFS_REBOOTTP_REBOOTCYCLE_LIMITCOUNT, REPEAT_COUNT_LIMIT);
        return limitCount;
    }

    static public void saveRebootCycleLimitCount(Context context, int count) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(PREFS_REBOOTTP_REBOOTCYCLE_LIMITCOUNT, count);
        editor.commit();
    }

    static public int getRebootedCount(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        int rebootedCount = settings.getInt(PREFS_REBOOTTP_REBOOTCYCLE_REBOOTEDCOUNT, 0);
        return rebootedCount;
    }

    static public void saveRebootedCount(Context context, int count) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(PREFS_REBOOTTP_REBOOTCYCLE_REBOOTEDCOUNT, count);
        editor.commit();
    }
}
