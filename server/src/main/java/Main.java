import chess.*;

import dataAccess.DataAccessException;
import dataAccess.DatabaseManager;
import server.Server;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws DataAccessException {
        try { DatabaseManager.createDatabase(); } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }

//        try (var conn = DatabaseManager.getConnection()) {
//                conn.setCatalog("chess");
//                var createTestTable = "CREATE TABLE if NOT EXISTS test (id INT NOT NULL AUTO_INCREMENT, value VARCHAR(255) NOT NULL, PRIMARY KEY (id))";
//                try (var createTableStatement = conn.prepareStatement(createTestTable)) {
//                    createTableStatement.executeUpdate();
//                }
//
//
//                try (var prepStatement = conn.prepareStatement("INSERT INTO test (value) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
//                    prepStatement.setString(1, "testing");
//                    var resultSet = prepStatement.executeUpdate();
//                    System.out.println(resultSet);
//                }
//
//            } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }

        System.out.println("♕ 240 Chess Server");
        Server server = new Server();
        int port = server.run(8080);

    }
}