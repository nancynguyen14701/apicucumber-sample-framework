package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocationLookupSteps {

    private String countryCode;
    private String postalCode;
    private Response response;

    @Given("the country code is {string}")
    public void the_country_code_is(String code) {
        this.countryCode = code;
    }

    @Given("the postal code is {string}")
    public void the_postal_code_is(String code) {
        this.postalCode = code;
    }

    @When("I send a GET request to the Zippopotam API")
    public void i_send_request() {
        response = given()
                .baseUri("http://api.zippopotam.us")
                .when()
                .get("/{country}/{postalCode}", countryCode, postalCode);
        System.out.println(response.asString());
    }

    @Then("the response status code should be {int}")
    public void the_status_code_should_be(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Then("the country name should be {string}")
    public void the_country_should_be(String expectedCountry) {
        String actualCountry = response.jsonPath().getString("country");
        assertThat(actualCountry, equalTo(expectedCountry));
    }

    @Then("the place name should include {string}")
    public void the_place_should_include(String expectedPlace) {
        String place = response.jsonPath().getString("places[0].'place name'");
        assertThat(place, containsString(expectedPlace));
    }

    @Then("the state should include {string}")
    public void the_state_should_include(String expectedState) {
        String state = response.jsonPath().getString("places[0].'state'");
        assertThat(state, containsString(expectedState));
    }
}
