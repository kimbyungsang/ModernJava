package com.example.thuva.api.controller;

import com.example.thuva.api.ShipmentApi;
import com.example.thuva.api.hateoas.ShipmentRepresentationModelAssembler;
import com.example.thuva.api.model.Shipment;
import com.example.thuva.api.service.ShipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot Ed 2
 */
@RestController
public class ShipmentController implements ShipmentApi {

  private final ShipmentService service;
  private final ShipmentRepresentationModelAssembler assembler;

  public ShipmentController (
      ShipmentService service, ShipmentRepresentationModelAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @Override
  public ResponseEntity<List<Shipment>> getShipmentByOrderId(@NotNull @Valid String id) {
    return ResponseEntity.ok(assembler.toListModel(service.getShipmentByOrderId(id)));
  }
}
