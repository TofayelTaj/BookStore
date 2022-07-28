package com.example.bookstore.services;

import com.example.bookstore.entities.ShippingAddress;
import com.example.bookstore.repositories.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;


    public void saveShippingAddress(ShippingAddress address){
        shippingAddressRepository.save(address);
    }


}
