Feature: Onboard Employee by adding details

  Background:
    Given User is on login page he enters username and password and clicks on login button
    Then User should be navigated to home page of the application

  @AddEmployee
  Scenario Outline: User wants to add a new employee to the system
    Given User clicks on category "<Category>" then user is navigated to home page of the category
    When User clicks on Add button he should be navigated to Add Employee home page
    Then User captures employee first name as "<FirstName>" middle name as "<MiddleName>" last name as "<LastName>" employee Id as "<EmpId>"
    And User clicks on Save button to save the details
    And User captures personal details of employee like drivers license number as "<DriverLicenseNumber>" license expiry date as "<LicenseExpiryDate>" nationality as "<Nationality>" marital status as "<MaritalStatus>" date of birth as "<DOB>" gender as "<Gender>"
    Then User clicks on Save button to save the details
    Then User clicks on contact details link
    And User captures contact details of employee like street1 as "<Street1>" street2 as "<Street2>" city as "<City>" state as "<State>" Zip code as "<ZipCode>" country as "<Country>" mobile number as "<MobileNumber>" email as "<WorkEmail>"
    Then User clicks on Save button to save the details
    Examples:
      | Category | FirstName | MiddleName | LastName | EmpId  | DriverLicenseNumber | LicenseExpiryDate | Nationality | MaritalStatus | DOB        | Gender | Street1 | Street2 | City | State       | ZipCode | Country | MobileNumber | WorkEmail            |
      | PIM      | Random    | No         | Sharma   | 420052 | 123456789           | 2029/27/02        | Indian      | Married       | 1991/30/08 | Male   | ABC     | XYZ     | Pune | Maharashtra | 221002  | India   | 9876543210   | randomName@gmail.com |