package net.djcp.mjappupdater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import org.jsoup.Jsoup;

public class PlayStore {

    private static final String URL_WEB_PLAY_STORE = "https://play.google.com/store/apps/details?id=%s";
    private static final String URL_PLAY_STORE = "market://details?id=%s";

    public static String getLastestVersion(Context context) {
        try {
            return new GetLatestVersion().execute(context.getPackageName()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static class GetLatestVersion extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String packageName = params[0];
                return Jsoup.connect(String.format(URL_WEB_PLAY_STORE, packageName))
                        .timeout(10000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .getElementsByAttributeValue("itemprop", "softwareVersion")
                        .first()
                        .ownText();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    public static void gotoMarket(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format(URL_PLAY_STORE, context.getPackageName())));
        context.startActivity(intent);
    }
}
