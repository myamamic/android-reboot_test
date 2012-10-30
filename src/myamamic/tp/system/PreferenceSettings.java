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

    /*
     * Reboot cycle flag
     */
    static public boolean getRebootCycleEnableFlag(Context context) {
        return getValue(context, PREFS_REBOOTTP_REBOOTCYCLE_ENABLEFLASG, false);
    }
    static public void saveRebootCycleEnableFlag(Context context, boolean enable) {
        putValue(context, PREFS_REBOOTTP_REBOOTCYCLE_ENABLEFLASG, enable);
    }

    /*
     * Reboot limit flag
     */
    static public boolean getRebootCycleEnableLimit(Context context) {
        return getValue(context, PREFS_REBOOTTP_REBOOTCYCLE_ENABLELIMIT, false);
    }
    static public void saveRebootCycleEnableLimit(Context context, boolean enable) {
        putValue(context, PREFS_REBOOTTP_REBOOTCYCLE_ENABLELIMIT, enable);
    }

    /*
     * Reboot limit count
     */
    static public int getRebootCycleLimitCount(Context context) {
        return getValue(context, PREFS_REBOOTTP_REBOOTCYCLE_LIMITCOUNT, REPEAT_COUNT_LIMIT);
    }
    static public void saveRebootCycleLimitCount(Context context, int count) {
        putValue(context, PREFS_REBOOTTP_REBOOTCYCLE_LIMITCOUNT, count);
    }

    /*
     * Rebooted count
     */
    static public int getRebootedCount(Context context) {
        return getValue(context, PREFS_REBOOTTP_REBOOTCYCLE_REBOOTEDCOUNT, 0);
    }
    static public void saveRebootedCount(Context context, int count) {
        putValue(context, PREFS_REBOOTTP_REBOOTCYCLE_REBOOTEDCOUNT, count);
    }

    ////////////////////////////////////////////////////////////////////////
    //// Private methods
    ////////////////////////////////////////////////////////////////////////
    /*
     * boolean
     */
    static private boolean getValue(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
    static private void putValue(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /*
     * int
     */
    static private int getValue(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }
    static private void putValue(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_REBOOTTP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
