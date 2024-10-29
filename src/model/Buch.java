package model;

//import java.io.Serializable;

// Methode um die Daten in einer Textdatei zu hinterlegen
//implements Serializable
public class Buch  {

  //  private static final long serialVersion = 1L;
    private String titel;
    private String autor;
    private int verjahr;
    private boolean status;
    private String isbn;


    // Konstruktor erstellen
    public Buch(String titel, String autor, String isbn, int verjahr) {


        this.isbn = isbn;
        this.verjahr = verjahr;
        this.titel = titel;
        this.autor = autor;
        this.status = false;
    }

    // Getter und Setter
    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public int getVerjahr() {return verjahr;}
    public void setVerjahr(int verjahr) {this.verjahr = verjahr;}

    public String getTitel() {return titel;}
    public void setTitel(String titel) {this.titel = titel;}

    public String getAutor() {return autor;}
    public void setAutor(String autor) {this.autor = autor;}

    public boolean isStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}

    // String-Methode überschreiben
    @Override
    public String toString() {
        String verliehen = status ? "Verliehen" : "Vorhanden";
        return String.format("%-15s | %-15s | %-15s | %-15s | %-15s", titel, autor, verjahr, isbn, verliehen);
    }
}