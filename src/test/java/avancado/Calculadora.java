package avancado;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class Calculadora{
    private AndroidDriver<MobileElement> driver;
    private DesiredCapabilities desiredCapabilities;
    private URL remoteUrl;
    private String nomePastaScenario;
    private String nomePastaData;
    private int cont = 1;
    private Massa massa;
    private String btn;

    //Funções ou Métodos de apoio
    public void print(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(foto, new File("target/scenariosEvidences/" + nomePastaScenario + "/" + nomePastaData + "/print" + cont + " " + nomePrint + ".png"));
        cont = cont + 1;
    }


    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {

        /*
        EN = Emulador na Nuvem
        EL = Emulador Local
        DN = Dispositivo na Nuvem
        DL = Dispositivo Local
         */

        String flag = "EL";
        nomePastaData = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
        nomePastaScenario = scenario.getName();

        switch (flag){
            case "EN":
                desiredCapabilities = new DesiredCapabilities();
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

                remoteUrl = new URL("https:/---saucelabs.com:---/wd/hub");
                break;

            case "EL":
                desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability("platformName", "Android");
                desiredCapabilities.setCapability("platformVersion", "10.0");
                desiredCapabilities.setCapability("deviceName", "emulator-5554");
                desiredCapabilities.setCapability("automationName", "uiautomator2");
                desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
                desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

                remoteUrl = new URL("http://localhost:4723/wd/hub");
                break;

            case "DN":

                break;

            case "DL":

                break;


        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^abro a calculadora do Google no meu smartphone$")
    public void abroACalculadoraDoGoogleNoMeuSmartphone() {
        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
    }

    @When("^seleciono \"([^\"]*)\" mais \"([^\"]*)\" e pressiono igual$")
    public void selecionoMaisEPressionoIgual(String num1, String num2) throws IOException {
        print("Abro a Calculadora");
        MobileElement btnA = driver.findElementById("com.google.android.calculator:id/digit_" + num1);
        btnA.click();
        print("Quando digito " + num1);
        MobileElement btnPlus = driver.findElementByAccessibilityId("mais");
        btnPlus.click();
        print("E utilizo a operação soma");
        MobileElement btnB = driver.findElementById("com.google.android.calculator:id/digit_" + num2);
        btnB.click();
        print("E quando digito " + num2);
        MobileElement btnEquals = driver.findElementByAccessibilityId("igual");
        btnEquals.click();

    }

    @Then("^exibe o resultado como \"([^\"]*)\"$")
    public void exibeOResultadoComo(String resultadoEsperado) throws IOException {
        print("Mostrando o Resultado esperado");
        MobileElement lblResultadoAtual = driver.findElementById("com.google.android.calculator:id/result_final");
        assertEquals(resultadoEsperado, lblResultadoAtual.getText());

    }

    @Given("^que utilizo a massa \"([^\"]*)\" para testar a calculadora$")
    public void queUtilizoAMassaParaTestarACalculadora(String nomeMassa) throws MalformedURLException {
        //Configurações para execução do emulador na nuvem via Sauce Labs
        //Não está lendo os parâmetros da massa

        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", massa.deviceName);
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

        remoteUrl = new URL("https://---.saucelabs.com:---/wd/hub");

        //Abri o app
        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
    }

    @When("^realizo a operacao com dois numeros$")
    public void realizoAOperacaoComDoisNumeros() throws IOException {
        print("Abro a Calculadora");
        MobileElement btnA = driver.findElementById("com.google.android.calculator:id/digit_" + massa.num1);
        btnA.click();
        print("Quando digito " + massa.num1);


        // Selecionar a operação matemática
        switch (massa.operador){
            case "+":
                btn = "plus";
                break;
            case "-":
                btn = "minus";
                break;
            case "*":
                btn = "multiply";
                break;
            case "/":
                btn = "divide";
                break;
        }

        MobileElement btnOp = driver.findElementByAccessibilityId(btn);
        btnOp.click();
        print("E utilizo a operação " + btn);
        MobileElement btnB = driver.findElementById("com.google.android.calculator:id/digit_" + massa.num2);
        btnB.click();
        print("E quando digito " + massa.num2);
        MobileElement btnEquals = driver.findElementByAccessibilityId("equals");
        btnEquals.click();

    }

    @Then("^compara o resultado atual com o esperado$")
    public void comparaOResultadoAtualComOEsperado() throws IOException {
        print("Mostrando o Resultado esperado");
        if ((btn.equals("divide")) && (massa.num2.equals("0"))){
            MobileElement lblPreview = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_preview");
            assertEquals(massa.resultadoEsperado, lblPreview.getText());
        }
        else{
            MobileElement lblResultadoAtual = driver.findElementById("com.google.android.calculator:id/result_final");
            assertEquals(massa.resultadoEsperado, lblResultadoAtual.getText());
        }

    }
}
