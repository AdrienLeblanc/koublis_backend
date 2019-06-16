package com.koublis.controller;

import com.koublis.entities.Wine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WineController {

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Wine addWine(@RequestParam(value="name", defaultValue="appellation") String name) {
        return new Wine(counter.incrementAndGet(),
                String.format(template, name));
    }
}
