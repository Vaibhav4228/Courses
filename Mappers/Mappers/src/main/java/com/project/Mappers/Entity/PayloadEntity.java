package com.project.Mappers.Entity;

import com.project.Mappers.DTO.InvoicesDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String paymentName;
    private String payId;
    private String payType;
    private String paymentReceiverName;
    private String amount;
    private String companyCode;
    private String transactionCode;
    private String plant;
    private Integer gst;

    @OneToMany(mappedBy = "payload", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicesEntity> invoices;

}
