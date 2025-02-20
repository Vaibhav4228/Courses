package com.effigo.vaibhav.projections_views.Projections;

import java.time.LocalDateTime;

public interface OrderProjection {
    Long getId();
    String getProductName();
    Double getPrice();
    LocalDateTime getOrderDate();
}
