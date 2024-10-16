package model;

public class Buch {
    String isbn;
    String titel;
    String autor;
    int veroefdatum;
    boolean status;


    public Buch(String isbn, int veroefdatum, String titel, String autor, String zustand, String genre, boolean status) {
        this.isbn = isbn;
        this.veroefdatum = veroefdatum;
        this.titel = titel;
        this.autor = autor;
        this.status = status;

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getVeroefdatum() {
        return veroefdatum;
    }

    public void setVeroefdatum(int veroefdatum) {
        this.veroefdatum = veroefdatum;
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

    public void infos (){
        System.out.println("ISBN: " + isbn + "\nVeroeffentlickkeitsdatum: " + veroefdatum + "\nTitel: " + titel +
                "\nAutor: " + autor +  "\nVerfügbar true/false: " + status);
    }

}