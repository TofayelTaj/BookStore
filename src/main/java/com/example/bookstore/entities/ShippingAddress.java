package com.example.bookstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull()
    @Size(min = 4, max = 100, message = "Address must be between 4 to 100 character")
    private String addressLine;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


}
