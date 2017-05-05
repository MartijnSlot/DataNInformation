package nl.utwente.di;

import java.sql.*;

/**
 * Created by martijn.slot on 02/05/2017.
 */
public class Main {
    private static final String HOST = "castle.ewi.utwente.nl";
    private static final String DBNAME = "di21";
    private static final String URL = "jdbc:postgresql://" + HOST + ":5432/" + DBNAME;
    private static final String PASSWORD = "C8wmT1gN";
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        initdb();
        ResultSet resultSet = runEx9();

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue);
            }
            System.out.println("");
        }
    }

    private static void initdb() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, DBNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static ResultSet runEx5() throws SQLException {
        String query =
                "SELECT DISTINCT pp.name " +
                        "FROM acts a, movie m, person p, person pp, writes w " +
                        "WHERE p.name LIKE 'Harrison Ford' " +
                        "AND p.pid = a.pid " +
                        "AND a.mid = w.mid " +
                        "AND w.pid = pp.pid";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    private static ResultSet runEx6() throws SQLException {
        String query =
                "SELECT DISTINCT pp.name " +
                        "FROM acts a, movie m, person p, person pp, writes w " +
                        "WHERE p.name LIKE ? " +
                        "AND p.pid = a.pid " +
                        "AND a.mid = w.mid " +
                        "AND w.pid = pp.pid";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "Bruce Willis");
        return preparedStatement.executeQuery();
    }

    private static ResultSet runEx8() throws SQLException {
        String sql = "CREATE OR REPLACE FUNCTION MoviesOfActor(actor text) " +
                "RETURNS TABLE(name text) AS $$ " +
                "BEGIN " +
                    "RETURN QUERY " +
                    "SELECT DISTINCT pp.name " +
                    "FROM acts a, movie m, person p, person pp, writes w " +
                    "WHERE p.name LIKE actor " +
                    "AND p.pid = a.pid " +
                    "AND a.mid = w.mid " +
                    "AND w.pid = pp.pid; " +
                "END; " +
                "$$ LANGUAGE plpgsql;";

        Statement statement = connection.createStatement();
        statement.execute(sql);
        CallableStatement callableStatement = connection.prepareCall("{call MoviesOfActor(?)}");
        callableStatement.setString(1, "Bruce Willis");
        return callableStatement.executeQuery();
    }

    private static ResultSet runEx9() throws SQLException {
        String query =
                "SELECT DISTINCT p.name " +
                        "FROM person p, writes w " +
                        "WHERE p.pid = w.pid " +
                        "AND NOT EXISTS(SELECT * FROM directs d WHERE d.pid=w.pid) " +
                        "ORDER BY name ASC";

        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
