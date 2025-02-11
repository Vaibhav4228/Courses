package com.project.Mappers.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices_entity")
public class InvoicesEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String invoiceType;
    private LocalDate invoiceDate;
    private String invoiceAmount;

    @ManyToOne
    @JoinColumn(name="payload_id")
    @JsonIgnore
    private PayloadEntity payload;

//    public InvoicesEntity(Long id, String invoiceAmount, LocalDate invoiceDate, String invoiceType, PayloadEntity payload) {
//        this.id = id;
//        this.invoiceAmount = invoiceAmount;
//        this.invoiceDate = invoiceDate;
//        this.invoiceType = invoiceType;
//        this.payload = payload;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getInvoiceAmount() {
//        return invoiceAmount;
//    }
//
//    public void setInvoiceAmount(String invoiceAmount) {
//        this.invoiceAmount = invoiceAmount;
//    }
//
//    public LocalDate getInvoiceDate() {
//        return invoiceDate;
//    }
//
//    public void setInvoiceDate(LocalDate invoiceDate) {
//        this.invoiceDate = invoiceDate;
//    }
//
//    public String getInvoiceType() {
//        return invoiceType;
//    }
//
//    public void setInvoiceType(String invoiceType) {
//        this.invoiceType = invoiceType;
//    }
//
//    public PayloadEntity getPayload() {
//        return payload;
//    }
//
//    public void setPayload(PayloadEntity payload) {
//        this.payload = payload;
//    }
}
