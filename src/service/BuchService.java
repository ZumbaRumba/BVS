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

    public boolean verliehenMarkieren(String titel) {
        for (Buch buch : buecherListe) {
            if (buch.getTitel().equalsIgnoreCase(titel)) {
                if (buch.isStatus() == true) {
                    buch.setStatus(false);
                    System.out.println("Das Buch " + titel + " ist jetzt vorhanden.");
                } else {
                    buch.setStatus(true);
                    System.out.println("Das Buch " + titel + " ist jetzt verliehen.");
                }

                return true;
            }
        }
        System.out.println("Das Buch " + titel + " ist jetzt verliehen.");
        return false;
    }

    // public boolean vorhandenMarkieren(String titel) {
       // for (Buch buch : buecherListe) {
         //   if (buch.getTitel().equalsIgnoreCase(titel)) {
           //     buch.setStatus(false);
             //   System.out.println("Das Buch " + titel + " ist jetzt vorhanden.");
               // return false;
           // }
        // }

        // System.out.println("Das Buch " + titel + " ist jetzt verliehen.");
       // return true;
   // }



}

