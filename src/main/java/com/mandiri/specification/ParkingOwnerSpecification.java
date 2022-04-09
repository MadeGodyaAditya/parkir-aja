package com.mandiri.specification;

import com.mandiri.dto.ParkingOwnerSearchForm;
import com.mandiri.entity.ParkingOwner;
import com.mandiri.library.PredicateLibrary;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ParkingOwnerSpecification implements Specification<ParkingOwner>, PredicateLibrary<ParkingOwner> {
    ParkingOwnerSearchForm parkingOwnerSearchForm;

    public ParkingOwnerSpecification(ParkingOwnerSearchForm parkingOwnerSearchForm) {
        this.parkingOwnerSearchForm = parkingOwnerSearchForm;
    }

    @Override
    public Predicate toPredicate(Root<ParkingOwner> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (parkingOwnerSearchForm.getIdCard()!=null){
            predicates.add(containsPredicate(criteriaBuilder, root, "idCard", parkingOwnerSearchForm.getIdCard()));
        }
        if (parkingOwnerSearchForm.getName()!=null){
           predicates.add(containsPredicate(criteriaBuilder, root, "name", parkingOwnerSearchForm.getName()));
        }
        if (parkingOwnerSearchForm.getAddress()!=null){
            predicates.add(containsPredicate(criteriaBuilder, root, "address", parkingOwnerSearchForm.getAddress()));
        }
        if (parkingOwnerSearchForm.getPhoneNumber()!=null){
            predicates.add(containsPredicate(criteriaBuilder, root, "phoneNumber", parkingOwnerSearchForm.getPhoneNumber()));
        }

        Predicate[] arrayPredicate = predicates.toArray(new Predicate[predicates.size()]);
        return criteriaBuilder.and(arrayPredicate);
    }
}
