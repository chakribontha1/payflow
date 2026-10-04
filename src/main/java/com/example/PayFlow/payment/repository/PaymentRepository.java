package com.example.PayFlow.payment.repository;

import com.example.PayFlow.payment.entity.OrderRecord;
import com.example.PayFlow.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByOrder(OrderRecord orderRecord);


    List<Payment> findByOrder_Id(OrderRecord order);
}