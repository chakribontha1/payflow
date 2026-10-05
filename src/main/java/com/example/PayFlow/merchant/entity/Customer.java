package com.example.PayFlow.merchant.entity;

import com.example.PayFlow.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

@Entity
@Table(name = "customer",indexes = {
        @Index(name = "idx_customer_merchant_id", columnList = "merchant_id"),
        @Index(name = "idx_customer_email", columnList = "email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchant;
//     @Query("SELECT c FROM Customer c WHERE c.merchant.id = :merchantId")
    @Column(length = 100)
    private String name;

    @Column(length = 200)
    private String email;

    @Column(length = 20)
    private String ContactNumber;

}
