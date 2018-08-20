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
import golos.seminarski.hci.nekreatnine_hci.Model.NekreatninaVM;
import golos.seminarski.hci.nekreatnine_hci.MyApp;

public class NekreatnineApi {

    public static void GetNekreatnineSve(final Context context, final MyRunnable<JSONArray> onSuccess) {

        final String url = Config.urlApi + "Nekreatnina/GetNekreatnine/";

    //    final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
     //   dialog.show();

        MyVolley.get2(url, new JSONArray(), new Response.Listener < JSONArray > () {
            @Override
            public void onResponse(JSONArray response) {
          //      dialog.dismiss();
                onSuccess.run(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            //    dialog.dismiss();
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji sa serverom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void GetNekreatnineBySearch(final Context context, final MyRunnable<JSONArray> onSuccess, String nazivGrada,
                                              Boolean Balkon, Boolean Grijanje, Boolean Parking, Boolean Plin, int VrstaNekreatnine) {

        final String url = Config.urlApi + "Nekreatnina/GetNekreatnineBySearch/";

        //    final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        //   dialog.show();

        MyVolley.getNekreatnine(url, new JSONArray(), new Response.Listener < JSONArray > () {
            @Override
            public void onResponse(JSONArray response) {
                //      dialog.dismiss();
                onSuccess.run(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    dialog.dismiss();
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji sa serverom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        },
                nazivGrada, Balkon, Grijanje, Parking, Plin, VrstaNekreatnine);
    }

    public static ArrayList<NekreatninaVM> jsonToNekreatnineView(JSONArray jsonObj) {

        ArrayList < NekreatninaVM > items = new ArrayList < NekreatninaVM > ();

        JSONObject o = new JSONObject();


        try {
            for (int i = 0; i < (jsonObj.length()); i++) {

                items.add(new NekreatninaVM(

                        jsonObj.getJSONObject(i).getInt("NekreatninaId"),
                        jsonObj.getJSONObject(i).getDouble("Cijena"),
                        jsonObj.getJSONObject(i).getInt("Velicina"),
                        jsonObj.getJSONObject(i).getString("Opis"),
                        jsonObj.getJSONObject(i).getString("VrstaNekreatnine"),
                        jsonObj.getJSONObject(i).getString("NazivGrada"),
                        jsonObj.getJSONObject(i).getString("NazivMjesta"),
                        jsonObj.getJSONObject(i).get("Slika").toString().getBytes(),
                        jsonObj.getJSONObject(i).getBoolean("Balkon"),
                        jsonObj.getJSONObject(i).getInt("CijenaKvadrata"),
                        jsonObj.getJSONObject(i).getBoolean("Grijanje"),
                        jsonObj.getJSONObject(i).getBoolean("KablovskaTv"),
                        jsonObj.getJSONObject(i).getBoolean("Lift"),
                        jsonObj.getJSONObject(i).getString("Opremljenost"),
                        jsonObj.getJSONObject(i).getBoolean("Parking"),
                        jsonObj.getJSONObject(i).getBoolean("Plin"),
                        jsonObj.getJSONObject(i).getBoolean("Uknjizen")
                ));

            }

        } catch (Exception e) {

            Log.e("jsonToNekreatnineView", "err: " + e.getMessage());

        }

        return items;

    }

    public static ArrayList<NekreatninaVM> jsonToNekreatnineShortView(JSONArray jsonObj) {

        ArrayList < NekreatninaVM > items = new ArrayList < NekreatninaVM > ();

        JSONObject o = new JSONObject();


        try {
            for (int i = 0; i < (jsonObj.length()); i++) {

                items.add(new NekreatninaVM(

                        jsonObj.getJSONObject(i).getInt("NekreatninaId"),
                        jsonObj.getJSONObject(i).getString("NazivGrada"),
                        jsonObj.getJSONObject(i).getString("NazivMjesta"),
                        jsonObj.getJSONObject(i).getDouble("Cijena"),
                        jsonObj.getJSONObject(i).getInt("Velicina")

                ));

            }

        } catch (Exception e) {

            Log.e("jsonShortView", "err: " + e.getMessage());

        }

        return items;

    }
}
