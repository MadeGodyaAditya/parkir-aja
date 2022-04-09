package com.mandiri.specification;

import com.mandiri.dto.ParkingLotSearchForm;
import com.mandiri.entity.ParkingLot;
import com.mandiri.library.PredicateLibrary;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotSpecification implements Specification<ParkingLot>, PredicateLibrary<ParkingLot> {
    ParkingLotSearchForm parkingLotSearchForm;

    public ParkingLotSpecification(ParkingLotSearchForm parkingLotSearchForm) {
        this.parkingLotSearchForm = parkingLotSearchForm;
    }

    @Override
    public Predicate toPredicate(Root<ParkingLot> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (parkingLotSearchForm.getOwnerId()!=null){
            predicates.add(containsPredicate(criteriaBuilder, root, "ownerId", parkingLotSearchForm.getOwnerId()));
        }
        if (parkingLotSearchForm.getName()!=null){
            predicates.add(containsPredicate(criteriaBuilder, root, "name", parkingLotSearchForm.getName()));
        }
        if (parkingLotSearchForm.getAddress()!=null){
            predicates.add(containsPredicate(criteriaBuilder, root, "address", parkingLotSearchForm.getAddress()));
        }
        if (parkingLotSearchForm.getCapacityBottomLimit()!=null){
            predicates.add(greaterThanEqualIntPredicate(criteriaBuilder, root, "capacity", parkingLotSearchForm.getCapacityBottomLimit()));
        }
        if (parkingLotSearchForm.getCapacityTopLimit()!=null){
            predicates.add(lessThanEqualIntPredicate(criteriaBuilder, root, "capacity", parkingLotSearchForm.getCapacityTopLimit()));
        }
        if (parkingLotSearchForm.getCategory()!=null){
            predicates.add(containsPredicate(criteriaBuilder, root, "category", parkingLotSearchForm.getCategory()));
        }
//        if (parkingLotSearchForm.getFeeBottomLimit()!=null){
//            predicates.add(greaterThanEqualIntPredicate(criteriaBuilder, root, "size", parkingLotSearchForm.getFeeBottomLimit()));
//        }
//        if (parkingLotSearchForm.getFeeTopLimit()!=null){
//            predicates.add(lessThanEqualIntPredicate(criteriaBuilder, root, "size", parkingLotSearchForm.getFeeTopLimit()));
//        }

        Predicate[] arrayPredicate = predicates.toArray(new Predicate[predicates.size()]);
        return criteriaBuilder.and(arrayPredicate);
    }
}
