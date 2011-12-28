package yama.tp.system;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class RebootTPActivity extends Activity {
    private static final String TAG = "RebootTP";

    private Button mStartRebootCycleButton = null;
    private Button mStopRebootCycleButton = null;
    private CheckBox mRepeatCountLimitCheck = null;
    private EditText mRepeatCountLimitEdit = null;
    private TextView mRebootedCountText = null;
    private int mRebootedCount = 0;

    private RebootTimer mRebootTimer = new RebootTimer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeWidget();

        // Is this activity is launched from BroadcastReceiver ?
        if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
            Log.d(TAG, "onCreate() : Start from BroadcastReceiver.");
            mRebootTimer.rebootAfterTime(1*60*1000); // Reboot after 3 minutes
        }
    }

    private void initializeWidget() {
        mStartRebootCycleButton = (Button)findViewById(R.id.button_rebootcycle_start);
        mStartRebootCycleButton.setOnClickListener(mStartRebootCycleButtonClickListener);
        mStopRebootCycleButton = (Button)findViewById(R.id.button_rebootcycle_stop);
        mStopRebootCycleButton.setOnClickListener(mStopRebootCycleButtonClickListener);
        mRepeatCountLimitCheck = (CheckBox)findViewById(R.id.check_repeatcountlimit);
        mRepeatCountLimitCheck.setOnCheckedChangeListener(mRepeatCountLimitChangeListener);
        mRepeatCountLimitEdit = (EditText)findViewById(R.id.edit_repeatcountlimit);
        mRebootedCountText = (TextView)findViewById(R.id.text_rebootedcount);

        loadPreferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        int limitCount = Integer.valueOf(mRepeatCountLimitEdit.getText().toString());
        PreferenceSettings.saveRebootCycleLimitCount(getApplicationContext(), limitCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
    }

    private void loadPreferences() {
        Context context = getApplicationContext();
        boolean rebootcycle_enableflag = PreferenceSettings.getRebootCycleEnableFlag(context);
        changeButtonState(rebootcycle_enableflag);
        int rebootcycle_limitcount = PreferenceSettings.getRebootCycleLimitCount(context);
        mRepeatCountLimitEdit.setText(String.valueOf(rebootcycle_limitcount));
        boolean rebootcycle_enablelimit = PreferenceSettings.getRebootCycleEnableLimit(context);
        mRepeatCountLimitCheck.setChecked(rebootcycle_enablelimit);
        mRebootedCount = PreferenceSettings.getRebootedCount(context);
        mRebootedCountText.setText(String.valueOf(mRebootedCount));
    }

    private void changeButtonState(boolean testStarted) {
        if (testStarted) { // During reboot cycle test...
            mStartRebootCycleButton.setEnabled(false);
            mStopRebootCycleButton.setEnabled(true);
        } else {
            mStartRebootCycleButton.setEnabled(true);
            mStopRebootCycleButton.setEnabled(false);
        }
    }

    private OnClickListener mStartRebootCycleButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "start button pressed.");

            // Reboot cycle test start
            mRebootedCount = 0;
            PreferenceSettings.saveRebootedCount(getApplicationContext(), mRebootedCount);
            mRebootedCountText.setText(String.valueOf(mRebootedCount));
            changeButtonState(true);
            PreferenceSettings.saveRebootCycleEnableFlag(getApplicationContext(), true);

            mRebootTimer.rebootAfterTime(1*60*1000); // Reboot after 1 minutes
        }
    };

    private OnClickListener mStopRebootCycleButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Stop button pressed.");
            mRebootTimer.cancelReboot();

            // Reboot cycle test stop
            changeButtonState(false);
            PreferenceSettings.saveRebootCycleEnableFlag(getApplicationContext(), false);
        }
    };

    private OnCheckedChangeListener mRepeatCountLimitChangeListener = new OnCheckedChangeListener() {
        //@Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) { // Enable reboot cycle limit
                int limitCount = Integer.valueOf(mRepeatCountLimitEdit.getText().toString());
                PreferenceSettings.saveRebootCycleEnableLimit(getApplicationContext(), true);
                PreferenceSettings.saveRebootCycleLimitCount(getApplicationContext(), limitCount);
            }
        }
    };

    class RebootTimer {
        private Timer mTimer = null;

        public boolean rebootAfterTime(long msec) {
            // Set timer of system reboot.
            final PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);

            if (mTimer != null) {
                cancelReboot();
            }

            mTimer = new Timer(true);
            Log.d(TAG, "rebootAfterTime(): Set reboot timer after " + msec + " msec.");
            mTimer.schedule( new TimerTask() {
                    @Override
                    public void run() {
                        Log.d(TAG, "rebootAfterTime(): Rebooting...");
                        pm.reboot("RebootTP_repeateTest");
                    }
            }, msec); 
            return true;
        }

        public void cancelReboot() {
            if (mTimer != null) {
                Log.d(TAG, "cancelReboot(): Reboot timer canceled.");
                mTimer.cancel();
                mTimer = null;
            }
        }
    }
}
