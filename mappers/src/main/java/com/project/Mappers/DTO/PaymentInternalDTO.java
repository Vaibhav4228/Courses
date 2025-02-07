package com.project.Mappers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentInternalDTO {
    private PaymentHeadersDTO paymentHeaders;
    private List<InvoicesDTO> invoices;
    private PaymentReqDetailsDTO paymentReqDetails;

//    public PaymentInternalDTO(List<InvoicesDTO> invoices, PaymentHeadersDTO paymentHeaders, PaymentReqDetailsDTO paymentReqDetails) {
//        this.invoices = (invoices != null) ? invoices : new ArrayList<>();
//        this.paymentHeaders = (paymentHeaders != null) ? paymentHeaders : new PaymentHeadersDTO();
//        this.paymentReqDetails = (paymentReqDetails != null) ? paymentReqDetails : new PaymentReqDetailsDTO();
//    }
}
