package io.foo.hateoas.repository.specification;

import io.foo.hateoas.model.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

public class StudentSpecification implements Specification<Student> {

    private String name;

    public StudentSpecification(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        if (!isEmpty(name)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
        }

        final Predicate[] predicatesArray = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(predicatesArray));
    }
}
