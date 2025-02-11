package com.project.Mappers.Repository;

import com.project.Mappers.Entity.FailedPaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedPaymentsRepo extends JpaRepository<FailedPaymentsEntity, Long> {

}
