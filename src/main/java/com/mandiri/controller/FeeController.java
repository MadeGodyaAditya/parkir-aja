package com.mandiri.controller;

import com.mandiri.entity.Fee;
import com.mandiri.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fee")
public class FeeController implements CreateReadController<Fee,String>, UpdateController<Fee>, DeleteController<String> {
    @Autowired
    FeeService feeService;

    @Override
    public Fee register(Fee fee) {
        return feeService.register(fee);
    }

    @CrossOrigin
    @Override
    public Fee findById(String s) {
        return feeService.findById(s);
    }

    @Override
    public Fee update(Fee fee) {
        return feeService.update(fee);
    }

    @Override
    public void delete(String s) {
        feeService.delete(s);
    }
}
