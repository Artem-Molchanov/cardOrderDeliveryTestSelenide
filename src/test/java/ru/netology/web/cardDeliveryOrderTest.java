package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class cardDeliveryOrderTest {

    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Test

    void shouldTestCardOrderDelivery() {

        java.util.Date currentDate = new Date();
        Calendar deliveryDate = Calendar.getInstance();
        deliveryDate.setTime(currentDate);
        deliveryDate.add(Calendar.DATE, 3);
        Date currentDatePlusThree = deliveryDate.getTime();

        open ("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").click();
        $("[data-test-id=date] input").sendKeys(Keys.LEFT_CONTROL+"a",Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateFormat.format(currentDatePlusThree));
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79051234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("успешно забронирована")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=notification]").equals(dateFormat.format(currentDatePlusThree));
    }
}
