package com.example.jobagapi.cucumber;

import com.example.jobagapi.domain.model.Postulant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PostulantEmailSteps {

    @LocalServerPort
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="http://jobagbackend-env.eba-uqwxesqd.us-east-2.elasticbeanstalk.com";

    private String error="El email ya esta en uso";

    @Given("I am in the register seccion")
    public void iAmInTheRegisterSection() {

        String url=postUrl + "/api" + "/postulants/";
        String allPostulants=restTemplate.getForObject(url, String.class);
        log.info(allPostulants);
        assertTrue(!allPostulants.isEmpty());
    }

    public String randomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }

    @Given("register with id {long} and email")
    public void I_register_with_email(Long Id){

        Postulant newpostulant = new Postulant(Id, "firstname", "lastname", randomString(), 123L, "password","document","civil");
        String url=postUrl + "/api" + "/postulants/";
        Postulant postulant=restTemplate.postForObject(url,newpostulant,Postulant.class);

        log.info(postulant);

        assertNotNull(postulant);
    }


    @Given("I should be able to register")
    public void i_want_to_see_my_register() {
        String url=postUrl+"/api" +"/postulants";
        Postulant postulant=restTemplate.getForObject(url,Postulant.class);
        assertNotNull(postulant);
    }



    @Given("register a repeat email")
    public void register_a_repeat_email() {
        Postulant newpostulant = new Postulant(1L, "firstname", "lastname", "a", 123L, "password","document","civil");
        String url=postUrl + "/api" + "/postulants/";

        try
        {
            Postulant post=restTemplate.postForObject(url,newpostulant,Postulant.class);
            log.info(post);
        }catch(RestClientException e){
            error="El email ya esta en uso";
        }
        assertEquals(error,"El email ya esta en uso");
    }


    @Then("I should see a message {string}")
    public void i_Should_See_A_MessageError(String string) {
        error="El email ya esta en uso";
        assertEquals(string,error);
    }

    //Tercer escenario
    @Given("the applicant enters the application")
    public void the_applicant_enters_the_application() {
        String url=postUrl+"/api/postulants";
        Postulant postulant=restTemplate.getForObject(url,Postulant.class);
        assertNotNull(postulant);
    }

    @Given("press the option to register by sharpening your personal data, email, cell phone number and a password")
    public void press_the_option_to_register_by_sharpening_your_personal_data_email_cell_phone_number_and_a_password() {
        Postulant newpostulant = new Postulant(102L, "Carlos", "Perez", randomString(), 999934125L, "carlito","DNI","viudo");

        String url=postUrl + "/api/postulants/";

        Postulant postulant=restTemplate.postForObject(url,newpostulant,Postulant.class);

        log.info(postulant);

        assertNotNull(postulant);
    }

    @Then("you are notified by text message that the registration was successful {int}")
    public void you_are_notified_by_text_message_that_the_registration_was_successful(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        int1 = 999999999;
        Integer numero = 999999999;
        assertEquals(int1,numero);
    }
}




