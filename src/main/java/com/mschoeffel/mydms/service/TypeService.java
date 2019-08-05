package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Type;
import com.mschoeffel.mydms.model.User;

import java.util.List;

public interface TypeService {

    public List<Type> findAll();

    public Type findById(String short_name);

    public Type save(Type type);

    public boolean existsId(String short_name);

    public void deleteById(String short_name);

}
