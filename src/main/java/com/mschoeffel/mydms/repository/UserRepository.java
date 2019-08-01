package com.mschoeffel.mydms.repository;

import com.mschoeffel.mydms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, String> {
}
