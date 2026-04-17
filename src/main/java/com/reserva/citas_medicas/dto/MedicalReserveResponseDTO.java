package com.reserva.citas_medicas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalReserveResponseDTO {
  private Long id;
  private String date;
  private String hr;
  private String nameDoctor;
  private String namePatient;
  private Boolean isReserved;
  private String description;
  private int totalAmount; 
};
