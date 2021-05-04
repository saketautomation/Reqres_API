package stepDefs;

import static utils.PropertyReader.getProperty;
import static utils.JsonUtil.getJsonUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.Assert;
import utils.JsonUtil;


public class CreateReqresUserStepDef {

   static Logger logger = Logger.getLogger(CreateReqresUserStepDef.class);
 RequestSpecification requestSpecification;
 Response response;

    @Given("I have base url")
    public void i_have_base_url() {
        RestAssured.baseURI = getProperty("baseURI");
    }

    @When("I POST the {string} endpoint {string} with {string} and {string}")
    public void i_post_the_endpoint_with_and(String body, String resourceId, String name, String job) {
       requestSpecification =  RestAssured.given();
       response = requestSpecification.body(getJsonUtil().getUpdatedJsonObject(getJsonUtil().loadJsonFile(body),name,job)).when().post(resourceId);
    }

    @Then("User should be created")
    public void user_should_be_created() {
    logger.info(response.then().extract().toString());
        System.out.println("*******"+response.then().extract().response().toString());
        System.out.println("*******"+response.then().extract().body().toString());
    }

    @And("Response code should be {int}")
    public void response_code_should_be(int statusCode) {
     Assert.assertEquals(statusCode,response.getStatusCode());
    }

}
