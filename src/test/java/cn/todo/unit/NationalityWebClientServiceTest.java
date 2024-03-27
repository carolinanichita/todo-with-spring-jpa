package cn.todo.unit;

import cn.todo.services.NationalityWebClientService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

public class NationalityWebClientServiceTest {
    private static MockWebServer webServer;
    private NationalityWebClientService nationalityService;

    @BeforeAll
    public static void init() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @BeforeEach
    public void initialize() {
        nationalityService = new NationalityWebClientService();
    }

    @Test
    public void givenWebServerIsMocked_whenPredictNationality_thenReceiveTheResponse() throws InterruptedException{
        String baseUrl = String.format("http://127.0.0.1:%s", webServer.getPort());
        String expectedResp = "{'name':'anshul','country':[{'country_id':'IN','probability':1}]}";

        webServer.enqueue(new MockResponse()
                .setBody(expectedResp)
                .addHeader("Content-Type", "application/json"));
        Mono<String> stringMono = nationalityService.predictNationality(baseUrl, "anshul");

        StepVerifier.create(stringMono)
                .expectNextMatches(response -> response.toLowerCase().equals(expectedResp.toLowerCase()))
                .verifyComplete();
        RecordedRequest recordedRequest = webServer.takeRequest();

        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/?name=anshul", recordedRequest.getPath());
    }
}
