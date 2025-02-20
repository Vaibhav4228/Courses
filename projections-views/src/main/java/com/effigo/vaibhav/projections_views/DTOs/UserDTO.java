package com.effigo.vaibhav.projections_views.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private List<OrderDTO> orders;
}
