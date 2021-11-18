Feature: Postulant functionality

  Scenario Outline: As a Postulant i want to register with my email.
    Given I am in the register seccion
    And register with id <id> and email
    Then I should be able to register
    Examples:
      |id|
      |1|

  Scenario Outline: As a Postulant i want to see a error if the email is in use.
    Given I am in the register seccion
    And register a repeat email
    Then I should see a message <errors>
    Examples:
     |errors|
     |"El email ya esta en uso"|

  Scenario Outline: The applicant postulant to register successfully
    Given the applicant enters the application
    And press the option to register by sharpening your personal data, email, cell phone number and a password
    Then you are notified by text message that the registration was successful <number>

    Examples:
      |number|
      |999999999|
