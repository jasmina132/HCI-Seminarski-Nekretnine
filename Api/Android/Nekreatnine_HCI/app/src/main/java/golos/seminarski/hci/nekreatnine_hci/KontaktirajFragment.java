package golos.seminarski.hci.nekreatnine_hci;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import golos.seminarski.hci.nekreatnine_hci.Api.PorukaApi;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Model.Poruka;


public class KontaktirajFragment extends Fragment {

    private EditText naslov;
    private EditText porukaE;
    private Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kontaktiraj, container, false);

        naslov = (EditText)rootView.findViewById(R.id.txt_naslovPoruke);
        porukaE = (EditText)rootView.findViewById(R.id.txt_Poruka);
        submit = (Button)rootView.findViewById(R.id.btn_posaljiPoruku);

        final Poruka poruka = new Poruka();
        poruka.IzdavacId = 1;
        poruka.KorisnikId = 1;
        poruka.Naslov = naslov.getText().toString();
        poruka.Poruka = porukaE.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PorukaApi.postPoruka(getContext(), new MyRunnable<Poruka>() {
                    @Override
                    public void run(Poruka result) {
                        if (result == null)
                            Toast.makeText(getContext(), "Greška u komunikaciji...", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(getContext(), "Uspješno ste se registrovali", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, poruka);
            }
        });

        return rootView;
    }


    public static KontaktirajFragment newInstance() {
        KontaktirajFragment fragment = new KontaktirajFragment();
        return fragment;
    }
}
