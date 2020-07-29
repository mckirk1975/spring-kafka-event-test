package com.saturnitservices.eventframework.domainacceptancetest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static io.cucumber.core.cli.Main.run;

@RestController
public class AcceptanceTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptanceTestController.class);

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, String> status() {

        LOGGER.info(" --> status requested");
        Map<String, String> result = new HashMap<>();
        result.put("status", "ok");
        return result;
    }

    @PutMapping("/run")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void runTests() {

        LOGGER.info(" --> run tests requested");

        // TODO: make test run async, provide link to results

        String[] args = {"classpath:features",
                         "--glue", "com.saturnitservices.eventframework.domainacceptancetest.glue",
                         "--strict"};

        byte exitStatus = run(args, Thread.currentThread().getContextClassLoader());

        LOGGER.info(" <-- run tests exit status {}", exitStatus);

    }
}
