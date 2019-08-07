package com.mschoeffel.mydms.repository;

import com.mschoeffel.mydms.model.Sender;
import com.mschoeffel.mydms.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenderRepository extends JpaRepository<Sender, Integer> {
}
