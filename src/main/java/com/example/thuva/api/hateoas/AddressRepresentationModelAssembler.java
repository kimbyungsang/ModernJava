package com.example.thuva.api.hateoas;

import com.example.thuva.api.controller.AddressController;
import com.example.thuva.api.entity.AddressEntity;
import com.example.thuva.api.model.AddAddressReq;
import com.example.thuva.api.model.Address;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class AddressRepresentationModelAssembler
    extends RepresentationModelAssemblerSupport<AddressEntity, Address> {

    public AddressRepresentationModelAssembler() {
        super(AddressController.class, Address.class);
    }

    @Override
    public Address toModel(AddressEntity entity) {
        Address resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());
        try {
            resource.add(
                    linkTo(methodOn(AddressController.class).getAddressesById((entity.getId().toString())))
                            .withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resource;
    }

    public List<Address> toListModel(Iterable<AddressEntity> entities) {
        if(Objects.isNull(entities)) return List.of();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel).collect(toList());
    }


}
