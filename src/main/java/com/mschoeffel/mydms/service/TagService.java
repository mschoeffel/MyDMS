package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Tag;
import com.mschoeffel.mydms.model.Type;

import java.util.List;

public interface TagService {

    public List<Tag> findAll();

    public Tag findById(String tag);

    public Tag save(Tag tag);

    public boolean existsId(String tag);

    public void deleteById(String tag);

}
