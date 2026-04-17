package com.reserva.citas_medicas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicalReserves")
public class MedicalReserve {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private String date;

  @Column(name = "hr")
  private String hr;

  @Column(name = "nameDoctor")
  private String nameDoctor;

  @Column(name = "namePatient")
  private String namePatient;

  @Column(name = "isReserved")
  private Boolean isReserved;

  @Column(name = "description", length = 2000)
  private String description;

  @Column(name = "totalAmount")
  private int totalAmount; 
};