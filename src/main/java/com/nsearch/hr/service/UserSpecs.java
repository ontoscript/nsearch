package com.nsearch.hr.service;

import com.nsearch.hr.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecs {
    public static Specification<User> hasMinimumSalary(float minSalary){
        return new Specification<User>() {
            public Predicate toPredicate(
                    Root<User> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
                return builder.greaterThanOrEqualTo(root.get("salary"), minSalary);
            }
        };
    }
    public static Specification<User> hasMaximumSalary(float maxSalary){
        return new Specification<User>() {
            public Predicate toPredicate(
                    Root<User> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
                return builder.lessThanOrEqualTo(root.get("salary"), maxSalary);
            }
        };
    }
}
