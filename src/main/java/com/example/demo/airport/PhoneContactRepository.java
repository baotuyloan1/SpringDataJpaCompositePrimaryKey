package com.example.demo.airport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneContactRepository extends JpaRepository<PhoneContact, PhoneID> {
}
