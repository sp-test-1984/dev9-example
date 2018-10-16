package com.dev9.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private final WebDriver driver;

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3000), this);
        new WebDriverWait(driver, 3000)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search li")));
    }

    public String getTermFromTitle(){
        String title = driver.getTitle();
        return title.substring(0, title.indexOf("-"));
    }
}
