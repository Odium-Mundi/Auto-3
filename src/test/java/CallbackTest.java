import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    void shouldSubmitRequestOk() {
        open("http://localhost:9999");
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");

        form.$("[name = 'name']").setValue("Паршикова Виолетта");
        form.$("[type ='tel']").setValue("+79996666666");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        $("[data-test-id=\"order-success\"]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestOk2() {
        open("http://localhost:9999");
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");

        form.$("[name = 'name']").setValue("Кто-кто Кто");
        form.$("[type ='tel']").setValue("+79996666666");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        $("[data-test-id=\"order-success\"]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestBadWhenEnglishName() {
        open("http://localhost:9999");
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");

        form.$("[name = 'name']").setValue("Who");
        form.$("[type ='tel']").setValue("+79996666666");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        $("body .App_appContainer__3jRx1 .input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void shouldSubmitRequestBadWhenNumberWrong() {
        open("http://localhost:9999");
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");

        form.$("[name = 'name']").setValue("Кто Кто");
        form.$("[type ='tel']").setValue("79996666666+");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        $("body .App_appContainer__3jRx1 .input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestBadWhenNumberLonger11() {
        open("http://localhost:9999");
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");

        form.$("[name = 'name']").setValue("Кто Кто");
        form.$("[type ='tel']").setValue("7999666666611");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        $("body .App_appContainer__3jRx1 .input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
