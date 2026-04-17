package com.reserva.citas_medicas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMedicalReserveRequestDTO {
  
  @NotBlank(message = "La fecha es obligatoria")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha debe tener formato yyyy-MM-dd")
  private String date;

  @NotBlank(message = "La hora es obligatoria")
	@Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "La hora debe tener formato HH:mm")
  private String hr;

  @NotBlank(message = "El nombre del doctor es obligatorio")
  private String nameDoctor;

  @NotBlank(message = "el nombre del paciente es obligatorio")
  private String namePatient;

  @NotBlank(message = "La descripción es obligatoria")
  private String description;

  @NotNull(message = "El monto de la cita es obligatorio")
  private int totalAmount; 
}