package myamamic.tp.system;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceSettings {
    private static final String PREFS_REBOOTTP = "RebootTP_Preference";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_ENABLEFLASG = "rebootcycle_enableflag";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_ENABLELIMIT = "rebootcycle_enablelimit";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_LIMITCOUNT = "rebootcycle_limitcount";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_REBOOTEDCOUNT = "rebootcycle_rebootedcount";
    private static final String PREFS_REBOOTTP_REBOOTCYCLE_INTERVAL_MSEC = "rebootcycle_interval_msec";

    private static final int REPEAT_COUNT_LIMIT = 300;
    private static final int MIN_REBOOT_INTERVAL = 60*1*1000; // 1 minute.
    private static final int MAX_REBOOT_INTERVAL = 60*60*1*1000; // 1 hour.

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

    /*
     * Reboot cycle interval (ms)
     */
    static public int getRebootInterval(Context context) {
        int interval = getValue(context, PREFS_REBOOTTP_REBOOTCYCLE_INTERVAL_MSEC, MIN_REBOOT_INTERVAL);
        if (interval < MIN_REBOOT_INTERVAL) {
            interval = MIN_REBOOT_INTERVAL;
        } else if (MAX_REBOOT_INTERVAL < interval) {
            interval = MAX_REBOOT_INTERVAL;
        }
        return interval;
    }
    static public void putRebootInterval(Context context, int interval) {
        putValue(context, PREFS_REBOOTTP_REBOOTCYCLE_INTERVAL_MSEC, interval);
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
