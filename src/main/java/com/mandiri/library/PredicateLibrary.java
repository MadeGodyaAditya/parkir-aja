package com.mandiri.library;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Locale;

public interface PredicateLibrary<T> {

    default Predicate containsPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, String columnName, String searchFormString) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get((java.lang.String) columnName)), "%"+ searchFormString.toString().toLowerCase(Locale.ROOT)+"%");
    }
}
