package com.project.Mappers.Repository;

import com.project.Mappers.Entity.PayloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMapperRepo extends JpaRepository<PayloadEntity, Long> {
}
