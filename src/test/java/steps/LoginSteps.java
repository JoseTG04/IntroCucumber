package steps;

import io.cucumber.java.en.And;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
    public WebDriver driver = new ChromeDriver();

    @Before
    public void setup(){
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void teardown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Given("el usuario está en la página de inicio de sesión")
    public void el_usuario_está_en_la_página_de_inicio_de_sesión() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("el usuario ingresa el nombre de usuario {string} y la contraseña {string}")
    public void el_usuario_ingresa_el_nombre_de_usuario_y_la_contraseña(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("hace click en el botón de inicio de sesión")
    public  void hace_click_en_el_botón_de_inicio_de_sesión(){
        driver.findElement(By.id("login-button")).click();
    }

    @Then("el usuario debería ver la página de productos")
    public void el_usuario_debería_ver_la_página_de_productos(){
        String expectUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectUrl,driver.getCurrentUrl());
    }

    @Then("el usuario debería ver un mensaje de error {string}")
    public void el_usuario_debería_ver_un_mensaje_de_error(String errorMessage){
        String actualError = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(actualError,errorMessage);
    }

    @Then("el usuario deja el campo nombre de usuario y contraseña vacíos")
    public void el_usuario_deja_el_campo_nombre_de_usuario_y_contraseña_vacíos(){
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
    }

}

