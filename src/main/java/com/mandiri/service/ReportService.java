package com.mandiri.service;

import com.mandiri.dto.CustomPage;
import com.mandiri.dto.ParkingOwnerSearchForm;
import com.mandiri.entity.ParkingOwner;
import com.mandiri.entity.Report;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ReportRepository;
import com.mandiri.specification.ParkingOwnerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements CreateReadService<Report, String> {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public void checkId(String s) {
        if(!reportRepository.existsById(s)) {
            CustomException.throwNotFound(this.getClass().getSimpleName(), s);
        }
    }

    @Override
    public Report register(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report findById(String s) {
        checkId(s);

        return reportRepository.findById(s).get();
    }

    public CustomPage<Report> find(Pageable pageable){
        Page<Report> pageData = reportRepository.findAll(pageable);
        return new CustomPage<Report>(pageData);
    }

}
