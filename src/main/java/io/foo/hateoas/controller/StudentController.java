package io.foo.hateoas.controller;

import io.foo.hateoas.assembler.StudentResourceAssembler;
import io.foo.hateoas.model.Student;
import io.foo.hateoas.repository.StudentRepository;
import io.foo.hateoas.resource.StudentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentResourceAssembler studentResourceAssembler;

    public StudentController(StudentRepository studentRepository, StudentResourceAssembler studentResourceAssembler) {
        this.studentRepository = studentRepository;
        this.studentResourceAssembler = studentResourceAssembler;
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public PagedResources<StudentResource> getAll(Pageable pageable,
                                                  PagedResourcesAssembler<Student> assembler) {
        final Page<Student> page = studentRepository.findAll(pageable);
        return assembler.toResource(page, studentResourceAssembler);
    }
}
