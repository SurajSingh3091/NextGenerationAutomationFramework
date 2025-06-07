Feature: Flight booking on Sky Scanner site

  @BookTicket
  Scenario Outline: User wants to book ticket on Sky Scanner site
    Given User is on home page of Sky Scanner site
    When User inputs From city as "FromCity" To city as "ToCity"
    And User chooses return ticket as "ReturnTicket" and enters from Date as "FromDate"  to Date as "ToDate"
    And User updated the number of travellers with adult as "Adult" children as "Children" and updated the Class of travel as "ClassOfTravel"
    Then User clicks on Search button he should get all the available options
    Examples:
    |FromCity|ToCity|ReturnTicket|FromDate|ToDate|Adult|Children|ClassOfTravel|
    |Varanasi|Pune  |No          |06-July |      |2    |2       |Business     |