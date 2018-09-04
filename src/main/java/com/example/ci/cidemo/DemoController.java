package com.example.ci.cidemo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class DemoController {

    public static final int PAYLOAD_LENGTH = 100;
    public static final int SLIGHT_DELAY_MS = 100;

    @GetMapping(
            value = "/demo",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Mono<DemoModel> getDemo() {
        return Mono.delay(Duration.ofMillis(SLIGHT_DELAY_MS))
                .then(Mono.just(
                        new DemoModel(RandomUtils.nextLong(),
                                RandomStringUtils.randomAlphanumeric(PAYLOAD_LENGTH)))
                );
    }
}
