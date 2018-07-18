package io.foo.hateoas.assembler;

import io.foo.hateoas.controller.StudentController;
import io.foo.hateoas.model.Student;
import io.foo.hateoas.resource.StudentResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StudentResourceAssembler extends ResourceAssemblerSupport<Student, StudentResource> {

    public StudentResourceAssembler() {
        super(StudentController.class, StudentResource.class);
    }

    @Override
    public StudentResource toResource(Student student) {
        final StudentResource studentResource = new StudentResource();
        studentResource.setStudentId(student.getId());
        studentResource.setName(student.getName());
        studentResource.setPassportNumber(student.getPassportNumber());

        return studentResource;
    }
}
