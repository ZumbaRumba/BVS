import model.Buch;
import service.BuchService;

public class test {
    public static void main(String[] args) {

        Buch lastSouls = new Buch("9798864870051", 2023, "Last Souls", "Kyrill Schmidt");
        System.out.println(lastSouls);


        BuchService buchService = new BuchService();
        buchService.buchHinzufuegen(lastSouls);
        System.out.println(buchService.getbuecher());




    }
}
