package model;

public class Buch {
    String isbn;
    String titel;
    String autor;
    int verjahr;
    boolean status;


    public Buch(String isbn, int verjahr, String titel, String autor) {
        this.isbn = isbn;
        this.verjahr = verjahr;
        this.titel = titel;
        this.autor = autor;
        this.status = true;

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getVerjahr() {
        return verjahr;
    }

    public void setVerjahr(int verjahr) {
        this.verjahr = verjahr;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String verliehen = status ? "Verliehen" : "Vorhanden";


        return String.format("%-15s | %-15s | %-8s | %-8s | %s", titel, autor, verjahr, verliehen, isbn);
    }

}