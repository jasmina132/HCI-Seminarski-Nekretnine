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

public class Autentifikacija

{
    public static void Provjera(final Context context, final MyRunnable<Korisnici> onSuccess, final String username, final String password)

    {
        final String url = Config.urlApi + "Korisnici/LoginKorsnik";
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "Loading...");
        dialog.show();

        MyVolley.get(url, Korisnici.class, new Response.Listener<Korisnici>() {
                    @Override
                    public void onResponse(Korisnici response) {
                        dialog.dismiss();

                        if (response.Lozinka.equals(password)) {
                            onSuccess.run(response);
                        } else {
                            Toast.makeText(MyApp.getContext(), "Pristupni podaci nisu ispravni : ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Toast.makeText(MyApp.getContext(), "Greška u komunikaciji sa serverom : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, username,
                password
        );
    }

}
