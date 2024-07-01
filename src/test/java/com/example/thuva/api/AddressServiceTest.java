package com.example.thuva.api;

import com.example.thuva.api.entity.AddressEntity;
import com.example.thuva.api.exceptions.ResourceNotFoundException;
import com.example.thuva.api.model.AddAddressReq;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;


import com.example.thuva.api.service.AddressServiceImpl;
import com.example.thuva.api.repository.AddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    private final static String id = "a1b9b31d-e73c-4112-af7c-b68530f38222";
    private final static String nonExistId = "a1b9b31d-e73c-4112-af7c-b68530f38220";
    private static AddressEntity entity;
    private static AddAddressReq addAddressReq;

    @Mock
    private AddressRepository repository;

    @InjectMocks
    private AddressServiceImpl service;

    @BeforeAll
    public static void setup() {
        entity = new AddressEntity()
                .setId(UUID.fromString(id))
                .setNumber("111")
                .setStreet("Street")
                .setCity("City")
                .setState("State")
                .setCountry("Country")
                .setPincode("12345");
        addAddressReq = new AddAddressReq()
                .id(entity.getId().toString())
                .number(entity.getNumber())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .pincode(entity.getPincode());
    }

    @Test
    @DisplayName("return an AddressEntity when private metjhod toEntity() is called with Addres model")
    public void convertModelToEntry() {

        //given
        AddressServiceImpl srvc = new AddressServiceImpl(repository);

        //when
        AddressEntity e = ReflectionTestUtils.invokeMethod(srvc, "toEntity", addAddressReq);

        //then
        then(e).as("check address entity is returned an not null").isNotNull();
        then(e.getNumber()).as("check house/flat number is set").isEqualTo(entity.getNumber());
        then(e.getStreet()).as("check street is set").isEqualTo(entity.getStreet());
        then(e.getCity()).as("check city is set").isEqualTo(entity.getCity());
        then(e.getState()).as("check state is set").isEqualTo(entity.getState());
        then(e.getCity()).as("check country is set").isEqualTo(entity.getCity());
        then(e.getPincode()).as("check pincode is set").isEqualTo(entity.getPincode());
    }

    @Test
    @DisplayName("create address")
    public void createAddress() {
        //BDD given
        given(repository.save(any())).willReturn(entity);
        //when
        Optional<AddressEntity> result = service.createAddressByReq(addAddressReq);
        // then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(entity);
    }

    @Test
    @DisplayName("return address by the given existing id")
    public void getAddressByIdWhenExists() {
        given(repository.findById(UUID.fromString(id))).willReturn(Optional.of(entity));
        // when
        Optional<AddressEntity> result = service.getAddressById(id);
        // then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("delete address by given existing id")
    public void deleteAddressByIdWhenExists() {
        given(repository.findById(UUID.fromString(id)))
                .willReturn(Optional.of(entity));

        willDoNothing().given(repository).deleteById(UUID.fromString(id));

        // when
        service.deleteAddressById(id);
        // then
        verify(repository, times(1)).findById(UUID.fromString(id));
        verify(repository, times(1)).deleteById(UUID.fromString(id));
//
//        given(repository.findById(UUID.fromString(nonExistId)))
//                .willReturn(Optional.of(entity));
//        willDoNothing().given(repository).deleteById(UUID.fromString(nonExistId));
//        // when
//        service.deleteAddressById(nonExistId);
//        // then
//        verify(repository, times(1)).findById(UUID.fromString(nonExistId));
//        verify(repository, times(1)).deleteById(UUID.fromString(nonExistId));
    }


    @Test
    @DisplayName("delete non existed id")
    public void deleteAddressByIdNonExistId() throws Exception {
        given(repository.findById(UUID.fromString(nonExistId)))
                .willReturn(Optional.empty())
                .willThrow(new ResourceNotFoundException(
                        String.format("No Address found with id %s.", nonExistId)));
        // when
        try {
            service.deleteAddressById(nonExistId);
        } catch (Exception ex) {
            //then
            assertThat(ex)
                    .isInstanceOf(com.example.thuva.api.exceptions.ResourceNotFoundException.class);
            assertThat(ex.getMessage()).contains("No Address found with id " + nonExistId);
        }
        // then
        verify(repository, times(1)).findById(UUID.fromString(nonExistId));
        verify(repository, times(0)).deleteById(UUID.fromString(nonExistId));

    }

    @Test
    @DisplayName("return all addresses")
    public void getAllAddress() {
        given(repository.findAll()).willReturn(List.of(entity));

        //when
        Iterable<AddressEntity> result = service.getAllAddresses();
        //then
        assertThat(result).isNotNull();
        assertThat(result).contains(entity);
        assertThat(result).contains(entity);
    }

}
