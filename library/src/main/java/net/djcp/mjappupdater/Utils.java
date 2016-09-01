package net.djcp.mjappupdater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import net.djcp.mjpreferences.Preferences;

public final class Utils {

    private Utils() {
        throw new AssertionError();
    }

    static String getAppName(Context context) {
        return context.getString(context.getApplicationInfo().labelRes);
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static void showUpdateAvailableDialog(final Context context,
                                                 final String appVersion, String title, String desc,
                                                 String btnUpdate, String btnLater, String btnNoThanks) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(desc)
                .setCancelable(true)
                .setNeutralButton(btnUpdate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PlayStore.gotoMarket(context);
                    }
                }).setNegativeButton(btnLater, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton(btnNoThanks, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Preferences.putBoolean(Constants.KEY_APPUPDATER_SHOW + appVersion, false);
                    }
                }).create().show();
    }
}
