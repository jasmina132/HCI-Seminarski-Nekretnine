package golos.seminarski.hci.nekreatnine_hci.Model;


public class Favorit {

    public int FavoritId;
    public int KorisnikId;
    public int NekreatninaId;

    public Favorit(){}
    public Favorit (int FavoritId, int KorisnikId, int NekreatninaId){
        this.FavoritId = FavoritId;
        this.KorisnikId = KorisnikId;
        this.NekreatninaId = NekreatninaId;
    }
}
