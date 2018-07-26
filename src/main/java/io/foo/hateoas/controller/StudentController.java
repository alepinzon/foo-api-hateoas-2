package io.foo.hateoas.controller;

import io.foo.hateoas.assembler.StudentResourceAssembler;
import io.foo.hateoas.model.Student;
import io.foo.hateoas.repository.StudentRepository;
import io.foo.hateoas.repository.specification.StudentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentResourceAssembler studentResourceAssembler;

    public StudentController(StudentRepository studentRepository, StudentResourceAssembler studentResourceAssembler) {
        this.studentRepository = studentRepository;
        this.studentResourceAssembler = studentResourceAssembler;
    }

    @GetMapping(path = "/students", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public PagedResources getAll(@RequestParam(value = "name", required = false) String name,
                                 @SortDefault(sort = "id", direction = DESC) Pageable pageable,
                                 PagedResourcesAssembler<Student> assembler) {
        final Link link = new Link(fromCurrentRequest().build().toString());

        final Page<Student> page = studentRepository.findAll(new StudentSpecification(name), pageable);

        return assembler.toResource(page, studentResourceAssembler, link);
    }

    @GetMapping(path = "/students-templated", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public PagedResources getAllTemplated(@RequestParam(value = "name", required = false) String name,
                                          @SortDefault(sort = "id", direction = DESC) Pageable pageable,
                                          PagedResourcesAssembler<Student> assembler) {
        final Link link = linkTo(ControllerLinkBuilder.methodOn(StudentController.class)
                .getAllTemplated(name, pageable, assembler)).withSelfRel();

        final Page<Student> page = studentRepository.findAll(new StudentSpecification(name), pageable);

        return assembler.toResource(page, studentResourceAssembler, link);
    }
}
