package golos.seminarski.hci.nekreatnine_hci;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import golos.seminarski.hci.nekreatnine_hci.Helper.Global;
import golos.seminarski.hci.nekreatnine_hci.Model.Lokacija;
import golos.seminarski.hci.nekreatnine_hci.Model.VrsteNekreatnina;


public class PretragaNekreatnina extends Fragment {

    private Spinner listaGradova;
    private Spinner listaVrsteNekreatnine;
    private CheckBox balkon;
    private CheckBox grijanje;
    private CheckBox parking;
    private CheckBox plin;
    private Button btnTrazi;

    private List<Lokacija> lokacijaList = Global.lokacijaLists;

    private List<VrsteNekreatnina> vrsteNekreatninaList = Global.vrsteNekreatninaLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pretraga_nekreatnina, container, false);

        listaGradova = (Spinner)rootView.findViewById(R.id.gradoviSpinner);
        listaVrsteNekreatnine = (Spinner)rootView.findViewById(R.id.vrsteNekreatninaSpinner);
        balkon = (CheckBox) rootView.findViewById(R.id.chb_Balkon);
        grijanje = (CheckBox) rootView.findViewById(R.id.chbGrijanje);
        parking = (CheckBox) rootView.findViewById(R.id.chbParking);
        plin = (CheckBox) rootView.findViewById(R.id.chbPlin);
        btnTrazi = (Button) rootView.findViewById(R.id.btnTrazi);
        do_PopulateSpinners();


        btnTrazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String grad = listaGradova.getSelectedItem().toString();
                String nekreatnina = listaVrsteNekreatnine.getSelectedItem().toString();
                String ostali = "Balkon : " +  balkon.isChecked() +
                                "Grijanje " + grijanje.isChecked() +
                                "Parking : " + parking.isChecked() +
                                "Plin : " + plin.isChecked();
            }
        });

        return rootView;
    }

    private void do_PopulateSpinners() {

        List<String> gradoviListString = new ArrayList<String>();
        List<String> vrsteNekListString = new ArrayList<String>();
        gradoviListString.add("Odaberite Grad");
        vrsteNekListString.add("Odaberite Vrstu nekreatnine");
        for(Lokacija lok : lokacijaList)
        {
            gradoviListString.add(lok.Naziv);
        }

        for(VrsteNekreatnina lok : vrsteNekreatninaList)
        {
            vrsteNekListString.add(lok.Naziv);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, gradoviListString);

        listaGradova.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, vrsteNekListString);

        listaVrsteNekreatnine.setAdapter(dataAdapter1);


    }



    public static PretragaNekreatnina newInstance() {
        PretragaNekreatnina fragment = new PretragaNekreatnina();
        return fragment;
    }

}
