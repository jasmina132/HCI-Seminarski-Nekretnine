package golos.seminarski.hci.nekreatnine_hci.Api;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import golos.seminarski.hci.nekreatnine_hci.Helper.Config;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Helper.volley.MyVolley;
import golos.seminarski.hci.nekreatnine_hci.Model.Poruka;
import golos.seminarski.hci.nekreatnine_hci.MyApp;

public class PorukaApi {

    public static void postPoruka(final Context context, final MyRunnable<Poruka> onSuccess, Poruka poruka) {

        final String url = Config.urlApi + "Poruka/";
        final ProgressDialog dialog = ProgressDialog.show(context, "Dobijanje podataka", "U toku");
        dialog.show();
        MyVolley.post(url, Poruka.class, new Response.Listener<Poruka>() {
            @Override
            public void onResponse(Poruka response) {
                dialog.dismiss();
                onSuccess.run(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(MyApp.getContext(), "Greška sa konekcijom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, poruka);
    }


}
