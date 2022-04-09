package com.mandiri.service;

import com.mandiri.entity.Report;
import com.mandiri.library.CustomException;
import com.mandiri.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements CreateReadService<Report, String>, UpdateService<Report>, DeleteService<String> {

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

    @Override
    public Report update(Report report) {
        checkId(report.getId());

        return reportRepository.save(report);
    }

    @Override
    public void delete(String s) {
        checkId(s);

        reportRepository.deleteById(s);
    }

}
