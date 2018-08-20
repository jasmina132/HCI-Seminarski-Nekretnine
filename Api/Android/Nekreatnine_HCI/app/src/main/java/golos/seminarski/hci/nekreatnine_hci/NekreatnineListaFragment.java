package golos.seminarski.hci.nekreatnine_hci;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import golos.seminarski.hci.nekreatnine_hci.Api.NekreatnineApi;
import golos.seminarski.hci.nekreatnine_hci.Helper.Global;
import golos.seminarski.hci.nekreatnine_hci.Helper.MyRunnable;
import golos.seminarski.hci.nekreatnine_hci.Model.NekreatninaVM;
import golos.seminarski.hci.nekreatnine_hci.Model.SlikeNekreatnina;


public class NekreatnineListaFragment extends Fragment {

    private List<NekreatninaVM> nekreatnineList;
    private ListView listaItema;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_nekreatnine_lista, container, false);

        listaItema = (ListView)rootView.findViewById(R.id.lista_nekreatina);

        if(Global.nekreatnineLista == null) {
            doPopulateList(inflater);
        }
        else {
            nekreatnineList = new ArrayList<>(Global.nekreatnineLista);
            do_removeDuplicate();
            doPopulateLisView(inflater);
        }

        listaItema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("model",nekreatnineList.get(i)); // Put anything what you want
               // Global.nekreatnina.add(nekreatnineList.get(i));
                doPopulateSlike(i);
                NekreatnineDetalji fragment2 = new NekreatnineDetalji();
                fragment2.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, fragment2)
                        .commit();

            }
        });

        return rootView;
    }

    private void doPopulateSlike(int i) {
        Global.slike = new ArrayList<>();
        SlikeNekreatnina slika = new SlikeNekreatnina();
        for(NekreatninaVM n : Global.nekreatnineLista){
            if(n.NekreatninaId == Global.nekreatnineLista.get(i).NekreatninaId)
            {
                if(n.Slika != null){
                    slika.NekreatninaId = n.NekreatninaId;
                    slika.Slika = n.Slika;
                    Global.slike.add(slika);
                }
            }
        }

    }

    private void do_removeDuplicate() {
        int temp_i = 0;
        List<NekreatninaVM> tempList = new ArrayList<NekreatninaVM>();
        for(NekreatninaVM a : nekreatnineList){
            if(a.NekreatninaId!= temp_i){
                temp_i =  a.NekreatninaId;
            }
            else
                tempList.add(a);
        }
        nekreatnineList.removeAll(tempList);
    }

    private void doPopulateLisView(final LayoutInflater inflater) {

            listaItema.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return nekreatnineList.size();
                }

                @Override
                public Object getItem(int position) {
                    return nekreatnineList.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View view, ViewGroup parent) {
                    NekreatninaVM a = nekreatnineList.get(position);

                    if (view == null) {
                        view = inflater.inflate(R.layout.nekreatnine_lista_item, parent, false);
                    }

                    TextView nazivGrada = (TextView) view.findViewById(R.id.txt_grad_nek);
                    TextView nazivMjesta = (TextView) view.findViewById(R.id.txt_grad_mjesto);
                    TextView Cijena = (TextView) view.findViewById(R.id.txt_cijena_nek);
                    TextView Ocjena = (TextView) view.findViewById(R.id.txt_ocjena_nek);
                    TextView kvadratura = (TextView) view.findViewById(R.id.txt_kvadratura_nek);
                    ImageView slika = (ImageView) view.findViewById(R.id.nekreatninaCoverImage);


                    nazivGrada.setText("Naziv grada : " + a.NazivGrada);
                    nazivMjesta.setText("Naziv mjesta : " + a.NazivMjesta);
                    Cijena.setText("Cijena : " + a.Cijena);
                    Ocjena.setText("Ocjena : 4");
                    kvadratura.setText("Broj kvadrata : " + a.CijenaKvadrata);


                    byte[] decodedBytes = Base64.decode(a.Slika, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

                    slika.setImageBitmap(bitmap);
                    return view;
                }
            });

        }


    private void doPopulateList(final LayoutInflater inflater) {

        nekreatnineList = new ArrayList<>();
        NekreatnineApi.GetNekreatnineSve(getContext(),
                new MyRunnable<JSONArray>() {
                    @Override
                    public void run(final JSONArray result) {
                        if (result == null)
                        {
                            Toast.makeText(MyApp.getContext(), "Neuspješno obavljeno", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(MyApp.getContext(), "Uspjessssno obavljeno", Toast.LENGTH_SHORT).show();

                            nekreatnineList = NekreatnineApi.jsonToNekreatnineShortView(result);
                            Global.nekreatnineLista = new ArrayList<NekreatninaVM>(nekreatnineList);
                            do_removeDuplicate();
                            doPopulateLisView(inflater);
                        }
                    }
                });
    }

    public static NekreatnineListaFragment newInstance() {
        NekreatnineListaFragment fragment = new NekreatnineListaFragment();
        return fragment;
    }

}
