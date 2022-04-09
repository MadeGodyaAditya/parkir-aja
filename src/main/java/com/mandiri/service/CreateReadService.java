package com.mandiri.service;

public interface CreateReadService<T, ID> {
    public void checkId(ID id);
    public T register(T t);
    public T findById(ID id);
}
