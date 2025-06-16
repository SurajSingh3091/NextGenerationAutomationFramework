package application.PageRepository.Pages;

import application.PageRepository.PageInitializer.PageInitializer;
import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import com.nextZen.Framework.WebUtility.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmployee_Page extends PageInitializer {
    public ExecutionContext executionContext;
    SeleniumHelper seleniumHelper;
    Logger logger = LogManager.getLogger(Home_Page.class);

    public AddEmployee_Page(ExecutionContext context) {
        super(context.getDriverManager().getDriver());
        this.executionContext = context;
        seleniumHelper = executionContext.getSeleniumHelper();
    }

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameTextBox;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleNameTextBox;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameTextBox;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following::input[@class='oxd-input oxd-input--active']")
    private WebElement employeeIdTextBox;

    @FindBy(xpath = "//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")
    private WebElement uploadImageButton;

    @FindBy(xpath = "//input[@class='oxd-file-input']")
    private WebElement inputImageFilePath;



    //Personal Details
    @FindBy(xpath = "//label[text()='Driver's License Number']/parent::div/following-sibling::div/input")
    private WebElement driverLicenceNumberTextBox;

    @FindBy(xpath = "//label[text()='License Expiry Date']/parent::div/following-sibling::div/child::div/div/input")
    private WebElement licenseExpiryDate;

    @FindBy(xpath = "//label[text()='Nationality']/parent::div/following-sibling::div/descendant::i")
    private WebElement nationalityDropDownIcon;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div")
    private WebElement dropDownValue;

    @FindBy(xpath = "label[text()='Marital Status']/parent::div/following-sibling::div/descendant::i")
    private WebElement maritalStatusDropDownIcon;

    @FindBy(xpath = "label[text()='Date of Birth']/parent::div/following-sibling::div/child::div/div/input")
    private WebElement dateOfBirthTextBox;

    @FindBy(xpath = "label[text()='Gender']/parent::div/following-sibling::div")
    private WebElement genderRadioButton;

    //Contact Details
    @FindBy(xpath = "//a[text()='Contact Details']")
    private WebElement contactDetailsLink;

    @FindBy(xpath = "//label[text()='Country']/parent::div/following-sibling::div/descendant::i")
    private WebElement countryDropDownIcon;

    public void captureFirstName(String firstName) {
        seleniumHelper.inputText("First Name", firstNameTextBox, firstName);
        logger.info("Successfully captured First Name as: " + firstName);
    }

    public void captureMiddleName(String middleName) {
        seleniumHelper.inputText("Middle Name", middleNameTextBox, middleName);
        logger.info("Successfully captured Middle Name as: " + middleName);
    }

    public void captureLastName(String lastName) {
        seleniumHelper.inputText("Last Name", lastNameTextBox, lastName);
        logger.info("Successfully captured Last Name as: " + lastName);
    }

    public void captureEmployeeId(String employeeId) {
        seleniumHelper.clearAndInputText("Employee ID", employeeIdTextBox, employeeId);
        logger.info("Successfully captured Employee ID as: " + employeeId);
    }

    public void uploadImage(String imagePath) {
        seleniumHelper.inputText("Employee Image", inputImageFilePath, imagePath);
        logger.info("Successfully uploaded the image as: " + imagePath);
    }



    public void captureDriverLicense(String drivingLicenseNumber) {
        seleniumHelper.inputText("Driver's License Number", driverLicenceNumberTextBox, drivingLicenseNumber);
        logger.info("Successfully captured Drivers License number");
    }

    public void captureDriverLicenseExpiryDate(String expiryDate) {
        seleniumHelper.inputText("Driver's License expiry date", licenseExpiryDate, expiryDate);
        logger.info("Successfully captured Drivers License Expiry date");
    }

    public void selectNationality(String nationality) {
        seleniumHelper.clickWebElement("Nationality Dropdown Icon", nationalityDropDownIcon);
        WebElement nationalityWebElement = dropDownValue.findElement(By.xpath("span[text()='" + nationality + "']"));
        seleniumHelper.clickWebElement("Nationality", nationalityWebElement);
        logger.info("Successfully selected nationality");
    }

    public void selectMaritalStatus(String maritalStatus) {
        seleniumHelper.clickWebElement("Marital Status Dropdown Icon", maritalStatusDropDownIcon);
        WebElement maritalWebElement = dropDownValue.findElement(By.xpath("span[text()='" + maritalStatus + "']"));
        seleniumHelper.clickWebElement("Marital Status", maritalWebElement);
        logger.info("Successfully selected Marital Status");
    }

    public void captureDateOfBirth(String dob) {
        seleniumHelper.inputText("Date of Birth", dateOfBirthTextBox, dob);
        logger.info("Successfully captured Date Of Birth");
    }

    public void selectGender(String gender) {
        WebElement genderWebElement = genderRadioButton.findElement(By.xpath("descendant::label[text()='" + gender + "']/input"));
        seleniumHelper.clickWebElement("Gender", genderWebElement);
    }

    //contact details action methods
    public void clickContactDetails() {
        seleniumHelper.clickWebElement("Contact Details", contactDetailsLink);
        logger.info("Successfully clicked Contact Detail link");
        seleniumHelper.waitForPageLoad();
    }

    public void captureInputFieldsOnContactDetail(String fieldName, String fieldValue) {
        WebElement fieldWebElement = executionContext.getDriverManager().getDriver().findElement(By.xpath("//label[text()='" + fieldName + "']/parent::div/following-sibling::div/input"));
        seleniumHelper.inputText(fieldName, fieldWebElement, fieldValue);
        logger.info("Successfully captured value for field: "+fieldName+" as : "+fieldValue);
    }

    public void selectCountry(String country) {
        seleniumHelper.clickWebElement("Country Dropdown Icon", countryDropDownIcon);
        WebElement nationalityWebElement = dropDownValue.findElement(By.xpath("span[text()='" + country + "']"));
        seleniumHelper.clickWebElement("Country", nationalityWebElement);
        logger.info("Successfully selected Country");
    }

}
