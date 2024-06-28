package com.example.thuva.api.controller;

import com.example.thuva.api.AddressApi;
import com.example.thuva.api.hateoas.AddressRepresentationModelAssembler;
import com.example.thuva.api.model.AddAddressReq;
import com.example.thuva.api.model.Address;
import com.example.thuva.api.service.AddressService;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class AddressController implements AddressApi {

    AddressService service;
    AddressRepresentationModelAssembler assember;

    public AddressController(AddressService service, AddressRepresentationModelAssembler assember) {
        this.service = service;
        this.assember = assember;
    }

    @Override
    public ResponseEntity<Address> createAddress(AddAddressReq addAddressReq) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createAddressByReq(addAddressReq)
                        .map(assember::toModel).get());
    }

    @Override
    public ResponseEntity<Void> deleteAddressesById(String id) throws Exception {
         service.deleteAddressById(id);
         return accepted().build();
    }

    @Override
    public ResponseEntity<Address> getAddressesById(String id) throws Exception {
        return service.getAddressById(id)
                .map(assember::toModel)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<Address>> getAllAddresses() throws Exception {
        return ok(assember.toListModel(service.getAllAddresses()));
    }
}
