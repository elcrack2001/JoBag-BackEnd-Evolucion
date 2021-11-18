package com.example.jobagapi.cucumber;

import com.example.jobagapi.domain.model.Employeer;
import com.example.jobagapi.domain.model.JobOffer;
import com.example.jobagapi.domain.model.Postulant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Random;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;


@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplyJobSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="http://jobagbackend-env.eba-uqwxesqd.us-east-2.elasticbeanstalk.com";
    private Long jobOfferId=1L;

    Employeer employeer = new Employeer(105L, "raul", "gonzales", "gonzales@gmail.com", 949033224L, "raul", "DNI", "CEO");
    private String error="El salario debe ser mayor o igual a 930";

    @Given("I want a new job")
    public void iWantANewJob() {
        String url=postUrl + "/api/employeers/" + employeer.getId() + "/joboffers";
        String allJobs=restTemplate.getForObject(url, String.class);
        log.info(allJobs);
        assertTrue(!allJobs.isEmpty());
    }

    public String randomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }


    @Given("The job offer have a stable minimum {long}")
    public void the_job_offer_have_a_stable_minimum_and(Long salary) {

        String url=postUrl + "/api/employeers/" + employeer.getId() + "/joboffers";

        LocalDate data=LocalDate.now();

        Employeer employeer=new Employeer();

        log.info(salary);

        JobOffer newJob= new JobOffer();

        newJob.setId(jobOfferId);
        newJob.setSalary(salary);
        newJob.setDescription("asd");
        newJob.setBegin_date_offer(data);
        newJob.setFinal_date_offer(data);
        newJob.setDirection("aea");
        newJob.setEmployeer(employeer);

        try
        {
            JobOffer job=restTemplate.postForObject(url,newJob,JobOffer.class);
            log.info(job);
        }catch(RestClientException e){
            error="El salario debe ser mayor o igual a 930";
        }
        assertEquals(error,"El salario debe ser mayor o igual a 930");
    }


    @Then("I should be able to see my newly job")
    public void iShouldBeAbleToSeeMyNewlyJob() {
        String url=postUrl + "/api/employeers/" + employeer.getId() + "/joboffers";
        JobOffer job=restTemplate.getForObject(url,JobOffer.class);
        assertNotNull(job);
    }

    @Given("The job offer have a low minimum {long}")
    public void the_job_offer_have_a_low_minimum(Long salary) {
        String url=postUrl + "/api/employeers/" + employeer.getId() + "/joboffers";
        LocalDate data=LocalDate.now();
        Employeer employeer=new Employeer();
        log.info(salary);
        JobOffer newJob= new JobOffer();
        newJob.setId(jobOfferId);
        newJob.setSalary(salary);
        newJob.setDescription("asd");
        newJob.setBegin_date_offer(data);
        newJob.setFinal_date_offer(data);
        newJob.setDirection("aea");
        newJob.setEmployeer(employeer);

        try
        {
            JobOffer job=restTemplate.postForObject(url,newJob,JobOffer.class);
            log.info(job);
        }catch(RestClientException e){
            error="El salario debe ser mayor o igual a 930";
        }
        assertEquals(error,"El salario debe ser mayor o igual a 930");
    }

    @Then("I should be able to see {string}")
    public void i_should_be_able_to_see(String errorMessage) {
        errorMessage = "El salario debe ser mayor o igual a 930";
        assertEquals(errorMessage,error);
    }

}
