import com.codeborne.selenide.Selenide;
import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.PersonalAccountPage;
import page.VerificationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBTest {

    @BeforeEach
    public void setUp() {
        var loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
    }

    @Test
    @DisplayName("Успешный вход в систему")
    public void shouldSuccessfullyLogInPersonalAccount() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.loginUser(DataHelper.getUserLogin(),DataHelper.getUserPassword());
        verificationPage.verificationCode(DataHelper.getUserLogin());
    }

    @Test
    @DisplayName("Вход в систему незарегестирированного пользователя")
    public void shouldDisplayedErrorUnregisteredUserLogsIn() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(DataHelper.generateLogin(), DataHelper.generatePassword());
        loginPage.getErrorMsg("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Блокировка системы после 3 неверных вводов пароля")
    public void shouldLockTheSystemAfter3IncorrectPasswords() {
        LoginPage loginPage = new LoginPage();

        loginPage.login(DataHelper.getUserSecondLogin(),DataHelper.generatePassword());
        loginPage.clearField();

        loginPage.login(DataHelper.getUserSecondLogin(),DataHelper.generatePassword());
        loginPage.clearField();

        loginPage.login(DataHelper.getUserSecondLogin(),DataHelper.generatePassword());

        loginPage.getErrorMsg("Ошибка!");
    }
}
