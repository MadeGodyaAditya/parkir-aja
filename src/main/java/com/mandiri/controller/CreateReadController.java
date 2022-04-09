package com.mandiri.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CreateReadController<T, ID> {
    @PostMapping
    public T register(@Valid @RequestBody T t);

    @GetMapping("/{id}")
    public T findById(@PathVariable ID id);
}
