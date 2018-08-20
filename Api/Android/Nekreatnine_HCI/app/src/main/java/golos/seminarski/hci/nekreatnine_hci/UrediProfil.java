package golos.seminarski.hci.nekreatnine_hci;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import golos.seminarski.hci.nekreatnine_hci.Api.KorisniciApi;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Helper.volley.Sesija;
import golos.seminarski.hci.nekreatnine_hci.Model.Korisnici;


public class UrediProfil extends Fragment {

    private EditText username;
    private EditText password;
    private EditText ime;
    private EditText prezime;
    private EditText email;
    private Button register;
    private ImageView reg_image;

    Korisnici korisnik = Sesija.getlogiraniKorisnik();



    // TODO: Rename and change types and number of parameters
    public static UrediProfil newInstance(String param1, String param2) {
        UrediProfil fragment = new UrediProfil();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.activity_register, container, false);
        username = (EditText)rootView.findViewById(R.id.txt_username_reg);
        password = (EditText)rootView.findViewById(R.id.txt_password_reg);
        ime = (EditText)rootView.findViewById(R.id.txt_ime_reg);
        prezime = (EditText)rootView.findViewById(R.id.txt_prezime_reg);
        email = (EditText)rootView.findViewById(R.id.txt_email_reg);
        register = (Button)rootView.findViewById(R.id.btn_register_reg);
        reg_image = (ImageView)rootView.findViewById(R.id.imageView);

        register.setText("Spasi promjene");

        reg_image.setVisibility(View.INVISIBLE);

        do_PopulateFileds();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                korisnik.Ime = ime.getText().toString();
                korisnik.Prezime = prezime.getText().toString();
                korisnik.KorisnickoIme = username.getText().toString();
                korisnik.Lozinka = password.getText().toString();
                korisnik.Email = email.getText().toString();
                korisnik.Status = true;
                Date dt = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                korisnik.DatumRegistracije = sdf.format(dt);

                do_register(korisnik);
            }
        });

        return rootView;
    }

    private void do_register(Korisnici korisnik) {
        KorisniciApi.putKorisnik(MyApp.getContext(), new MyRunnable<Korisnici>() {
            @Override
            public void run(Korisnici result) {

                    Toast.makeText(MyApp.getContext(), "Uspješno ste spasili podatke", Toast.LENGTH_SHORT).show();


            }
        }, korisnik);
    }

    private void do_PopulateFileds() {


        ime.setText(korisnik.Ime);
        prezime.setText(korisnik.Prezime);
        username.setText(korisnik.KorisnickoIme);
        password.setText(korisnik.Lozinka);
        email.setText(korisnik.Email);
    }


    public static UrediProfil newInstance() {
        UrediProfil fragment = new UrediProfil();
        return fragment;
    }
}
