package net.djcp.mjappupdater.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import net.djcp.mjappupdater.AppUpdater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AppUpdater.Builder(this)
                .setDialogTitle("New update available!")
                .setDialogDescription("Check out the new update for mjAppUpdater!\nAvailable now on Google Play.")
                .setDialogButtonUpdate("OK")
                .setDialogButtonLater("Later")
                .setDialogButtonNoThanks("No, thanks")
                .build().create();
    }

}

