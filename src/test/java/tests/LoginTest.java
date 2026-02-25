package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void TC_001_ValidLogin() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        Assert.assertTrue(driver.getCurrentUrl().contains("bstackdemo"),
                "Valid login failed");
    }

    @Test
    public void TC_002_InvalidPassword() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithInvalidPassword("wrongpass123");

        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Invalid Password");
    }

    @Test
    public void TC_003_EmptyPassword() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithEmptyPassword();

        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Invalid Password");
    }
}