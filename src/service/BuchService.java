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

    public boolean verliehenMarkieren(String titel){
        for (Buch buch : buecherListe){
            if (buch.getTitel().equalsIgnoreCase(titel)){
                buch.setStatus(false);
                System.out.println("Das Buch " + titel + " ist verliehen.");
                return false;
            }
        }
        System.out.println("Das Buch " + titel + " ist vorhanden.");
        return true;
    }




}

