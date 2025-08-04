package stepdefinitions;

import com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ApiUtils;

import java.net.http.HttpResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LocationLookupSteps {

    private static final Logger logger = LogManager.getLogger(LocationLookupSteps.class);
    private String countryCode;
    private String postalCode;
    private HttpResponse<String> response;

    @Given("the country code is {string}")
    public void the_country_code_is(String code) {
        this.countryCode = code;
        logger.info("Running test");
        logger.info("Country Code: {}", code);
    }

    @Given("the postal code is {string}")
    public void the_postal_code_is(String code) {
        this.postalCode = code;
        logger.info("Postal Code: {}", code);
    }

    @When("I send a GET request to the Zippopotam API")
    public void sendStandardRequest() {
        response = ApiUtils.sendGetRequest(countryCode, postalCode, false);
    }

    @When("I send a malicious GET request to the Zippopotam API")
    public void sendEncodedRequest() {
        response = ApiUtils.sendGetRequest(countryCode, postalCode, true);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedCode) {
        assertThat("Unexpected status code", response.statusCode(), equalTo(expectedCode));
    }

    @Then("the country name should be {string}")
    public void verifyCountryName(String expectedCountry) {
        JsonObject json = ApiUtils.parseJson(response.body());
        String actualCountry = json.get("country").getAsString();
        assertThat("Country name mismatch", actualCountry, equalTo(expectedCountry));
    }

    @Then("the place name should include {string}")
    public void verifyPlaceName(String expectedPlace) {
        JsonObject json = ApiUtils.parseJson(response.body());
        String place = json.getAsJsonArray("places").get(0).getAsJsonObject().get("place name").getAsString();
        assertThat("Place name mismatch", place, containsString(expectedPlace));
    }

    @Then("the state should include {string}")
    public void verifyState(String expectedState) {
        JsonObject json = ApiUtils.parseJson(response.body());
        String state = json.getAsJsonArray("places").get(0).getAsJsonObject().get("state").getAsString();
        assertThat("State mismatch", state, containsString(expectedState));
    }
}
