package com.pokemon;


import pageobjects.CardDetails;
import util.Util;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;


public class TestCatalog {

    public  String deviceName = "PXL_9Pro";
    public String appName = "sdet-challenge.apk";

    @Test
    public void testCard() throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = Util.getApp(appName,deviceName);
        CardDetails cardDetailsPage = new CardDetails();
        String title = cardDetailsPage.getTitleText(appiumDriver);
        String cardName = cardDetailsPage.getCardNameText("Name: Ampharos", appiumDriver);
        String hpText = cardDetailsPage.getHPText("HP: 130",appiumDriver);
        System.out.println(title);
        Assert.assertEquals(title, "Pokemon Exercise", "The title is correct");
        Assert.assertEquals(cardName, "Name: Ampharos", "The text is as expected");
        Assert.assertEquals(hpText, "HP: 130", "The HP is as expected");
    }


    @Test
    public void testAggronCard() throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = Util.getApp(appName,deviceName);
        String cardName = "Name: Aggron";
        String HpText = "HP: 140";
        CardDetails cardDetails = new CardDetails();
        String actualName  = cardDetails.viewCard(cardName,appiumDriver);
        System.out.println(actualName);
        String actualHPText = cardDetails.getHPText(HpText,appiumDriver);
        System.out.println(actualHPText);
        Assert.assertEquals(actualName,cardName,"Card Name Matched");
        Assert.assertEquals(actualHPText,HpText,"HP text Matched");
    }

    @Test
    public void testDratiniCard() throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = Util.getApp(appName,deviceName);
        String cardName = "Name: Dratini";
        String HpText = "HP: 40";
        CardDetails cardDetails = new CardDetails();
        String actualName  = cardDetails.viewCard(cardName,appiumDriver);
        System.out.println(actualName);
        String actualHPText = cardDetails.getHPText(HpText,appiumDriver);
        System.out.println(actualHPText);
        Assert.assertEquals(actualName,cardName,"Card Name Matched");
        Assert.assertEquals(actualHPText,HpText,"HP text Matched");
    }

    @Test
    public void testInfoIcon() throws MalformedURLException, InterruptedException {
        AppiumDriver appiumDriver = Util.getApp(appName,deviceName);
        CardDetails cardDetails = new CardDetails();
        String actualInfo = cardDetails.getAppInfo(appiumDriver);
        System.out.println(actualInfo);
        Assert.assertEquals(actualInfo, "Powered by Pokémon TCG API","Text matched");
    }

}
