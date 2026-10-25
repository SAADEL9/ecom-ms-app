package com.saad.ebankservice.entities;
import com.saad.ebankservice.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Date;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class BankAccount{
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String type;
    private long customerId;
    @Transient
    private Customer customer;

}
