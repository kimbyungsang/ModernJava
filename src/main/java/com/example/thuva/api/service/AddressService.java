package com.example.thuva.api.service;

import com.example.thuva.api.entity.AddressEntity;
import com.example.thuva.api.model.AddAddressReq;

import java.util.Optional;

public interface AddressService {

    Optional<AddressEntity> createAddressByReq(AddAddressReq addAddressReq);
    void deleteAddressById(String id);
    Optional<AddressEntity> getAddressById(String id);
    Iterable<AddressEntity> getAllAddresses();
}
