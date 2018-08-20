package golos.seminarski.hci.nekreatnine_hci;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import golos.seminarski.hci.nekreatnine_hci.Api.FavoritApi;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Helper.volley.Sesija;
import golos.seminarski.hci.nekreatnine_hci.Model.Favorit;
import golos.seminarski.hci.nekreatnine_hci.Model.NekreatninaVM;


public class NekreatnineDetalji extends Fragment{

    NekreatninaVM nekreatnina;
    private static String ARG_PARAM1 = "model";
    // TODO: Rename and change types of parameters

    private TextView cijena;
    private TextView velicina;
    private TextView opis;
    private TextView vrstaNekreatnine ;
    private TextView nazivGrada ;
    private TextView nazivMjesta ;
    private TextView balkon;
    private TextView cijenaKvadrata;
    private TextView grijanje;
    private TextView kablovskaTv;
    private TextView lift;
    private TextView opremljenost;
    private TextView parking;
    private TextView plin;
    private TextView uknjizen;
    private TextView sprat;

    private Button posaljiPoruku;
    private Button dodajUFavorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nekreatnina = (NekreatninaVM) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nekreatnine_detalji, container, false);

        cijena = (TextView) rootView.findViewById(R.id.cijena_id);
        velicina = (TextView) rootView.findViewById(R.id.txt_povrsina_det);
        opis = (TextView) rootView.findViewById(R.id.txt_opis_det);
        vrstaNekreatnine  = (TextView) rootView.findViewById(R.id.txt_vrstaNek);
        nazivGrada  = (TextView) rootView.findViewById(R.id.txt_grad_det);
        nazivMjesta  = (TextView) rootView.findViewById(R.id.txt_mjesto_det);
        balkon = (TextView) rootView.findViewById(R.id.txt_balkon);
        cijenaKvadrata = (TextView) rootView.findViewById(R.id.txt_kvadrat_det);
        grijanje = (TextView) rootView.findViewById(R.id.txt_grijanje_det);
        kablovskaTv = (TextView) rootView.findViewById(R.id.txt_kablTv_det);
        lift = (TextView) rootView.findViewById(R.id.txt_lift_det);
        opremljenost = (TextView) rootView.findViewById(R.id.txt_opremljenost_det);
        parking = (TextView) rootView.findViewById(R.id.txt_postStr_det);
        plin = (TextView) rootView.findViewById(R.id.txt_plin_det);
        uknjizen = (TextView) rootView.findViewById(R.id.txt_knjizeno_det);
        sprat = (TextView) rootView.findViewById(R.id.txt_sprat_det);
        posaljiPoruku = (Button) rootView.findViewById(R.id.btn_kontaktiraj);
        dodajUFavorite = (Button) rootView.findViewById(R.id.btn_dodajFavorite);

        doPopulateText();

        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.viewPageAndroid);
        ImageSlider adapterView = new ImageSlider(getContext());
        mViewPager.setAdapter(adapterView);

        posaljiPoruku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KontaktirajFragment fragment2 = new KontaktirajFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, fragment2)
                        .commit();
            }
        });

        dodajUFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Favorit favorit = new Favorit();
                favorit.KorisnikId = Sesija.getlogiraniKorisnik().KorisnikId;
                favorit.NekreatninaId = nekreatnina.NekreatninaId;

                FavoritApi.postFavorit(getContext(), new MyRunnable<Favorit>() {
                    @Override
                    public void run(Favorit result) {
                        if (result == null)
                            Toast.makeText(getContext(), "Greška u komunikaciji...", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(getContext(), "Uspješno ste se dodali u favorite", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, favorit);
            }
        });


        return rootView;
    }

    private void doPopulateText() {
        if(nekreatnina != null){
            cijena.setText(nekreatnina.Cijena + "");
            velicina.setText(nekreatnina.Velicina + "");
            opis.setText(nekreatnina.Opis + "");
            vrstaNekreatnine.setText(nekreatnina.CijenaKvadrata + "");
            nazivGrada.setText(nekreatnina.NazivGrada + "");
            nazivMjesta.setText(nekreatnina.NazivMjesta + "");
            balkon.setText(nekreatnina.Balkon + "");
            cijenaKvadrata.setText(nekreatnina.CijenaKvadrata + "");
            grijanje.setText(nekreatnina.Grijanje + "");
            kablovskaTv.setText(nekreatnina.KablovskaTv + "");
            lift.setText(nekreatnina.Lift + "");
            opremljenost.setText(nekreatnina.Opremljenost + "");
            parking.setText(nekreatnina.Parking + "");
            plin.setText(nekreatnina.Plin + "");
            uknjizen.setText(nekreatnina.Uknjizen + "");
            sprat.setText(" - - -");

        }
    }


    public static NekreatnineDetalji newInstance(NekreatninaVM nek) {
        NekreatnineDetalji fragment = new NekreatnineDetalji();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, nek);
        fragment.setArguments(args);
        return fragment;
    }

}
