package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By addToCartButtons = By.className("shelf-item__buy-btn");
    By cartIcon = By.xpath("//span[contains(@class,'bag')]");
    By cartQuantity = By.className("bag__quantity");

    // Actions

    // Add first product
    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    // Add multiple products
    public void addMultipleProducts(int count) {
        for (int i = 0; i < count; i++) {
            driver.findElements(addToCartButtons).get(i).click();
        }
    }

    // Open cart
    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    // Get cart quantity number
    public int getCartQuantity() {
        String quantity = driver.findElement(cartQuantity).getText();
        return Integer.parseInt(quantity);
    }
}