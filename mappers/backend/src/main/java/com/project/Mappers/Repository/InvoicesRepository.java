package com.project.Mappers.Repository;

import com.project.Mappers.Entity.InvoicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoicesRepository extends JpaRepository<InvoicesEntity, Long> {
    Optional<InvoicesEntity> findTopByPayloadIdOrderByIdDesc(Long payloadId);

}