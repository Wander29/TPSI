package pkg5aia_venturi_esjdbc;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String DRIVER = "com.mysql.jdbc.Driver";
        try {
            Class.forName(DRIVER);
            System.out.println("Driver trovato");
        } catch (ClassNotFoundException el) {
            System.out.println("Driver non trovato");
            System.exit(1);
        }

        //nome e indirizzo del database
        String URL_MioDB = "jdbc:mysql://localhost:3306/db_5aia";

        //stabilisco la connessione
        System.out.println("Connessione con: " + URL_MioDB);
        Connection connessione = null;

        try {
            connessione = DriverManager.getConnection(URL_MioDB, "root", "");
            System.out.println("Connessione avvenuta");
        } catch (Exception e) {
            System.out.println("Errore durante la connessione: " + e);
        }

        //LETTURA RECORD
        /*
        String query = "SELECT * FROM Alunno";
        try {
            //Creazione statement per interagire con il db
            Statement statement = connessione.createStatement();
            //Interrogo il DBMS mediante la query
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String nome = resultSet.getString(2);
                String cognome = resultSet.getString(1);

                System.out.println("Cognome: " + cognome + "\nNome: " + nome + "\n");
            }
        } catch (Exception e) {

        } */
        //INSERIMENTO RECORD
        String inserimento = "INSERT INTO Alunno (nome, cognome) VALUES (?, ?)";
        try {
            PreparedStatement statementPr = connessione.prepareStatement(inserimento);
            statementPr.setString(1, "Mario");
            statementPr.setString(2, "Rossi");
            //statementPr.toString();   COMANDO in SQL che manderà al Server

            int rowsInserted = statementPr.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("E' stato inserito un nuovo alunno");
            }

        } catch (Exception e) {

        }
        //AGGIORNAMENTO RECORD
        /*
        String sql = "UPDATE Alunno SET nome=?, cognome=? WHERE cognome=?";
        try {
            PreparedStatement statementUp = connessione.prepareStatement(sql);
            statementUp.setString(1, "Luca");
            statementUp.setString(2, "Ciotti");
            statementUp.setString(3, "Rossi");

            int rowsUpdated = statementUp.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("E' stato effettuato l'update!");
            }
        } catch (Exception e) {

        }*/
    }
}
