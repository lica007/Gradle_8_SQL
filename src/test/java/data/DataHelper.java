package data;

//import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.javafaker.Faker;

public class DataHelper {
    private static final Faker FAKER = new Faker();

    private DataHelper() {
    }

    public static String generateLogin() {
        return FAKER.name().firstName();
    }

    public static String generatePassword() {
        return FAKER.internet().password();
    }

    public static String generateId() {
        return FAKER.internet().uuid();
    }

    public static String getUserLogin() { return "vasya"; }
    public static String getUserPassword() { return "qwerty123"; }

    public static String getUserSecondLogin() { return "petya"; }
}
