package com.reserva.citas_medicas.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class MedicalReserveResponseDTO extends RepresentationModel<MedicalReserveResponseDTO> {
  private Long id;
  private String date;
  private String hr;
  private String nameDoctor;
  private String namePatient;
  private Boolean isReserved;
  private String description;
  private int totalAmount;
};
