package com.reserva.citas_medicas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reserva.citas_medicas.dto.CreateMedicalReserveRequestDTO;
import com.reserva.citas_medicas.dto.MedicalReserveResponseDTO;
import com.reserva.citas_medicas.dto.UpdateMedicalReserveRequestDTO;
import com.reserva.citas_medicas.services.MedicalReserveServiceIMPL;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/medical-reserves")
public class MedicalReserveController {

  @Autowired
  private MedicalReserveServiceIMPL service;

  @GetMapping
  public ResponseEntity<List<MedicalReserveResponseDTO>> getAll(){
    return ResponseEntity.ok(service.getAllReserves()); 
  };

  @GetMapping("/{date}")
  public ResponseEntity<?> BookAppointment(@PathVariable String date) {
    List<MedicalReserveResponseDTO> availableReservation = service.getAvailableReservations(date);

    if(availableReservation.size() == 0){
      return ResponseEntity.ok("No hay reservas disponibles para la el dia " + date);
    }

    return ResponseEntity.ok(availableReservation);
  };

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody CreateMedicalReserveRequestDTO request){
    MedicalReserveResponseDTO reserveCreated = service.createReserve(request);

    return ResponseEntity.ok(reserveCreated);
  };

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateMedicalReserveRequestDTO request){
    MedicalReserveResponseDTO reserveUpdate = service.book(id, request);

    if(reserveUpdate == null){
      return ResponseEntity.status(404)
        .body("No se encontro o no esta disponible la cita medica por el id: " + id);
    }

    return ResponseEntity.ok(reserveUpdate);
  };

  @PutMapping("/{id}/cancel")
  public ResponseEntity<String> cancel(@PathVariable Long id) {
    Boolean isCancel = service.cancelReservation(id);

    if(!isCancel){
      return ResponseEntity.status(404).body("No se encontro la cita medica");
    };

    return ResponseEntity.ok("Cita medica cancelada");
  };

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMedicalReserve(@PathVariable Long id){
    Boolean isDeleted = service.deleteMedicalReserve(id);

    if(!isDeleted){
      return ResponseEntity.status(404).body("No se encontro la reserva con el id " + id);
    };

    return ResponseEntity.ok("Reserva eliminada");
  };
};
