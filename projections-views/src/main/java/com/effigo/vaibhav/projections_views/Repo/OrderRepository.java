package com.effigo.vaibhav.projections_views.Repo;

import com.effigo.vaibhav.projections_views.Entity.Order;
import com.effigo.vaibhav.projections_views.Projections.OrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<OrderProjection> findAllProjectedBy(Pageable pageable);
}
