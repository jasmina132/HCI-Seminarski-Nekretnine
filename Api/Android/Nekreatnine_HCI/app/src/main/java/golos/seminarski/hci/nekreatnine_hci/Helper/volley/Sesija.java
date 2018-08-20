package golos.seminarski.hci.nekreatnine_hci.Helper.volley;

import android.content.SharedPreferences;

import golos.seminarski.hci.nekreatnine_hci.Helper.MyGson;
import golos.seminarski.hci.nekreatnine_hci.Model.Korisnici;
import golos.seminarski.hci.nekreatnine_hci.MyApp;

public class Sesija
{

    public static int IDlogiranogKupca=0;
    private static final String PREFS_NAME = "DatotekaZaSharedPreferences";

    public static Korisnici getlogiraniKorisnik()
    {
        // Restore preferences
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        String str = settings.getString("logiraniKorisnikJson", "");

        if (str.length() == 0)
            return null;
        final Korisnici logiraniKorisnik = MyGson.build().fromJson(str, Korisnici.class);
        IDlogiranogKupca=logiraniKorisnik.KorisnikId;
        return logiraniKorisnik;
    }

    public static void setlogiraniKorisnik(Korisnici logiraniKorisnik)
    {
        final String str = MyGson.build().toJson(logiraniKorisnik);
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("logiraniKorisnikJson", str);

        // Commit the edits!
        editor.commit();

    }



}

