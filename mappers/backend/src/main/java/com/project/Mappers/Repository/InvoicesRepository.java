package com.project.Mappers.Repository;

import com.project.Mappers.Entity.InvoicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoicesRepository extends JpaRepository<InvoicesEntity, Long> {
//@Query("SELECT MAX(i.grandTotal) FROM InvoicesEntity i WHERE i.payload.id = :payloadId")
//Optional<Double> findMaxGrandTotalByPayloadId(@Param("payloadId") Long payloadId);

}