package jdbc_02_annunci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class NonStaticMain {
    
    //Dichiarazioni
    BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
    Connection connessione = null;
    String input, query;
    Statement statement;
    PreparedStatement statementPr;
    int choose;
    int cnt;
    
    public NonStaticMain(){  } //costruttore

    public void run () {
        String DRIVER = "com.mysql.jdbc.Driver";
        try {
            Class.forName(DRIVER);
            System.out.println("Driver TROVATO");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver NON trovato");
            System.exit(1);
        }

        String url_db = "jdbc:mysql://localhost:3306/5aia_venturi_annunci";
        System.out.println("Connessione al db: " + url_db);

        try {
            connessione = DriverManager.getConnection(url_db, "root", "");
            System.out.println("Connessione avvenuta");

        } catch (Exception e) {
            System.out.println("Errore durante la connessione: " + e);
        }

        //Loop programma
        do {
            //Azzeramento variabili
            input = "";
            query = "";
            choose = 0;
            //Menu
            System.out.println("\n\n######### MENÙ #########");
            System.out.println("> 1 : Inserisci un relatore\n"
                    + "> 2 : Inserisci un annuncio\n"
                    + "> 3 : Mostra relatori\n"
                    + "> 4 : Mostra annunci\n"
                    + "> 5 : Cancella annuncio\n"
                    + "> 6 : ESCI\n");
            try {
                input = tastiera.readLine();
            } catch (Exception e) {  }

            if (isNumeric(input))
                choose = Integer.parseInt(input);
            else 
                continue;

            switch (choose) {
                case 1: //Inserimento
                    System.out.println(">Inserisci un Relatore");

                    query = "INSERT INTO relatore (nome, cognome, ruolo) VALUES (?, ?, ?)";
                    try {
                        statementPr = connessione.prepareStatement(query);
                        System.out.print("Nome: ");
                        statementPr.setString(1, tastiera.readLine());
                        System.out.print("Cognome: ");
                        statementPr.setString(2, tastiera.readLine());
                        System.out.print("Ruolo: ");
                        statementPr.setString(3, tastiera.readLine());
                        statementPr.toString();

                        int rowsInserted = statementPr.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("E' stato inserito un nuovo relatore");
                        }
                    } catch (Exception e) {  }
                    break;

                case 2:
                    System.out.println(">Inserisci un Annuncio");
                    query = "INSERT INTO annuncio (titolo, data, testo, FkRelatore) VALUES (?, ?, ?, ?)";
                    try {
                        statementPr = connessione.prepareStatement(query);
                        System.out.print("Titolo: ");
                        statementPr.setString(1, tastiera.readLine());
                        System.out.print("Data (yyyy-mm-dd): ");
                        statementPr.setString(2, tastiera.readLine());
                        System.out.print("Testo: ");
                        statementPr.setString(3, tastiera.readLine());

                        mostraRel("CodRelatore");

                        System.out.print("Relatore (codice numerico): ");
                        do {
                            input = tastiera.readLine();
                            statementPr.setString(4, input);
                        } while (!isNumeric(input));
                        statementPr.toString();

                        int rowsInserted = statementPr.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("E' stato inserito un nuovo relatore");
                        }
                    } catch (Exception e) {  }
                    break;

                case 3: //Lettura
                    System.out.println("********** Relatori **********");
                    mostraRel("Cognome");
                    break;

                case 4:
                    System.out.println("********** Annunci **********");
                    mostraAnn();
                    break;

                case 5: //Eliminazione
                    System.out.println(">Cancella Annunco");
                    query = "DELETE annuncio.* FROM annuncio WHERE annuncio.CodAnnuncio = ?";
                    try {
                        statementPr = connessione.prepareStatement(query);

                        mostraAnn();
                        System.out.println("ENTER per tornare indietro");
                        System.out.println("Annuncio da Eliminare (codice numerico): ");
                        do {
                            input = tastiera.readLine();
                            statementPr.setString(1, input);
                        } while (!isNumeric(input));

                        int rowsUpdated = statementPr.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("ELIMINAZIONE effettuata");
                        }
                    } catch (Exception e) {  }
                    break;

                case 6:
                    System.out.println(".. Uscita in corso ..");
                    System.exit(1);

                default:
                    break;
            }

            try {
                System.out.println("\n.. Press ENTER to restart..");
                tastiera.readLine();
            } catch (Exception e) {  }

        } while (true);
    }
        
    public void mostraAnn() {
        query = "SELECT annuncio.Data, annuncio.Titolo, annuncio.Testo, relatore.Cognome, relatore.Nome, annuncio.CodAnnuncio FROM annuncio, relatore WHERE relatore.CodRelatore = annuncio.FkRelatore ORDER BY annuncio.Data ";
        
        try {
            statement = connessione.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "    Titolo: " + resultSet.getString(2)
                        + "   Relatore: " + resultSet.getString(4) + " " + resultSet.getString(5) +
                        "       «Annuncio n°: " + resultSet.getString(6) + "\n" + resultSet.getString(3) + "\n");;
            }
        } catch (Exception e) {  }
    }

    public void mostraRel(String ordine) {
        query = "SELECT * FROM relatore ORDER BY relatore." + ordine;
        try {
            statement = connessione.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - Cognome: " + resultSet.getString(3)
                        + "    Nome: " + resultSet.getString(2)
                        + "   Ruolo: " + resultSet.getString(4));
            }
        } catch (Exception e) {  }
    }
        
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
