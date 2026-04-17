package com.reserva.citas_medicas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reserva.citas_medicas.dto.CreateMedicalReserveRequestDTO;
import com.reserva.citas_medicas.dto.MedicalReserveResponseDTO;
import com.reserva.citas_medicas.dto.UpdateMedicalReserveRequestDTO;

@Service
public interface MedicalReserveService {
  //GET
  List<MedicalReserveResponseDTO> getAllReserves();
  List<MedicalReserveResponseDTO> getAvailableReservations(String date);
  //POST
  MedicalReserveResponseDTO createReserve(CreateMedicalReserveRequestDTO request);
  //UPDATE
  MedicalReserveResponseDTO book(Long id, UpdateMedicalReserveRequestDTO request);
  Boolean cancelReservation(Long id);
  //DELETE
  Boolean deleteMedicalReserve(Long id);
};