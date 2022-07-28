package com.example.bookstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @ManyToOne()
    @JoinColumn(
            name = "customer_id"
    )
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;

}
