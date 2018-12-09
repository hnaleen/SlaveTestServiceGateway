package com.hasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import se.cambio.qa.multiprocess.testframework.dto.TestCaseResultDTO;

/**
 * - TestServiceGateway -
 * @author Hasantha Alahakoon 
 */
@SpringBootApplication @RestController @RequestMapping(value = "/v1/tests") @RibbonClient(name = "slavetestservice", configuration = LoadBalancerConfiguration.class) public class Application
{
  @LoadBalanced @Bean public RestTemplate getRestTemplate()
  {
    return new RestTemplate();
  }

  @Autowired RestTemplate restTemplate;

  @RequestMapping(value = "/{testClass}/{testMethodName}", method = RequestMethod.GET) public TestCaseResultDTO run(
      @PathVariable("testClass") String testClass, @PathVariable("testMethodName") String testMethodName)
  {
    ResponseEntity<TestCaseResultDTO> result = restTemplate
        .exchange("http://slavetestservice/v1/tests/{testClass}/{testMethodName}", HttpMethod.GET, null,
            TestCaseResultDTO.class, testClass, testMethodName);
    return result.getBody();
  }

  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
  }
}
