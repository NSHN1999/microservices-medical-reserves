package com.reserva.citas_medicas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reserva.citas_medicas.models.MedicalReserve;

@Repository
public interface MedicalReserveRepository extends JpaRepository<MedicalReserve, Long> {

  @Query(value = "SELECT * FROM medical_reserves mr WHERE mr.date = :date AND mr.is_Reserved = false", nativeQuery = true)
  List<MedicalReserve> findAllAvailableByDate(@Param("date") String date);
};