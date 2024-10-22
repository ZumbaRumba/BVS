package view;

// Methoden zur Steuerung des Programms
import java.util.List;
import java.util.Scanner;
import service.BuchService;
import model.Buch;

public class KonsoleUI {
    private BuchService buchService;
    private Scanner input;

    public KonsoleUI(){
    buchService = new BuchService();
    input = new Scanner(System.in);
    }

public void menu(){
        boolean run = true;
        while (run) {
            showMenu();
            int auswahl = input.nextInt();
            input.nextLine();
            switch (auswahl) {
                case 1:
                    buchHinzufuegenUI();
                    break;
                case 2:
                    buecherAnzeigenUI();
                    break;
                case 3:
                    buchStatusUI1();
                    break;
                case 4:
                    buchLoeschenUI();
                    break;
                case 5:
                    buchSuchenUI();
                    break;

                    // Alternative Methode um Buchstatus zu ändern

                // case 4:
                    // buchStatusUI2();
                    // break;

                case 0:
                    buchService.buecherSpeichern();
                    System.out.println("Programm beendet.");
                    run = false;
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
            }
        }
    }


// Benutzeroberfläche
private void showMenu() {
    System.out.println("\n*** Bibliothekverwaltung ***\n");
    System.out.println("1. Buch hinzufügen");
    System.out.println("2. Bücher anzeigen");
    System.out.println("3. Buchstatus ändern");
    System.out.println("4. Buch löschen");
    System.out.println("5. Buch suchen");


    // Alternative Methode um Buchstatus zu ändern
    // System.out.println("4. Buch als vorhanden markieren");

    System.out.println("0. Beenden");
    System.out.println("Auswahl: ");
    }

private void buchHinzufuegenUI() {
    System.out.println("Bitte den Titel eingeben: ");
    String titel = input.nextLine();
    System.out.println("Bitte Autor/in eingeben: ");
    String autor = input.nextLine();
    System.out.println("Bitte Veröffentlichungsjahr eingeben: ");
    int jahr = input.nextInt();
    System.out.println("Bitte ISBN eingeben: ");
    String isbn = input.next();
    input.nextLine();

    Buch buch = new Buch(isbn, jahr, titel, autor);
    buchService.buchHinzufuegen(buch);

    }

    private void buecherAnzeigenUI() {

        System.out.println("\n*** Aktuelle Bücherliste ***\n");
        System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s", "Titel", "Autor", "Veröf. Jahr", "ISBN", "Verliehen/Vorhanden");
        System.out.println("\n-------------------------------------------------------------------------------------");
        for (Buch buch : buchService.getBuecherListe()) {
            System.out.println(buch);
        }

    }

    private void buchStatusUI1 () {

        System.out.print("Titel des Buches, welches als verliehen/vorhanden markiert werden soll: ");
        String titel = input.nextLine();
        buchService.verfuegbarkeit(titel);
    }



    private void  buchLoeschenUI () {
        System.out.println("Welcher Titel soll gelöscht werden.");
        String titel = input.nextLine();
        System.out.println("Soll " + titel + " wirklich gelöscht werden? (j/n)");
        String zustimmungB = input.nextLine();
        if (zustimmungB.equalsIgnoreCase("j")) {
            buchService.buchLoeschen(titel);
        } else {
            System.out.println("Löschvorgang abgebrochen.");
        }
    }


    private void buchSuchenUI() {
        System.out.print("Suchbegriff: ");
        String suchbegriff = input.nextLine();
        List<Buch> suchErgebnisse = buchService.buchSuchen(suchbegriff);
        if (suchErgebnisse.isEmpty()) {
            System.out.println("Keine Treffer.");
        } else {
            System.out.println("\n--- Suchergebnisse ---");
            buchListeAnzeigen(suchErgebnisse);
        }
    }


    private void buchListeAnzeigen(List<Buch> liste) {
        System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s", "Titel", "Autor", "Veröf. Jahr", "ISBN", "Verliehen/Vorhanden");
        System.out.println("\n-------------------------------------------------------------------------------------");
        for (Buch buch : liste) {
            System.out.println(buch);
        }
    }
    // Alternative Methode um Buchstatus zu ändern

   // private void buchStatusUI2 () {

     //   System.out.print("Titel des Buches, welches als vorhanden markiert werden soll: ");
       // String titel = input.nextLine();
       // buchService.vorhandenMarkieren(titel);
   // }
}