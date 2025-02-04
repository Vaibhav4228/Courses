package com.project.Mappers.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoicesEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String invoiceType;
    private LocalDate invoiceDate; // Updated to LocalDate
    private String invoiceAmount;

    @ManyToOne
    @JoinColumn(name="payload_id")
    private PayloadEntity payload;
}
