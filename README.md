#mjAutoUpdater
=====
An Android library that checks for your application's updates on Google Play Store.

[![Download](https://api.bintray.com/packages/blackdole/maven/mjautoupdater/images/download.svg)](https://bintray.com/blackdole/maven/mjautoupdater/_latestVersion)
[![API](https://img.shields.io/badge/API-10%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=10)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

###Maven
```
<dependency>
  <groupId>net.djcp</groupId>
  <artifactId>mjautoupdater</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

###Gradle
```groovy
dependencies { 
    compile 'net.djcp:mjautoupdater:1.0.0'
}
```

###Required permissions
Add the following permissions to your AndroidManifest.xml.

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.INTERNET"/>
```

###Usage
```java
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
```

###License
    
    Copyright (c) 2016 Black}{ole
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
