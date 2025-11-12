package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id='login'] input");
    private final SelenideElement passwordField = $("[data-test-id='password'] input");
    private final SelenideElement clickButton = $("[data-test-id='action-login']");
    private final SelenideElement errorMsg = $("[data-test-id='error-notification'].notification");

    public VerificationPage loginUser(String userLogin, String userPassword) {
        login(userLogin, userPassword);
        return new VerificationPage();
    }

    public void login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        clickButton.click();
    }

    public void clearField() {
        loginField.press(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        passwordField.press(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }

    public void getErrorMsg(String msg) {
        errorMsg.shouldBe(Condition.visible, Duration.ofSeconds(5))
                .shouldHave(Condition.text(msg));
    }
}
