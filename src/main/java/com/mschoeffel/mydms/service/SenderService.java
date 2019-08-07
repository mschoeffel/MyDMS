package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Sender;
import com.mschoeffel.mydms.model.Tag;

import java.util.List;

public interface SenderService {

    public List<Sender> findAll();

    public Sender findById(int id);

    public Sender save(Sender sender);

    public boolean existsId(int id);

    public void deleteById(int id);

}
