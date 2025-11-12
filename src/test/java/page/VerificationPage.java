package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.SQLHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement buttonCode = $("[data-test-id='action-verify']");

    public VerificationPage(){
        codeField.should(Condition.visible);
    }

    public PersonalAccountPage verificationCode(String userLogin){
        var code = SQLHelper.getCode(userLogin);
        codeField.setValue(code);
        buttonCode.click();
        return new PersonalAccountPage();
    }
}
