package com.example.jobagapi.cucumber;

import com.example.jobagapi.domain.model.Interview;
import com.example.jobagapi.domain.model.Postulant;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LinkInterviewSteps {

    @LocalServerPort

    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="http://jobagbackend-env.eba-uqwxesqd.us-east-2.elasticbeanstalk.com";
    private Long interviewId=1L;
    private String message = "successful access to the platform";
    private String expectet = "successful access to the platform";

    @Given("I am in the interview section")
    public void iAmInTheInterviewSection() {
        String url=postUrl + "/api/interviews/";
        String allInterviews=restTemplate.getForObject(url, String.class);
        log.info(allInterviews);
        assertTrue(!allInterviews.isEmpty());
    }

    public String randomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }

    @And("I register the interview link {string}")
    public void i_register_the_interview_link(String link) {

        String url = postUrl + "/api/interviews/";

        LocalDate data=LocalDate.now();

        Postulant newpostulant = new Postulant();

        log.info(link);

        Interview newInterview = new Interview();

        newInterview.setDate_Interview(data);
        newInterview.setFinal_date_Interview(data);
        newInterview.setLink_Interview(link);
        newInterview.setPostulant(newpostulant);
    }

    @Then("I want to see the message {string}")
    public void i_want_to_see_the_message(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(expectet, message);
    }

}
