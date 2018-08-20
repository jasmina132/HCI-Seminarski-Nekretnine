package golos.seminarski.hci.nekreatnine_hci.Helper;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGson {
    public static Gson build()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return gsonBuilder.create();
    }
}
