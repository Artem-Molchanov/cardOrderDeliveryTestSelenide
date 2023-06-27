package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {


    @Test
    void shouldTestCardOrderDelivery() {

        DataGenerator date = new DataGenerator();
        String planingDate = date.generateDate(3);


        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").click();
        $("[data-test-id=date] input").sendKeys(Keys.LEFT_CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planingDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79051234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planingDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
