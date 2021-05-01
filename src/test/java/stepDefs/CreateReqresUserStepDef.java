package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateReqresUserStepDef {
    @Given("I have base url")
    public void i_have_base_url() {

    }

    @When("I POST the {string} endpoint {string} with {string} and {string}")
    public void i_post_the_endpoint_with_and(String string, String string2, String string3, String string4) {

    }

    @Then("User should be created")
    public void user_should_be_created() {

    }

    @And("Response code should be {int}")
    public void response_code_should_be(Integer int1) {

    }

}
