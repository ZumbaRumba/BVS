public class BVS1 {
    String isbn;
    int veroefdatum;
    String titel;
    String autor;
    String zustand;
    String genre;


    // Geliehen oder nicht
    boolean status;


    public BVS1(String isbn, int veroefdatum, String titel, String autor, String zustand, String genre, boolean status) {
        this.isbn = isbn;
        this.veroefdatum = veroefdatum;
        this.titel = titel;
        this.autor = autor;
        this.zustand = zustand;
        this.genre = genre;
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

    public String getZustand() {
        return zustand;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void infos (){
        System.out.println("ISBN: " + isbn + "\nVeroeffentlickkeitsdatum: " + veroefdatum + "\nTitel: " + titel +
                "\nAutor: " + autor + "\nZustand des Buches: " + zustand + "\nGenre: " + genre + "\nVerfügbar true/false: " + status);
    }

}