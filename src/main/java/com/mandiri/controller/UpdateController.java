package com.mandiri.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UpdateController<T> {
    @PutMapping
    public T update(@Valid @RequestBody T t);
}
