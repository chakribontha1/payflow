package com.example.PayFlow.payment.repository;

import com.example.PayFlow.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface paymentRepository extends JpaRepository<Payment, UUID> {
}
