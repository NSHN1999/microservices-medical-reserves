package com.reserva.citas_medicas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reserva.citas_medicas.dto.CreateMedicalReserveRequestDTO;
import com.reserva.citas_medicas.dto.MedicalReserveResponseDTO;
import com.reserva.citas_medicas.models.MedicalReserve;
import com.reserva.citas_medicas.repositories.MedicalReserveRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias - MedicalReservesServiceIMPL")
public class MedicalReservesServiceIMPLTest {

  @Mock
  private MedicalReserveRepository medicalReserveRepository;

  @InjectMocks
  private MedicalReserveServiceIMPL medicalReserveService;

  private MedicalReserve reserveEntity;
  private CreateMedicalReserveRequestDTO reserveRequest;

  @BeforeEach
  void setUp(){
    reserveEntity = new MedicalReserve();
    reserveEntity.setId(1L);
    reserveEntity.setDate("2026-05-01");
    reserveEntity.setHr("10:30");
    reserveEntity.setNameDoctor("Tony Stark");
    reserveEntity.setNamePatient("Nicolás Hernández");
    reserveEntity.setIsReserved(true);
    reserveEntity.setDescription("Dolor en el ojo derecho.");
    reserveEntity.setTotalAmount(25000);

    reserveRequest = new CreateMedicalReserveRequestDTO();
    reserveRequest.setDate("2026-05-04");
    reserveRequest.setHr("09:00");
    reserveRequest.setNameDoctor("Bruce Banner");
    reserveRequest.setNamePatient("Thanos");
    reserveRequest.setDescription("Dolor de pecho");
    reserveRequest.setTotalAmount(45000);
  };

  @Test
  @DisplayName("Debería obtener todas las reservas")
  void deberiaObtenerTodasLasReservas(){

    MedicalReserve usuario2 = new MedicalReserve();
    usuario2.setId(2L);
    usuario2.setDate("2026-05-02");
    usuario2.setHr("11:00");
    usuario2.setNameDoctor("Peter Parker");
    usuario2.setNamePatient("Sebastian Hernández");
    usuario2.setIsReserved(true);
    usuario2.setDescription("Dolor de estomago");
    usuario2.setTotalAmount(20000);

    when(medicalReserveRepository.findAll()).thenReturn(Arrays.asList(reserveEntity, usuario2));
    List<MedicalReserveResponseDTO> resultado = medicalReserveService.getAllReserves();

    assertNotNull(resultado);
    assertEquals(2, resultado.size());
    assertEquals("Nicolás Hernández", resultado.get(0).getNamePatient());
    assertEquals("Sebastian Hernández", resultado.get(1).getNamePatient());
    verify(medicalReserveRepository, times(1)).findAll();
  };

  @Test
  @DisplayName("Debería obtener reservas medicas por fecha")
  void deberiaObtenerReservasMedicasPorFecha(){
    String date = "2026-05-01";
    when(medicalReserveRepository.findAllAvailableByDate(date)).thenReturn(Arrays.asList(reserveEntity));
    List<MedicalReserveResponseDTO> resultado = medicalReserveService.getAvailableReservations(date);

    assertNotNull(resultado);
    assertEquals(date, resultado.get(0).getDate());
    assertEquals("Nicolás Hernández", resultado.get(0).getNamePatient());
    verify(medicalReserveRepository, times(1)).findAllAvailableByDate(date);
  };

  @Test
  @DisplayName("Debería obtener un array vacio si no existen citas medicas")
  void deberiaObtenerUnArrayVacioSiNoExistenCitasMedicas(){
    String date = "2026-05-02";
    when(medicalReserveRepository.findAllAvailableByDate(date)).thenReturn(Arrays.asList());
    List<MedicalReserveResponseDTO> resultado = medicalReserveService.getAvailableReservations(date);

    assertNotNull(resultado);
    assertEquals(0, resultado.size());
    verify(medicalReserveRepository, times(1)).findAllAvailableByDate(date);
  };

  @Test
  @DisplayName("Debería crear una nueva reserva medica")
  void deberiaCrearUnaNuevaReservaMedica(){
    when(medicalReserveRepository.save(any(MedicalReserve.class))).thenReturn(reserveEntity);

    MedicalReserveResponseDTO resultado = medicalReserveService.createReserve(reserveRequest);

    assertNotNull(resultado);
    assertEquals("2026-05-04", resultado.getDate());
    assertEquals("09:00", resultado.getHr());
    assertEquals("Bruce Banner", resultado.getNameDoctor());
    assertEquals("Thanos", resultado.getNamePatient());
    assertEquals("Dolor de pecho", resultado.getDescription());
    assertEquals(45000, resultado.getTotalAmount());
  };
};