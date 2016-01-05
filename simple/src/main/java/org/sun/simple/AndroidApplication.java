package org.sun.simple;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by 王天明 on 2016/1/5 0005.
 */
public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                            .build());
        }
    }
}
