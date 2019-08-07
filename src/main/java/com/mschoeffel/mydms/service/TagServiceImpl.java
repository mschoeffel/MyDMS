package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Tag;
import com.mschoeffel.mydms.model.Type;
import com.mschoeffel.mydms.repository.TagRepository;
import com.mschoeffel.mydms.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(String tag) {
        Optional<Tag> type = tagRepository.findById(tag);
        return type.orElse(null);
    }

    @Override
    public boolean existsId(String tag) {
        return tagRepository.existsById(tag);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteById(String tag) {
        tagRepository.deleteById(tag);
    }
}
