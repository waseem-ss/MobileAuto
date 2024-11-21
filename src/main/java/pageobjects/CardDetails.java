package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Util;

import java.time.Duration;

public class CardDetails extends Util {

    private WebElement getCardNameElement (String cardName, AppiumDriver appiumDriver){
        //System.out.println("Get card name: " + cardName);
        try {
            WebElement progress = appiumDriver.findElement(AppiumBy.
                    xpath("//android.widget.ProgressBar[@resource-id=\"com.example.pokemonexercise:id/loading\"]"));
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofMillis(10000));
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(progress)));
        }catch (NoSuchElementException e){
            System.out.println("progress not found");
        }
        catch (RuntimeException e){
            e.printStackTrace();
        } finally {
            //viewCard(cardName,appiumDriver);
            return appiumDriver.findElement(AppiumBy.
                    xpath("//android.widget.TextView[@resource-id=\"com.example.pokemonexercise:id/card_name\" and @text=\"" + cardName + "\"]"));
        }
    }

    //android.widget.ProgressBar[@resource-id="com.example.pokemonexercise:id/loading"]
    private WebElement getHPElement (String HP, AppiumDriver appiumDriver){
        return appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@resource-id=\"com.example.pokemonexercise:id/hp\" and @text=\""+HP+"\"]"));
    }

    private WebElement getTitleELement (AppiumDriver appiumDriver){
        return appiumDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Pokemon Exercise\"]"));
    }

    private WebElement getInfoImageElement(AppiumDriver appiumDriver){
        return appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.ImageButton[@resource-id=\"com.example.pokemonexercise:id/fab\"]"));
    }


    private WebElement getSnackbarElement (AppiumDriver appiumDriver){
        return appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@resource-id=\"com.example.pokemonexercise:id/snackbar_text\"]"));
    }

    public String getCardNameText(String cardName, AppiumDriver appiumDriver){
        return getCardNameElement(cardName,appiumDriver).getText();
    }

    public String getHPText(String HP, AppiumDriver appiumDriver){
        return getHPElement(HP,appiumDriver).getText();
    }

    public String getTitleText (AppiumDriver appiumDriver){
        return getTitleELement(appiumDriver).getText();
    }

    public String getSnackbarText(AppiumDriver appiumDriver){
        getInfoImageElement(appiumDriver).click();
        WebDriverWait wait = new WebDriverWait(appiumDriver,Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOf(getSnackbarElement(appiumDriver)));
        WebElement snackbarEle = getSnackbarElement(appiumDriver);
        return snackbarEle.getText();
    }

    public String viewCard (String cardName,AppiumDriver appiumDriver){
        boolean cardDisplayed = Util.scrollToCard(cardName,30,appiumDriver);
        if (cardDisplayed){
            return getCardNameText(cardName,appiumDriver);
        }
        return "none";
    }

    public String getAppInfo(AppiumDriver appiumDriver) {
        WebElement infoImage = getInfoImageElement(appiumDriver);
        //Thread.sleep(2000);
        String infoText = "null";
        try {
            infoImage.click();
            infoText = getSnackbarText(appiumDriver);
            System.out.println("Info text \""+ infoText + "\"");
        }
        catch (NoSuchElementException e){
            System.out.println("Element not found!");
        }
        return infoText;
    }
}
