package golos.seminarski.hci.nekreatnine_hci.Api;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import golos.seminarski.hci.nekreatnine_hci.Helper.Config;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Helper.volley.MyVolley;
import golos.seminarski.hci.nekreatnine_hci.Model.Korisnici;
import golos.seminarski.hci.nekreatnine_hci.MyApp;

public class KorisniciApi {
    public static void postKorisnik(final Context context, final MyRunnable<Korisnici> onSuccess, Korisnici korisnik) {

        final String url = Config.urlApi + "Korisnici/";
        final ProgressDialog dialog = ProgressDialog.show(context, "Dobijanje podataka", "U toku");
        dialog.show();
        MyVolley.post(url, Korisnici.class, new Response.Listener<Korisnici>() {
            @Override
            public void onResponse(Korisnici response) {
                dialog.dismiss();
                onSuccess.run(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(MyApp.getContext(), "Greška sa konekcijom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, korisnik);
    }

    public static void putKorisnik(final Context context, final MyRunnable<Korisnici> onSuccess, Korisnici k) {

        final String url = Config.urlApi + "Korisnici/";
        MyVolley.putKupac(url, Korisnici.class, new Response.Listener<Korisnici>() {
                    @Override
                    public void onResponse(Korisnici response) {

                        onSuccess.run(response);
                      //  dialog.dismiss();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   dialog.dismiss();
                        Toast.makeText(MyApp.getContext(), "Greška sa konekcijom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                k
        );
    }

}
