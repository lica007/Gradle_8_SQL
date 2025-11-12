package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountPage {
    private final SelenideElement personalAccountField = $("[data-test-id='dashboard']");

    public PersonalAccountPage() {
        personalAccountField.should(Condition.visible);
    }
}
