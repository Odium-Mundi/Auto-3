import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");

        form.$("[name = 'name']").setValue("Паршикова Виолетта");
        form.$("[type ='tel']").setValue("+79996666666");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        $("[data-test-id=\"order-success\"]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
