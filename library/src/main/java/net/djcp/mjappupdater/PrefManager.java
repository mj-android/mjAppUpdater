package net.djcp.mjappupdater;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private final int PRIVATE_MODE = 0;
    private final String PREF_NAME = "$AutoUpdaterPref";
    private final String KEY_UPDATE_SHOW = "$UpdaterShow";
    private static PrefManager mPrefManager = null;
    private Context mContext;
    private SharedPreferences mPreferences;

    public PrefManager(Context context) {
        mContext = context;
        mPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public static PrefManager getInstance(Context context) {
        if (mPrefManager == null) {
            mPrefManager = new PrefManager(context);
        }
        return mPrefManager;
    }

    public boolean getAppUpdaterShow(String appVersion) {
        return mPreferences.getBoolean(KEY_UPDATE_SHOW + appVersion, true);
    }

    public void setAppUpdaterShow(String appVersion, boolean show) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(KEY_UPDATE_SHOW + appVersion, show);
        editor.apply();
    }

}
