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
    @JoinColumn(name = "payload_id")
    @JsonIgnore
    private PayloadEntity payload;

    private Double grandTotal;

}