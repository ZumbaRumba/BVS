package repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Buch;

public class buecherRepo {
    private Connection connection;

    public buecherRepo(String dbPath) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS buecher (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titel TEXT NOT NULL,
                    autor TEXT,
                    isbn TEXT,
                    verjahr INTEGER,
                    status BOOLEAN
                );
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void buchHinzufuegen(Buch buch) {
        String query = "INSERT INTO buecher (titel, autor,isbn, verjahr, status) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, buch.getTitel());
            pstmt.setString(2, buch.getAutor());
            pstmt.setString(3, buch.getIsbn());
            pstmt.setInt(4, buch.getVerjahr());
            pstmt.setBoolean(5, buch.isStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  List<Buch> getbuecher() {
        List<Buch> buecher = new ArrayList<>();
        String query = "SELECT titel, autor, isbn, verjahr, status FROM buecher";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Buch buch = new Buch(
                        rs.getString("Titel"),
                        rs.getString("Autor"),
                        rs.getString("Isbn"),
                        rs.getInt("verjahr")


                );
                buch.setStatus(rs.getBoolean("status"));
                buecher.add(buch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buecher;
    }

    public void verfuegbarkeit(String titel) {
        String query = "UPDATE buecher SET status = 1 WHERE titel = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, titel);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buchLoeschen(String titel) {
        String query = "DELETE FROM buecher WHERE titel = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, titel);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        
    }


