Feature: ProfessionalProfile functionality

  Scenario Outline: As a Postulant i want to add to my professionalprofile my skills.
    Given I want my professionalprofile
    And I add my new skill <skill>
    Then I should be able to see <mensaje>

    Examples:
      | skill                  | mensaje                                     |
      | "Ingles avanzado 5"    | "Se agrego la habilidad correctamente"      |