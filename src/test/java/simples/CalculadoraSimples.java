package simples;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class CalculadoraSimples {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator");
        desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("appiumVersion", "1.20.2");
        desiredCapabilities.setCapability("deviceOrientation", "portrait");
        desiredCapabilities.setCapability("browserName", "");
        desiredCapabilities.setCapability("app", "storage:filename=Calculator_v7.8 (271241277)_apkpure.com.apk");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("SAUCE_USERNAME", "rafafcorreia");
        desiredCapabilities.setCapability("SAUCE_ACCESS_KEY", "948a3999-def6-47bf-88e0-96c8be289ee2");

        URL remoteUrl = new URL("https://rafafcorreia:948a3999-def6-47bf-88e0-96c8be289ee2@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        String resultadoEsperado = "5";

        MobileElement btn3 = driver.findElementById("com.google.android.calculator:id/digit_3");
        btn3.click();
        MobileElement btnPlus = driver.findElementByAccessibilityId("plus");
        btnPlus.click();
        MobileElement btn2 = driver.findElementById("com.google.android.calculator:id/digit_2");
        btn2.click();
        MobileElement btnEquals = driver.findElementByAccessibilityId("equals");
        btnEquals.click();
        MobileElement lblResultadoAtual = driver.findElementById("com.google.android.calculator:id/result_final");

        assertEquals(resultadoEsperado, lblResultadoAtual.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


