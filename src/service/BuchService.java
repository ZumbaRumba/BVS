package service;

// Bücherliste erstellen/bearbeiten
import model.Buch;
import java.util.ArrayList;
import java.util.List;
public class BuchService {
    private List<Buch> buecherListe;

    // Eine Array-Liste erstellen und verknüpfen
    public BuchService(){
        this.buecherListe = new ArrayList<>();
    }


    // Methode "Buch hinzufügen"
    public void buchHinzufuegen(Buch buch){
        buecherListe.add(buch);
        System.out.println("Buch hinzugefügt: " + buch.getTitel());
    }

    // Methode zum Anzeigen der Bücherliste
    public List<Buch> getBuecherListe() {
        return buecherListe;
    }


    // Boolean wird zur Eingabe der Verfügbarkeit des Buches genutzt
    public boolean verfuegbarkeit(String titel) {
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

    // Alternative Methode um Buchstatus zu ändern

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

