package com.project.Mappers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentInternalDTO {
    private PaymentHeadersDTO paymentHeaders;
    private List<InvoicesDTO> invoices;
    private PaymentReqDetailsDTO paymentReqDetails;
}


