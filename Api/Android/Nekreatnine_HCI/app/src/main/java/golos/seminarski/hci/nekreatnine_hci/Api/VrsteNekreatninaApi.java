package golos.seminarski.hci.nekreatnine_hci.Api;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import golos.seminarski.hci.nekreatnine_hci.Helper.Config;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Helper.volley.MyVolley;
import golos.seminarski.hci.nekreatnine_hci.Model.VrsteNekreatnina;
import golos.seminarski.hci.nekreatnine_hci.MyApp;

public class VrsteNekreatninaApi {

    public static void GetVrsteNekreatnina(final Context context, final MyRunnable<JSONArray> onSuccess) {

        final String url = Config.urlApi + "VrstaNekreatnine/";

        MyVolley.get2(url, new JSONArray(), new Response.Listener < JSONArray > () {
            @Override
            public void onResponse(JSONArray response) {
                onSuccess.run(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji sa serverom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static ArrayList<VrsteNekreatnina> jsonToVrsteNekreatninaShortView(JSONArray jsonObj) {

        ArrayList <VrsteNekreatnina> items = new ArrayList <VrsteNekreatnina> ();

        JSONObject o = new JSONObject();
        try {
            for (int i = 0; i < (jsonObj.length()); i++) {
                items.add(new VrsteNekreatnina(
                        jsonObj.getJSONObject(i).getInt("VrstaId"),
                        jsonObj.getJSONObject(i).getString("Naziv")
                ));
            }

        } catch (Exception e) {
            Log.e("jsonShortView", "err: " + e.getMessage());
        }
        return items;
    }


}
