package golos.seminarski.hci.nekreatnine_hci;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
