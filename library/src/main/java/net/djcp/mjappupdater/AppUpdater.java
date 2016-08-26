package net.djcp.mjappupdater;

import android.content.Context;
import android.util.Log;

public class AppUpdater {

    private static final String TAG = AppUpdater.class.getSimpleName();
    private Context mContext;
    private String mDialogTitle;
    private String mDialogDesc;
    private String mBtnUpdate;
    private String mBtnLater;
    private String mBtnNoThanks;

    private AppUpdater(Context context) {
        mContext = context;
    }

    public void create() {
        if (!Utils.isNetworkAvailable(mContext)) {
            return;
        }

        String appVersion = Utils.getAppVersion(mContext);
        if (!PrefManager.getInstance(mContext).getAppUpdaterShow(appVersion)) {
            return;
        }
        String appStoreVersion = PlayStore.getLastestVersion(mContext);
        Log.i(TAG, "appVersion: " + appVersion + ", appStoreVersion: " + appStoreVersion);

        if (appVersion.isEmpty() || appStoreVersion.isEmpty()) {
            return;
        }
        int retVal = 0;
        try {
            Version oldVersion = new Version(appVersion);
            Version newVersion = new Version(appStoreVersion);
            retVal = oldVersion.compareTo(newVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (retVal == -1) {
            String title = mDialogTitle != null ? mDialogTitle : mContext.getResources().getString(R.string.dialogTitle);
            String desc = mDialogDesc != null ? mDialogDesc : String.format(mContext.getResources().getString(R.string.dialogDescription),
                    Utils.getAppName(mContext), appStoreVersion);
            String btnUpdate = mBtnUpdate != null ? mBtnUpdate : mContext.getResources().getString(R.string.dialogOKButton);
            String btnLater = mBtnLater != null ? mBtnLater : mContext.getResources().getString(R.string.dialogLaterButton);
            String btnNoThanks = mBtnNoThanks != null ? mBtnNoThanks : mContext.getResources().getString(R.string.dialogNoThanksButton);
            Utils.showUpdateAvailableDialog(mContext, appVersion, title, desc, btnUpdate, btnLater, btnNoThanks);
        }
    }

    public static class Builder {
        private Context mContext;
        private String mDialogTitle;
        private String mDialogDesc;
        private String mBtnUpdate;
        private String mBtnNoThanks;
        private String mBtnLater;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setDialogTitle(String dialogTitle) {
            mDialogTitle = dialogTitle;
            return this;
        }

        public Builder setDialogDescription(String dialogDesc) {
            mDialogDesc = dialogDesc;
            return this;
        }

        public Builder setDialogButtonUpdate(String btnUpdate) {
            mBtnUpdate = btnUpdate;
            return this;
        }

        public Builder setDialogButtonLater(String btnLater) {
            mBtnLater = btnLater;
            return this;
        }

        public Builder setDialogButtonNoThanks(String btnNoThanks) {
            mBtnNoThanks = btnNoThanks;
            return this;
        }

        public AppUpdater build() {
            AppUpdater appUpdater = new AppUpdater(mContext);
            appUpdater.mDialogTitle = mDialogTitle;
            appUpdater.mDialogDesc = mDialogDesc;
            appUpdater.mBtnUpdate = mBtnUpdate;
            appUpdater.mBtnLater = mBtnLater;
            appUpdater.mBtnNoThanks = mBtnNoThanks;
            return appUpdater;
        }
    }

}
