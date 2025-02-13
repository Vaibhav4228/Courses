package com.project.Mappers.Controller;

import com.project.Mappers.Entity.InvoicesEntity;
import com.project.Mappers.Repository.InvoicesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/batch")
public class BatchJobController {

    private final InvoicesRepository invoicesRepository;

    public BatchJobController(InvoicesRepository invoicesRepository) {
        this.invoicesRepository = invoicesRepository;
    }

    @PostMapping("/calculate-grand-total/{payloadId}")
    public String runGrandTotalJob(@PathVariable Long payloadId) {
        return "Grand Total Calculation Started for Payload ID: " + payloadId;
    }

    @GetMapping("/latest-grand-total/{payloadId}")
    public Double getLatestGrandTotal(@PathVariable Long payloadId) {
        Optional<InvoicesEntity> latestInvoice = invoicesRepository.findTopByPayloadIdOrderByIdDesc(payloadId);
        return latestInvoice.map(invoice -> Math.round(invoice.getGrandTotal() * 1000.0) / 1000.0).orElse(0.0);
    }
}
