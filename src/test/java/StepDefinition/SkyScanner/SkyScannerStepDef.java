package StepDefinition.SkyScanner;

import com.nextZen.Framework.Context.ExecutionContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SkyScannerStepDef {
    private final ExecutionContext executionContext;
    //Constructor
    public SkyScannerStepDef(ExecutionContext context){
        this.executionContext= context;
    }

    @Given("User is on home page of Sky Scanner site")
    public void user_is_on_home_page_of_sky_scanner_site() {
            //executionContext.getPageObjectManager().getHomePageRepo().clickDifferentSectionsOnHomePage("Suraj");
            System.out.println("Home Page");
    }
    @When("User inputs From city as {string} To city as {string}")
    public void user_inputs_from_city_as_to_city_as(String string, String string2) {
        System.out.println("Second Page");
    }
    @When("User chooses return ticket as {string} and enters from Date as {string}  to Date as {string}")
    public void user_chooses_return_ticket_as_and_enters_from_date_as_to_date_as(String string, String string2, String string3) {
        System.out.println("third Page");
    }
    @When("User updated the number of travellers with adult as {string} children as {string} and updated the Class of travel as {string}")
    public void user_updated_the_number_of_travellers_with_adult_as_children_as_and_updated_the_class_of_travel_as(String string, String string2, String string3) {
        System.out.println("Fourth Page");
    }
    @Then("User clicks on Search button he should get all the available options")
    public void user_clicks_on_search_button_he_should_get_all_the_available_options() {

        System.out.println("Fifth Page");
    }
}
