package golos.seminarski.hci.nekreatnine_hci;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import golos.seminarski.hci.nekreatnine_hci.Api.Autentifikacija;
import golos.seminarski.hci.nekreatnine_hci.Api.LokacijaApi;
import golos.seminarski.hci.nekreatnine_hci.Api.NekreatnineApi;
import golos.seminarski.hci.nekreatnine_hci.Api.VrsteNekreatninaApi;
import golos.seminarski.hci.nekreatnine_hci.Helper.Global;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Helper.volley.Sesija;
import golos.seminarski.hci.nekreatnine_hci.Model.Korisnici;
import golos.seminarski.hci.nekreatnine_hci.Model.Lokacija;
import golos.seminarski.hci.nekreatnine_hci.Model.VrsteNekreatnina;

import static golos.seminarski.hci.nekreatnine_hci.MyApp.getContext;

public class LoginActivity extends AppCompatActivity {

    private EditText ime;
    private EditText prezime;
    private EditText username;
    private EditText password;
    private EditText email;
    private Button login;

    private List<Lokacija> lokacijaList;
    private List<VrsteNekreatnina> vrsteNekreatninaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        username = (EditText)findViewById(R.id.txt_username);
        password = (EditText)findViewById(R.id.txt_password);
        login = (Button) findViewById(R.id.btn_login);

        username.setText("korisnik");
        password.setText("korisnik");

        do_getAllNekreatnine();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLoginInitiate();
            }
        });
    }

    private void do_getAllNekreatnine() {
        NekreatnineApi.GetNekreatnineSve(getApplicationContext(),
                new MyRunnable<JSONArray>() {
                    @Override
                    public void run(final JSONArray result) {
                        if (result == null)
                        {
                            Toast.makeText(getContext(), "Neuspješno obavljeno", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getContext(), "Uspjessssno obavljeno", Toast.LENGTH_SHORT).show();

                            Global.nekreatnineLista = NekreatnineApi.jsonToNekreatnineView(result);
                        }
                    }
                });
    }

    private void doLoginInitiate() {
        Autentifikacija.Provjera(this,
                new MyRunnable<Korisnici>() {
                    @Override
                    public void run(Korisnici result) {
                        if (result == null) {
                            Toast.makeText(getContext(), "Pogrešan Username ili Password", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "Uspjesan login ", Toast.LENGTH_SHORT).show();

                            Sesija.setlogiraniKorisnik(result);
                            doPopulateList();
                            startActivity(new Intent(LoginActivity.this, GlavniActivity.class));


                        }
                    }
                },username.getText().toString(),password.getText().toString());
    }

    private void doPopulateList() {




        LokacijaApi.GetLokacije(getContext(),
                new MyRunnable<JSONArray>() {
                    @Override
                    public void run(final JSONArray result) {
                        if (result == null)
                        {
                            Toast.makeText(MyApp.getContext(), "Neuspješno obavljeno", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(MyApp.getContext(), "Uspjessssno obavljeno", Toast.LENGTH_SHORT).show();

                            lokacijaList = LokacijaApi.jsonToLokacijeShortView(result);


                                String temp_i = "";
                                List<Lokacija> tempList = new ArrayList<Lokacija>();
                                for(Lokacija a : lokacijaList){
                                    if(!a.Naziv.equals(temp_i)){
                                        temp_i =  a.Naziv;
                                    }
                                    else
                                        tempList.add(a);
                                }
                            lokacijaList.removeAll(tempList);


                            Global.lokacijaLists = lokacijaList;

                        }
                    }
                });

        VrsteNekreatninaApi.GetVrsteNekreatnina(getContext(),
                new MyRunnable<JSONArray>() {
                    @Override
                    public void run(final JSONArray result) {
                        if (result == null)
                        {
                            Toast.makeText(MyApp.getContext(), "Neuspješno obavljeno", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(MyApp.getContext(), "Uspjessssno obavljeno", Toast.LENGTH_SHORT).show();

                            vrsteNekreatninaList = VrsteNekreatninaApi.jsonToVrsteNekreatninaShortView(result);

                            Global.vrsteNekreatninaLists = vrsteNekreatninaList;
                        }
                    }
                });


    }
}
