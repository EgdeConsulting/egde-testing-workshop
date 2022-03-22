package no.egde.hotelbooking.acceptanceTests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitionRooms {
    private static final String BASE_URL = "http://hotel.no";

    @Given("rooms with the following details exists")
    public void roomsWithTheFollowingDetailsExists(DataTable table) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Accept", "application/json");
        Response response = request.get("/rooms/");

        String jsonString = response.asString();
    }

    @When("a user request all rooms")
    public void aUserRequestAllRooms() {
    }

    @Then("A list containing {int} elements is retrieved")
    public void aListContainingElementsIsRetrieved(int arg0) {
    }
}
