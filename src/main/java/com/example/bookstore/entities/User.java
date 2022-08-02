package com.example.bookstore.entities;


import com.example.bookstore.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name can't be Empty")
    @Size(min = 3, max = 20, message = "name should between 3 to 20 character")
    private String name;
    @Email(message = "must be an email")
    @NotEmpty(message = "can't be empty")
    @Column(unique = true)
    private String email;
    @Size(min = 5, max = 60)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType role;
    @OneToMany
    private List<ShippingAddress> shippingAddressList;


}
