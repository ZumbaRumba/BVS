package service;

// Bücherliste erstellen/bearbeiten/suchen
import java.io.*;
import java.util.stream.Collectors;
import model.Buch;
import java.util.ArrayList;
import java.util.List;


public class BuchService {
    private List<Buch> buecherListe;
    private static final String BVS_PFAD = "data/buecherDatabase.txt";

    // Eine Array-Liste erstellen und verknüpfen
    public BuchService(){
        this.buecherListe = new ArrayList<>();
        datenLaden();
    }

    // Methode "Buch löschen"
    public boolean buchLoeschen (String titel) {

        for (int i = 0; i < buecherListe.size(); i++) {
            Buch buch = buecherListe.get(i);
            if (buch.getTitel().equalsIgnoreCase(titel)) {
                buecherListe.remove(i);
                System.out.println("\nDas Buch " + titel + " wurde gelöscht.");
                return true;
            }
        }
        System.out.println("\nDas Buch " + titel + " wurde nicht gefunden");
        return false;
    }

    // Methode "Buch hinzufügen"
    public void buchHinzufuegen(Buch buch){
        buecherListe.add(buch);
        System.out.println("\nDas Buch " + buch.getTitel() + " wurde erfolgreich hinzugefügt.");
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
                    System.out.println("\nDas Buch " + titel + " ist jetzt vorhanden.");
                } else {
                    buch.setStatus(true);
                    System.out.println("\nDas Buch " + titel + " ist jetzt verliehen.");
                }
                return true;
            }
        }
        System.out.println("Das Buch " + titel + " ist verliehen.");
        return false;
    }

    // Methode "Buch suchen"
    public List<Buch> buchSuchen(String suchbegriff) {

        return buecherListe.stream()
                .filter(a -> a.getTitel().toLowerCase().contains(suchbegriff.toLowerCase())
                        || a.getAutor().toLowerCase().contains(suchbegriff.toLowerCase())
                        || a.getIsbn().toLowerCase().contains(suchbegriff.toLowerCase())
                        || String.valueOf(a.getVerjahr()).toLowerCase().contains(suchbegriff.toLowerCase())
                        || (a.isStatus() ? "verliehen" : "vorhanden").toLowerCase().contains(suchbegriff.toLowerCase()))
                            .collect(Collectors.toList());
        }

    // Speicherung der Daten in die Textdatei
    public void buecherSpeichern() {
        try {
            File bvs = new File(BVS_PFAD);
            File verzeichnis = bvs.getParentFile();
            if (verzeichnis != null && !verzeichnis.exists()) {
                verzeichnis.mkdirs(); // Erstellt alle nicht existierenden Verzeichnisse
            }
            try (ObjectOutputStream buchAusgabe = new ObjectOutputStream(new FileOutputStream(bvs))) {
                buchAusgabe.writeObject(buecherListe);
                System.out.println("Das Buch wurde erfolgreich eingetragen.");
            } catch (IOException e) {
                System.out.println("Fehler bei der Speicherung: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Datenpersistenz: Daten laden
    @SuppressWarnings("unchecked")
    private void datenLaden() {
        File bvs = new File(BVS_PFAD);
        if (bvs.exists()) {
            try (ObjectInputStream buchEingabe = new ObjectInputStream(new FileInputStream(bvs))) {
                buecherListe = (List<Buch>) buchEingabe.readObject();
                System.out.println("Das Buch wurde erfolgreich geladen.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Fehler beim Laden des Buches: " + e.getMessage());
            }
        } else {
            System.out.println("Keine vorhandene Bücher zum Laden gefunden.");
        }
    }

    public void close() {
        close();
    }
}