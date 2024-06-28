package com.example.thuva.api.service;

import com.example.thuva.api.entity.AddressEntity;
import com.example.thuva.api.exceptions.ResourceNotFoundException;
import com.example.thuva.api.model.AddAddressReq;
import com.example.thuva.api.model.Address;
import com.example.thuva.api.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<AddressEntity> createAddressByReq(AddAddressReq addAddressReq) {
        return Optional.of(repository.save(toEntity(addAddressReq)));
    }

    @Override
    public void deleteAddressById(String id) {
        repository
                .findById(UUID.fromString(id))
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("No Address found with id %s.", id)));
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<AddressEntity> getAddressById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    @Override
    public Iterable<AddressEntity> getAllAddresses() {
        return repository.findAll();
    }

    private AddressEntity toEntity(AddAddressReq model) {
        AddressEntity entity = new AddressEntity();
        return entity
                .setNumber(model.getNumber())
                .setStreet(model.getStreet())
                .setCity(model.getCity())
                .setState(model.getState())
                .setCountry(model.getCountry())
                .setPincode(model.getPincode());
    }
}
