package cn.todo.unit;

import cn.todo.services.NationalityRestTemplateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RestClientTest(NationalityRestTemplateService.class)
public class NationalityRestTemplateServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NationalityRestTemplateService nationalityRestTemplateService;

    private MockRestServiceServer server;

    @BeforeEach
    public void init() {
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void givenServerIsMocked_whenPredictNationality_thenReceiveTheResponse() {
        this.server.expect(requestTo("https://api.nationalize.io?name=anshul"))
                .andRespond(withSuccess("{'name':'anshul','country':[{'country_id':'IN','probability':1}]}", MediaType.APPLICATION_JSON));

        String userServiceResponse = nationalityRestTemplateService.predictNationality("anshul");

        Assertions.assertEquals("{'name':'anshul','country':[{'country_id':'IN','probability':1}]}", userServiceResponse);
    }

}
