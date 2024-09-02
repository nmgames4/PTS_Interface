package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");  // Run browser in headless mode
        options.addArguments("--no-sandbox");  // Bypass OS security model

        WebDriver driver = new FirefoxDriver(options);

        try {
            driver.get("https://pts.habbousdf.com");
            String pageSource = driver.getPageSource();
            //System.out.println(pageSource);
            new App(pageSource).Run();
        } finally {
            driver.quit();
        }
    }
}
