package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By signInButton = By.id("signin");
    By usernameDropdown = By.id("username");
    By passwordField = By.id("password");
    By loginButton = By.id("login-btn");
    By errorMessage = By.className("api-error");

    // Open login popup
    public void openLoginPopup() {
        driver.findElement(signInButton).click();
    }

    // Valid login (dropdown selection)
    public void loginWithValidCredentials() {

        openLoginPopup();

        driver.findElement(usernameDropdown).click();
        driver.findElement(By.xpath("//div[text()='demouser']")).click();

        driver.findElement(passwordField).click();
        driver.findElement(By.xpath("//div[text()='testingisfun99']")).click();

        driver.findElement(loginButton).click();
    }

    // Invalid password login
    public void loginWithInvalidPassword(String invalidPassword) {

        openLoginPopup();

        driver.findElement(usernameDropdown).click();
        driver.findElement(By.xpath("//div[text()='demouser']")).click();

        WebElement passwordInput = driver.findElement(passwordField);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + invalidPassword + "';", passwordInput);

        driver.findElement(loginButton).click();
    }

    // Empty password login
    public void loginWithEmptyPassword() {

        openLoginPopup();

        driver.findElement(usernameDropdown).click();
        driver.findElement(By.xpath("//div[text()='demouser']")).click();

        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}