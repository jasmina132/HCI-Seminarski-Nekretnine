package golos.seminarski.hci.nekreatnine_hci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import golos.seminarski.hci.nekreatnine_hci.Api.KorisniciApi;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Model.Korisnici;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText ime;
    private EditText prezime;
    private EditText email;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.txt_username_reg);
        password = (EditText)findViewById(R.id.txt_password_reg);
        ime = (EditText)findViewById(R.id.txt_ime_reg);
        prezime = (EditText)findViewById(R.id.txt_prezime_reg);
        email = (EditText)findViewById(R.id.txt_email_reg);
        register = (Button)findViewById(R.id.btn_register_reg);


        ime.setText("Korisnik x");
        prezime.setText("Korisnik x");
        username.setText("korisnik");
        password.setText("korisnik");
        email.setText("korisnik@mail.com");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Korisnici korisnik = new Korisnici();

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
    }

    private void do_register(Korisnici korisnik) {
        KorisniciApi.postKorisnik(this, new MyRunnable<Korisnici>() {
            @Override
            public void run(Korisnici result) {
                if (result == null)
                    Toast.makeText(getApplicationContext(), "Greška u komunikaciji...", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getApplicationContext(), "Uspješno ste se registrovali", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        }, korisnik);
    }
}
