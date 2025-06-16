package StepDefinition;

import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StepDefinition {
    private final ExecutionContext executionContext;
    Logger logger = LogManager.getLogger(StepDefinition.class);

    public StepDefinition(ExecutionContext context) {
        this.executionContext = context;
    }


    @Given("User clicks on category {string} then user is navigated to home page of the category")
    public void user_clicks_on_category_then_user_is_navigated_to_home_page_of_the_category(String category) {
        executionContext.getPageObjectManager().getHomePage().clickCategoryLink(category, category);
        executionContext.getSeleniumHelper().waitForPageLoad();
        if (executionContext.getPageObjectManager().getApplicationMasterPage().isElementOnHomepageDisplayed(category)) {
            logger.info("Successfully Navigated to " + category + " home Page");
        } else {
            logger.error("Failed to click " + category + " link and navigate to its home page");
        }
    }

    @When("User clicks on Add button he should be navigated to Add Employee home page")
    public void user_clicks_on_add_button_he_should_be_navigated_to_add_employee_home_page() {
        executionContext.getPageObjectManager().getHomePage().clickAddButton();
        executionContext.getSeleniumHelper().waitForPageLoad();
        if (executionContext.getPageObjectManager().getApplicationMasterPage().isElementOnHomepageDisplayed("Add Employee")) {
            logger.info("Successfully Navigated to Add Employee Page");
        } else {
            logger.error("Failed to navigate to Add Employee Page");
        }
    }

    @Then("User captures employee first name as {string} middle name as {string} last name as {string} employee Id as {string}")
    public void user_captures_employee_first_name_as_middle_name_as_last_name_as_employee_id_as(String firstName, String middleName, String lastName, String empID) {
        try {
            executionContext.getPageObjectManager().getAddEmployeePage().captureFirstName(firstName);
            executionContext.getPageObjectManager().getAddEmployeePage().captureMiddleName(middleName);
            executionContext.getPageObjectManager().getAddEmployeePage().captureLastName(lastName);
            executionContext.getPageObjectManager().getAddEmployeePage().captureEmployeeId(empID);
            logger.info("Successfully captured the details");
        } catch (Exception e) {
            logger.error("Method failed with exception: ", e.getMessage());
            throw new RuntimeException("Method failed with exception: " + e.getMessage(), e);
        }

    }

    @Then("User clicks on Save button to save the details")
    public void user_clicks_on_save_button_to_save_the_details() {
        executionContext.getPageObjectManager().getApplicationMasterPage().clickSaveButton();
        if(executionContext.getPageObjectManager().getApplicationMasterPage().isSaveSuccessfulMessageDisplayed()){
            logger.info("Successfully saved the record.");
        }else{
            logger.error("Failed to save the record.");
        }
    }

    @Then("User captures personal details of employee like drivers license number as {string} license expiry date as {string} nationality as {string} marital status as {string} date of birth as {string} gender as {string}")
    public void user_captures_personal_details_of_employee_like_drivers_license_number_as_license_expiry_date_as_nationality_as_marital_status_as_date_of_birth_as_gender_as(String drivingLicense, String licenceExpiryDate, String nationality, String maritalStatus, String dob, String gender) {
       executionContext.getPageObjectManager().getAddEmployeePage().captureDriverLicense(drivingLicense);
       executionContext.getPageObjectManager().getAddEmployeePage().captureDriverLicenseExpiryDate(licenceExpiryDate);
       executionContext.getPageObjectManager().getAddEmployeePage().selectNationality(nationality);
       executionContext.getPageObjectManager().getAddEmployeePage().selectGender(gender);
       executionContext.getPageObjectManager().getAddEmployeePage().selectMaritalStatus(maritalStatus);
       executionContext.getPageObjectManager().getAddEmployeePage().captureDateOfBirth(dob);
    }

    @Then("User clicks on contact details link")
    public void user_clicks_on_contact_details_link() {
      executionContext.getPageObjectManager().getAddEmployeePage().clickContactDetails();
      executionContext.getSeleniumHelper().waitForPageLoad();
        if (executionContext.getPageObjectManager().getApplicationMasterPage().isElementOnHomepageDisplayed("Contact Details")) {
            logger.info("Successfully Navigated to Contact Details Page");
        } else {
            logger.error("Failed to navigate to Contact Details Page");
        }
    }

    @Then("User captures contact details of employee like street1 as {string} street2 as {string} city as {string} state as {string} Zip code as {string} country as {string} mobile number as {string} email as {string}")
    public void user_captures_contact_details_of_employee_like_street1_as_street2_as_city_as_state_as_zip_code_as_country_as_mobile_number_as_email_as(String street1, String street2, String city, String state, String zipCode, String country, String mobileNumber, String email) {
        executionContext.getPageObjectManager().getAddEmployeePage().captureInputFieldsOnContactDetail("Street 1",street1);
        executionContext.getPageObjectManager().getAddEmployeePage().captureInputFieldsOnContactDetail("Street 2",street2);
        executionContext.getPageObjectManager().getAddEmployeePage().captureInputFieldsOnContactDetail("City",city);
        executionContext.getPageObjectManager().getAddEmployeePage().captureInputFieldsOnContactDetail("State/Province",state);
        executionContext.getPageObjectManager().getAddEmployeePage().captureInputFieldsOnContactDetail("Zip/Postal Code",zipCode);
        executionContext.getPageObjectManager().getAddEmployeePage().selectCountry(country);
        executionContext.getPageObjectManager().getAddEmployeePage().captureInputFieldsOnContactDetail("Mobile",mobileNumber);
        executionContext.getPageObjectManager().getAddEmployeePage().captureInputFieldsOnContactDetail("Email",email);

    }

}
