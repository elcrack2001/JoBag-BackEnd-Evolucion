package com.example.jobagapi.cucumber;

import com.example.jobagapi.domain.model.Postulant;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterPostulantStep {

    @LocalServerPort
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="http://jobagbackend-env.eba-uqwxesqd.us-east-2.elasticbeanstalk.com";
    private Long phone = 0L;
    private String email = "mark@htomail.com";
    private String error = "el correo electronico ingresado no es valido, intente de nuevo";
    private boolean accept = false;
    private String errorCreate = "no se puedo crear la cuenta, por favor acepte los teminos y condiciones";

    Postulant postulantId = new Postulant(104L, "maria", "alcantara", "marisa@gmail.com", 949790334L, "mariluz", "DNI", "soltera");

    @Given("the postulant enters the application")
    public void thePostulantEntersTheApplication() {
        String url = postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";
        String allJobs=restTemplate.getForObject(url, String.class);
        log.info(allJobs);
        assertTrue(!allJobs.isEmpty());
    }

    @Given("you press the register option and it does not fill in the data properly {int}")
    public void you_press_the_register_option_and_it_does_not_fill_in_the_data_properly(Integer int1) {
        String url=postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";

        log.info(phone);

        Postulant postulant = new Postulant();

        postulant.setId(postulantId.getId());
        postulant.setFirstname(postulantId.getFirstname());
        postulant.setLastname(postulantId.getLastname());
        postulant.setEmail(postulantId.getEmail());
        postulant.setNumber(phone);
        postulant.setPassword(postulantId.getPassword());
        postulant.setDocument(postulantId.getDocument());
        postulant.setCivil_status(postulantId.getCivil_status());
    }


//Segundo test

    @Given("the postulant is filling in registration data")
    public void thePostulantIsFillingInRegistrationData() {
        String url = postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";
        String allJobs=restTemplate.getForObject(url, String.class);
        log.info(allJobs);
        assertTrue(!allJobs.isEmpty());
    }

    @Given("enter a piece of information that does not meet the requirements {string}")
    public void enter_a_piece_of_information_that_does_not_meet_the_requirements(String string) {
        String url=postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";

        log.info(email);

        Postulant postulant = new Postulant();

        postulant.setId(postulantId.getId());
        postulant.setFirstname(postulantId.getFirstname());
        postulant.setLastname(postulantId.getLastname());
        postulant.setEmail(email);
        postulant.setNumber(postulantId.getNumber());
        postulant.setPassword(postulantId.getPassword());
        postulant.setDocument(postulantId.getDocument());
        postulant.setCivil_status(postulantId.getCivil_status());
    }
    @Then("you are shown a message that a registration data is wrong {string}")
    public void you_are_shown_a_message_that_a_registration_data_is_wrong(String string) {
        string = "el correo electronico ingresado no es valido, intente de nuevo";
        assertEquals(string,error);
    }


    // Tercer test

    @Given("the postulant is completing his registration")
    public void thePostulantIsCompletingHisRegistration() {
        String url = postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";
        String allJobs=restTemplate.getForObject(url, String.class);
        log.info(allJobs);
        assertTrue(!allJobs.isEmpty());
    }

    @And("does not accept the terms and conditions of account registration")
    public void doesNotAcceptTheTermsAndConditionsOfAccountRegistration() {
        String url=postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";

        log.info(accept);

        Postulant postulant = new Postulant();

        postulant.setId(postulantId.getId());
        postulant.setFirstname(postulantId.getFirstname());
        postulant.setLastname(postulantId.getLastname());
        postulant.setEmail(postulantId.getEmail());
        postulant.setNumber(postulantId.getNumber());
        postulant.setPassword(postulantId.getPassword());
        postulant.setDocument(postulantId.getDocument());
        postulant.setCivil_status(postulantId.getCivil_status());
    }

    @Then("you are not allowed to register and you are asked to accept the terms {string}")
    public void you_are_not_allowed_to_register_and_you_are_asked_to_accept_the_terms(String string) {
        string = "no se puedo crear la cuenta, por favor acepte los teminos y condiciones";
        assertEquals(string,errorCreate);
    }
}
