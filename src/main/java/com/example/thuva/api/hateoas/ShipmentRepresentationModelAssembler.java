package com.example.thuva.api.hateoas;

import com.example.thuva.api.controller.ShipmentController;
import com.example.thuva.api.entity.ShipmentEntity;
import com.example.thuva.api.model.Shipment;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot Ed 2
 */
@Component
public class ShipmentRepresentationModelAssembler
    extends RepresentationModelAssemblerSupport<ShipmentEntity, Shipment> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and
   * resource type.
   */
  public ShipmentRepresentationModelAssembler() {
    super(ShipmentController.class, Shipment.class);
  }

  /**
   * Coverts the Shipment entity to resource
   *
   * @param entity
   * @return
   */
  @Override
  public Shipment toModel(ShipmentEntity entity) {
    Shipment resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());
    // Self link generated by createModelWithId has missing api path. Therefore, generating
    // additionally.
    // can be removed once fixed.
    resource.add(
        linkTo(methodOn(ShipmentController.class).getShipmentByOrderId(entity.getId().toString()))
            .withRel("byOrderId"));
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   * @return
   */
  public List<Shipment> toListModel(Iterable<ShipmentEntity> entities) {
    if (Objects.isNull(entities)) return List.of();
    return StreamSupport.stream(entities.spliterator(), false).map(this::toModel).collect(toList());
  }
}
