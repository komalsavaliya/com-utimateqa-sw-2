package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.Duration;

/**
 * 3. Write down the following test into ‘LoginTest’ class
 * 1. userShouldNavigateToLoginPageSuccessfully * click on the ‘Sign In’ link
 * * Verify the text ‘Welcome Back!’
 * 2. verifyTheErrorMessage
 * * click on the ‘Sign In’ link
 * * Enter invalid username
 * * Enter invalid password
 * * Click on Login button
 * * Verify the error message ‘Invalid email or password.’
 */

public class LoginTest extends BaseTest {
    static String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //Click on Sign Link
        driver.findElement(By.linkText("Sign In")).click();

        //Verify the text 'WelcomeBack!'
        String exceptedText = "Welcome Back!";
        String actualText = driver.findElement(By.xpath("//h2[@class = 'page__heading']")).getText();
        Assert.assertEquals(exceptedText, actualText);
    }

    @Test
    public void verifyTheErrorMessage() {
        //Click on Sign link
        driver.findElement(By.linkText("Sign In")).click();

        //Enter invalid username in user name
        driver.findElement(By.id("user[email]")).sendKeys("johnmary@gmail.com");

        //Enter invalid password in password field
        driver.findElement(By.name("user[password]")).sendKeys("654321");

        //Click on Login Button
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        //Verify the error message 'Invalid email or password.'
        String excepted = "Invalid email or password.";
        String actual = driver.findElement(By.className("form-error__list-item")).getText();
        Assert.assertEquals("Correct Message not Displayed", excepted, actual);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
