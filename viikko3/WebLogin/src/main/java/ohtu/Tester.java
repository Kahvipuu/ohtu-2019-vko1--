package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        //satunnainen uusi käyttäjä
        String kayttaja = tunnus();
        
        //loginin testaus
        //WebElement element = driver.findElement(By.linkText("login"));
        //
        //uuden käyttäjän luominen
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));      
        //vanhan valmiin käyttäjän testaus
        //element.sendKeys("pekka");
        //sat.uusi käyttäjä
        element.sendKeys(kayttaja);

        element = driver.findElement(By.name("password"));

        // Oikea salasana
        //element.sendKeys("akkep");
        //
        // Väärä salasana
        //element.sendKeys("vaara");
        // Uuden käyttäjän salasana, ei kovin tietoturvainen
        element.sendKeys(kayttaja);

        element = driver.findElement(By.name("passwordConfirmation"));
        // Uuden käyttäjän salasanan varmistus
        element.sendKeys(kayttaja);
        
        
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        //lisää toimintoja käyttäjän luomisen jälkeen
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(3);

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
    
    private static String tunnus(){
        Random r = new Random();
        return "kayttaja" +r.nextInt(100000);
    }
}
