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
}
