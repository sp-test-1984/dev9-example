package com.dev9.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchQueryPage {

    private final WebDriver driver;
    private static final String SEARCHURL = "https://www.google.com/webhp?complete=0&hl=en";

    @FindBy(css = "input[name=q]")
    WebElement query;

    @FindBy( css = "input[value=\"Google Search\"]")
    WebElement searchButton;

    @FindBy( css = "input[value=\"I'm Feeling Lucky\"]")
    WebElement luckyButton;

    public SearchQueryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3000), this);
    }

    public static SearchQueryPage loadUsing(WebDriver driver) {
        driver.get(SEARCHURL);
        return new SearchQueryPage(driver);
    }

    public SearchQueryPage setQuery(String term){
        query.clear();
        query.sendKeys(term);
        return this;
    }

    public SearchResultsPage pressEnterInQuery(){
        query.sendKeys("\n");
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage clickSearchButton(){
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage clickLuckyButton(){
        luckyButton.click();
        return new SearchResultsPage(driver);
    }


}
