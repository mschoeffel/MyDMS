package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Sender;
import com.mschoeffel.mydms.model.Tag;

import java.util.List;

public interface SenderService {

    public List<Sender> findAll();

    public Sender findById(Integer id);

    public Sender save(Sender sender);

    public boolean existsId(Integer id);

    public void deleteById(Integer id);

}
