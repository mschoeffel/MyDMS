package com.mschoeffel.mydms.repository;

import com.mschoeffel.mydms.model.Type;
import com.mschoeffel.mydms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, String> {
}
