
package com.reserva.citas_medicas.mappers;

import com.reserva.citas_medicas.dto.MedicalReserveResponseDTO;
import com.reserva.citas_medicas.models.MedicalReserve;

public class MedicalReserveMapper {

  public static MedicalReserveResponseDTO toDto(MedicalReserve entity){
    MedicalReserveResponseDTO dto = new MedicalReserveResponseDTO();

    dto.setId(entity.getId());
    dto.setDate(entity.getDate());
    dto.setHr(entity.getHr());
    dto.setNameDoctor(entity.getNameDoctor());
    dto.setNamePatient(entity.getNamePatient());
    dto.setIsReserved(entity.getIsReserved());
    dto.setDescription(entity.getDescription());
    dto.setTotalAmount(entity.getTotalAmount());

    return dto;
  };
  
  public static MedicalReserve toEntity(MedicalReserveResponseDTO dto){
    MedicalReserve entity = new MedicalReserve();

    entity.setId(dto.getId());
    entity.setDate(dto.getDate());
    entity.setHr(dto.getHr());
    entity.setNameDoctor(dto.getNameDoctor());
    entity.setNamePatient(dto.getNamePatient());
    entity.setIsReserved(dto.getIsReserved());
    entity.setDescription(dto.getDescription());
    entity.setTotalAmount(dto.getTotalAmount());

    return entity;
  };

};