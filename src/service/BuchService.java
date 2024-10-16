package service;

import model.Buch;
import java.util.ArrayList;
import java.util.List;
public class BuchService {
    private List<Buch> buecherListe;

    public BuchService(){
        this.buecherListe = new ArrayList<>();
    }


    // Methode "Buch hinzufügen"
    public void buchHinzufuegen(Buch buch){
        buecherListe.add(buch);
        System.out.println("Buch hinzugefügt: " + buch.getTitel());
    }

    public List<Buch> getBuecherListe() {
        return buecherListe;
    }




}

