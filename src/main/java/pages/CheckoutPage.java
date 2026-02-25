package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By firstName = By.id("firstNameInput");
    By lastName = By.id("lastNameInput");
    By address = By.id("addressLine1Input");
    By state = By.id("provinceInput");
    By postalCode = By.id("postCodeInput");
    By submitButton = By.id("checkout-shipping-continue");
    By confirmation = By.xpath("//h1[text()='Your Order has been successfully placed.']");

    // Actions
    public void enterShippingDetails() {
        driver.findElement(firstName).sendKeys("John");
        driver.findElement(lastName).sendKeys("Doe");
        driver.findElement(address).sendKeys("Street 123");
        driver.findElement(state).sendKeys("California");
        driver.findElement(postalCode).sendKeys("90001");
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public boolean isOrderPlacedSuccessfully() {

        WebElement confirmation = driver.findElement(By.id("confirmation-message"));
        return confirmation.isDisplayed();
    }
}