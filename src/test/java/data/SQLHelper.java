package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper(){
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "asy", "autumn");
    }

    @SneakyThrows
    public static void addUser(String id, String login, String password) {
        var dataSQL = "INSERT INTO users (id, login, password) VALUES (?, ?, ?);";
        try (var conn = getConnection()) {
            runner.update(conn, dataSQL, id, login, password);
        }
    }

    @SneakyThrows
    public static String getCode(String userLogin) {
        var dataSQL = "SELECT code FROM auth_codes JOIN users ON auth_codes.user_id = users.id WHERE login  = ? ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnection()) {
            return runner.query(conn, dataSQL, new ScalarHandler<>(), userLogin);
        }
    }
}
