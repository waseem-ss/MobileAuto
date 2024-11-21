package util;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class Util {

    public static AppiumDriver getApp(String appName, String deviceName) throws InterruptedException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(System.getProperty("user.dir") + "/Apps/" + appName);
        options.setDeviceName(deviceName);
        URL url = new URL("http://127.0.0.1:4723");
        AppiumDriver appiumDriver = new AppiumDriver(url,options);
        Thread.sleep(10000);
        return appiumDriver;
    }


    public static boolean scrollToCard(String cardName,int cnt, AppiumDriver appiumDriver) {
        System.out.println("In Scroll to Card");
        Dimension dim = appiumDriver.manage().window().getSize();
        Point start = new Point(dim.width / 2, (int) (dim.height * 0.8));
        Point move = new Point(dim.width / 2, (int) (dim.height * 0.3));
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence actions = new Sequence(finger1, 1);
        actions.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), move))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(500)));

        //Execute the Swipe
        boolean cardDisplayed = false;
        for (int i = 0; i < cnt; i++) {
            try {
                WebElement card = appiumDriver.findElement(AppiumBy.
                        xpath("//android.widget.TextView[@resource-id=\"com.example.pokemonexercise:id/card_name\" " +
                                "and @text=\"" + cardName + "\"]"));
                cardDisplayed = card.isDisplayed();
                String textCard = card.getText();
                System.out.println(textCard);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Scroll Count: " + i);
                appiumDriver.perform(Collections.singleton(actions));
                continue;
            }

        }
        return cardDisplayed;
    }
}
