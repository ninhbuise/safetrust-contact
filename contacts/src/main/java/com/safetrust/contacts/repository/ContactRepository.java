package com.safetrust.contacts.repository;

import com.safetrust.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    public List<Contact> findByEmail(String email);
    public List<Contact> findByPhone(String email);
}
