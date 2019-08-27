package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Type;
import com.mschoeffel.mydms.model.User;
import com.mschoeffel.mydms.repository.TypeRepository;
import com.mschoeffel.mydms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type findById(String short_name) {
        Optional<Type> type = typeRepository.findById(short_name);
        return type.orElse(null);
    }

    @Override
    public boolean existsId(String short_name) {
        return typeRepository.existsById(short_name);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void deleteById(String short_name) {
        typeRepository.deleteById(short_name);
    }

    @Override
    public List<Type> findAllOrderByName() {
        return typeRepository.findAll(Sort.by(Sort.Order.asc("name")));
    }
}
