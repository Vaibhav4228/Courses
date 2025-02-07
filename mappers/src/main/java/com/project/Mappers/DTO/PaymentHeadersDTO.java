package com.project.Mappers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHeadersDTO {
    private String paymentName;
    private String pay_id;
    private String pay_type;

}
