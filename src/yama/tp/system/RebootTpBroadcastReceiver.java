package yama.tp.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RebootTpBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "RebootTPBroadcastReceiver";

    /**
     * Sets alarm on ACTION_BOOT_COMPLETED. 
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            boolean rebootcycle_enableflag = PreferenceSettings.getRebootCycleEnableFlag(context);
            Log.d(TAG, "onReceive(): rebootcycle_enableflag is " + rebootcycle_enableflag);

            if (rebootcycle_enableflag) {
                int rebootedCount = PreferenceSettings.getRebootedCount(context);
                rebootedCount++;
                PreferenceSettings.saveRebootedCount(context, rebootedCount);
                Log.d(TAG, "onReceive(): ACTION_BOOT_COMPLETED: " + rebootedCount + " rebooted.");

                // Lunch RebootTP activity
                Intent i = new Intent(context, RebootTPActivity.class);
                i.setAction(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }
}
