package com.project.Mappers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoicesDTO {
    private String invoice_type;
    private String invoice_date;
    private String invoice_amount;


}

