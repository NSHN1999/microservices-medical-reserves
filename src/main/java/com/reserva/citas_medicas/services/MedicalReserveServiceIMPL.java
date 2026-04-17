package com.reserva.citas_medicas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reserva.citas_medicas.dto.CreateMedicalReserveRequestDTO;
import com.reserva.citas_medicas.dto.MedicalReserveResponseDTO;
import com.reserva.citas_medicas.dto.UpdateMedicalReserveRequestDTO;
import com.reserva.citas_medicas.mappers.MedicalReserveMapper;
import com.reserva.citas_medicas.models.MedicalReserve;
import com.reserva.citas_medicas.repositories.MedicalReserveRepository;

@Service
public class MedicalReserveServiceIMPL implements MedicalReserveService{

  @Autowired
  private MedicalReserveRepository reserveRepository;

  public List<MedicalReserveResponseDTO> getAllReserves(){
    List<MedicalReserveResponseDTO> reserves = reserveRepository.findAll()
      .stream()
      .map(MedicalReserveMapper::toDto)
      .toList();

    return reserves;
  };

  public MedicalReserveResponseDTO createReserve(CreateMedicalReserveRequestDTO request){
    MedicalReserve newReserve = new MedicalReserve();

    newReserve.setDate(request.getDate());
    newReserve.setHr(request.getHr());
    newReserve.setNameDoctor(request.getNameDoctor());
    newReserve.setNamePatient(request.getNamePatient()); 
    newReserve.setIsReserved(true);
    newReserve.setDescription(request.getDescription());
    newReserve.setTotalAmount(request.getTotalAmount());
    reserveRepository.save(newReserve);

    MedicalReserveResponseDTO reserveDto = MedicalReserveMapper.toDto(newReserve);
    return reserveDto;
  };

  public List<MedicalReserveResponseDTO> getAvailableReservations(String date){
    List<MedicalReserveResponseDTO> reservesAvailable = reserveRepository.findAllAvailableByDate(date)
      .stream()
      .map(MedicalReserveMapper::toDto)
      .toList();
    
    return reservesAvailable;
  };

  public MedicalReserveResponseDTO book(Long id, UpdateMedicalReserveRequestDTO request){
    Optional<MedicalReserve> medicalReserveOptional = reserveRepository.findById(id);

    if(medicalReserveOptional.isEmpty()){
      return null;
    };

    MedicalReserve medicalReserve = medicalReserveOptional.get();
    medicalReserve.setDate(request.getDate());
    medicalReserve.setHr(request.getHr());
    medicalReserve.setNamePatient(request.getNamePatient());
    medicalReserve.setIsReserved(true);
    medicalReserve.setDescription(request.getDescription());
    medicalReserve.setTotalAmount(request.getTotalAmount());
    reserveRepository.save(medicalReserve);

    MedicalReserveResponseDTO reserveResponseDTO = MedicalReserveMapper.toDto(medicalReserve);
    return reserveResponseDTO;
  };

  public Boolean cancelReservation(Long id){
    Optional<MedicalReserve> medicalReserveOption = reserveRepository.findById(id);

    if(medicalReserveOption.isEmpty()){
      return false;
    };

    MedicalReserve medicalReserve = medicalReserveOption.get();
    medicalReserve.setNamePatient("");
    medicalReserve.setIsReserved(false);
    medicalReserve.setDescription("");
    medicalReserve.setTotalAmount(0);
    reserveRepository.save(medicalReserve);

    return true;
  };

  public Boolean deleteMedicalReserve(Long id){
    if(reserveRepository.existsById(id)){
      reserveRepository.deleteById(id);
      return true;
    }
    
    return false;
  }
};
