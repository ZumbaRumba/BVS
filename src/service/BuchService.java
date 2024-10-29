package service;

import model.Buch;
import repo.buecherRepo;

import java.util.List;

public class BuchService {
    private buecherRepo repository;

    public BuchService() {
        this.repository = new buecherRepo("Buecher.db");
    }

    public void buchHinzufuegen(Buch buch) {
        repository.buchHinzufuegen(buch);
        System.out.println("Buch hinzugefügt: " + buch.getTitel());
    }

    public  List<Buch> getbuecher() {
        return repository.getbuecher();
    }

    public void verfuegbarkeit(String titel) {
        repository.verfuegbarkeit(titel);
        System.out.println("Buchstatus geändert: " + titel);
    }

    public void buchLoeschen(String titel) {
        repository.buchLoeschen(titel);
        System.out.println("Buch gelöscht: " + titel);
    }


    public void close() {
        repository.close();
    }
}