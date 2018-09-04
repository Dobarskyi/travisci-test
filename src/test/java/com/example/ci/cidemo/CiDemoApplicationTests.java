package com.example.ci.cidemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CiDemoApplicationTests {

    static final int TIMEOUT_SECONDS = 2;

    WebClient webClient;

    @Before
    public void setUpWebClient() {
        webClient = WebClient.create("http://localhost:8080");
    }

    @Test
    public void shouldGetDemoModel() {

        ClientResponse response = webClient
                .get()
                .uri("/demo")
                .exchange()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));


        assertThat(response)
                .isNotNull()
                .extracting(clientResponse ->

                        clientResponse
                                .toEntity(DemoModel.class)
                                .block(Duration.ofSeconds(TIMEOUT_SECONDS))
                                .getBody()

                )
                .extracting("id", "payload")
                .doesNotContain(0L, null, "null");
    }
}
