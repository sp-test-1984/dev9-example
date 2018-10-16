package com.dev9.web;

import com.dev9.page.SearchQueryPage;
import com.dev9.page.SearchResultsPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchSteps {

    private WebDriver driver;
    private Object currentPage;
    private String termExpected = "";
    private String template = "expected type %s - actual -%s";

    @Before
    public void buildDriver(){
        driver = new FirefoxDriver();
    }

    @After
    public void destroyDriver(){
        driver.quit();
    }

    @Given("^A Google search page$")
    public void a_Google_search_page() throws Throwable {
        currentPage = SearchQueryPage.loadUsing(driver);
    }

    @When("^I enter the search term \"(.*?)\"$")
    public void i_enter_the_search_term(String term) throws Throwable {
        Assert.assertEquals(template,
                SearchQueryPage.class.getSimpleName(), currentPage.getClass().getSimpleName());
        termExpected = term;
        currentPage = ((SearchQueryPage) currentPage).setQuery(term);
    }

    @When("^I submit the search by pressing \"(.*?)\"$")
    public void i_submit_the_search_by_pressing(String submitType) throws Throwable {
        switch (submitType.toLowerCase()){
            case "enter":
            case "enter key":
                currentPage = ((SearchQueryPage) currentPage).pressEnterInQuery();
                break;
            case "search":
            case "google search button":
            case "search button":
                currentPage = ((SearchQueryPage) currentPage).clickSearchButton();
                break;
            case "i'm feeling lucky button":
            case "i'm feeling lucky":
            case "lucky":
            case "lucky button":
                currentPage = ((SearchQueryPage) currentPage).clickLuckyButton();
                break;
        }
    }

    @Then("^The search result page title should contain the search term$")
    public void the_search_result_page_title_should_contain_the_search_term() throws Throwable {
        String termActual = ((SearchResultsPage) currentPage).getTermFromTitle();
        Assert.assertTrue("they're not equal ",
                termActual.contains(termExpected));
    }

}
