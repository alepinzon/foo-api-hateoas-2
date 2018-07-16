package io.foo.hateoas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class FooResource {

    private String message;
    private Date date;

}
