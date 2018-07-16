package io.foo.hateoas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController(value = "foo")
public class FooController {

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public FooResource get(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new FooResource("Hello " + name, new Date());
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public FooResource post(@RequestBody FooRequest body) {
        return new FooResource("Hello World", body.getFooDate());
    }
}
