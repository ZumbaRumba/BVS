package view;
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
        boolean run = false;
        while (run) {
            showMenu();
            int auswahl = input.nextInt();
            input.nextLine();
            switch (auswahl) {
                case 1:
                    buchHinzufuegenUI();
                    break;
                case 2:
                    //buecherAnzeigenUI();
                    break;
                case 3:
                    //buchStatusUI();
                    break;
                case 0:
                    run = true;
                    break;
                default:
                    System.out.println("Ungültige Auswahl du Hund!!!!");
            }
        }
    }

private void showMenu() {
    System.out.println("\n*** Bibliothekverwaltung ***\n");
    System.out.println("1. Buch hinzufügen");
    System.out.println("2. Bücher anzeigen");
    System.out.println("3. Als Verfügbar markieren");
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
    String isbn = input.nextLine();
    input.nextLine();

    Buch buch = new Buch(isbn, jahr, titel, autor);
    buchService.buchHinzufuegen(buch);

    }




}
