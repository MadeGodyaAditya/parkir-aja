package com.mandiri.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface DeleteController<ID> {
    @DeleteMapping
    public void delete(@RequestParam ID id);
}
