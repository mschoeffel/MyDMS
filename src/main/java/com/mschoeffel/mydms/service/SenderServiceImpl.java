package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Sender;
import com.mschoeffel.mydms.model.Tag;
import com.mschoeffel.mydms.repository.SenderRepository;
import com.mschoeffel.mydms.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SenderServiceImpl implements SenderService {

    private SenderRepository senderRepository;

    @Autowired
    public SenderServiceImpl(SenderRepository senderRepository){
        this.senderRepository = senderRepository;
    }


    @Override
    public List<Sender> findAll() {
        return senderRepository.findAll();
    }

    @Override
    public Sender findById(Integer id) {
        Optional<Sender> sender =  senderRepository.findById(id);
        return sender.orElse(null);
    }

    @Override
    public boolean existsId(Integer id){ return senderRepository.existsById(id); }

    @Override
    public Sender save(Sender sender) {
        return senderRepository.save(sender);
    }

    @Override
    public void deleteById(Integer id) {
        senderRepository.deleteById(id);
    }
}
