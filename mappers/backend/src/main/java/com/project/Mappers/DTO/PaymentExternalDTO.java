package com.project.Mappers.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentExternalDTO {

    private String paymentName;
    private String pay_id;
    private String pay_type;
    private List<InvoicesDTO> invoices;
    private String paymentReceiverName;
    private String amount;
    private String companyCode;
    private String transactionCode;
    private String plant;
    private String gst;



}

