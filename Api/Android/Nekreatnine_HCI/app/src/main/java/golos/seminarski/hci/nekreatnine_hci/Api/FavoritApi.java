package golos.seminarski.hci.nekreatnine_hci.Api;

import android.app.ProgressDialog;
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
import golos.seminarski.hci.nekreatnine_hci.Model.Favorit;
import golos.seminarski.hci.nekreatnine_hci.MyApp;

public class FavoritApi {

    public static void postFavorit(final Context context, final MyRunnable<Favorit> onSuccess, Favorit favorit) {

        final String url = Config.urlApi + "Favorit/";
        final ProgressDialog dialog = ProgressDialog.show(context, "Dobijanje podataka", "U toku");
        dialog.show();
        MyVolley.post(url, Favorit.class, new Response.Listener<Favorit>() {
            @Override
            public void onResponse(Favorit response) {
                dialog.dismiss();
                onSuccess.run(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(MyApp.getContext(), "Greška sa konekcijom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, favorit);
    }

    public static void getFavorite(final Context context, final MyRunnable<JSONArray> onSuccess, final int korsinikId)

    {
        final String url = Config.urlApi + "Favorit/GetFavoritByUserId/" +  korsinikId;
    //    final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "Loading...");
  //      dialog.show();

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

    public static ArrayList<Favorit> jsonToFavoritiShortView(JSONArray jsonObj) {

        ArrayList < Favorit > items = new ArrayList < Favorit > ();

        JSONObject o = new JSONObject();


        try {
            for (int i = 0; i < (jsonObj.length()); i++) {

                items.add(new Favorit(

                        jsonObj.getJSONObject(i).getInt("FavoritId"),
                        jsonObj.getJSONObject(i).getInt("KorisnikId"),
                        jsonObj.getJSONObject(i).getInt("NekreatninaId")

                ));

            }

        } catch (Exception e) {

            Log.e("jsonShortView", "err: " + e.getMessage());

        }

        return items;

    }

}
