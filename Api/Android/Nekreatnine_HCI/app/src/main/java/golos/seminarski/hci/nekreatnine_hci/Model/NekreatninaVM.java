package golos.seminarski.hci.nekreatnine_hci.Model;

import java.io.Serializable;

public class NekreatninaVM   implements Serializable {
    public int NekreatninaId ;
    public double Cijena;
    public int Velicina;
    public String Opis;
    public String VrstaNekreatnine ;
    public String NazivGrada ;
    public String NazivMjesta ;
    public byte[] Slika;
    public boolean Balkon;
    public int CijenaKvadrata;
    public boolean Grijanje;
    public boolean KablovskaTv;
    public boolean Lift;
    public String Opremljenost;
    public boolean Parking;
    public boolean Plin;
    public boolean Uknjizen;

    public NekreatninaVM() {}

    public NekreatninaVM(int NekreatninaId, double Cijena, int Velicina, String Opis, String VrstaNekreatnine,
                         String  NazivGrada, String NazivMjesta, byte[] Slika, boolean Balkon, int CijenaKvadrata,
                         boolean Grijanje, boolean KablovskaTv, boolean Lift, String Opremljenost,
                         boolean Parking, boolean Plin, boolean Uknjizen) {
        this.NekreatninaId = NekreatninaId;
        this.Cijena = Cijena;
        this.Velicina = Velicina;
        this.Opis = Opis;
        this.VrstaNekreatnine = VrstaNekreatnine;
        this.NazivGrada = NazivGrada;
        this.NazivMjesta = NazivMjesta;
        this.Slika  = Slika;
        this.Balkon = Balkon;
        this.CijenaKvadrata = CijenaKvadrata;
        this.Grijanje = Grijanje;
        this.KablovskaTv = KablovskaTv ;
        this.Lift = Lift;
        this.Opremljenost = Opremljenost;
        this.Parking = Parking;
        this.Plin = Plin;
        this.Uknjizen = Uknjizen;
    }

    public NekreatninaVM(int NekreatninaId, String  NazivGrada, String NazivMjesta, double Cijena, int Velicina) {
        this.NekreatninaId = NekreatninaId;
        this.NazivGrada = NazivGrada;
        this.NazivMjesta = NazivMjesta;
        this.Cijena = Cijena;
        this.Velicina = Velicina;
    }
}
