Feature: Register a new Postulant

  Scenario Outline: Postulant is unable to register
    Given the postulant enters the application
    And you press the register option and it does not fill in the data properly <phone>
    Then I should be able to see <error>

    Examples:
      | phone  |  error                                                                     |
      | 0      | "el proceso de registro no se realiza porque debe digitar datos válidos"   |

  Scenario Outline: The postulant enters a wrong or not allowed registration data
    Given the postulant is filling in registration data
    And enter a piece of information that does not meet the requirements <email>
    Then you are shown a message that a registration data is wrong <error>
    Examples:
      | email                   |  error                                                                     |
      | "mark@htomail.com"      | "el correo electronico ingresado no es valido, intente de nuevo"           |

  Scenario Outline: The psotulant did not accept the terms and conditions
    Given the postulant is completing his registration
    And does not accept the terms and conditions of account registration
    Then you are not allowed to register and you are asked to accept the terms <error>
    Examples:
      |  error                                                                     |
      | "no se puedo crear la cuenta, por favor acepte los teminos y condiciones"  |