package com.project.Mappers.Service;

import com.project.Mappers.DTO.InvoicesDTO;
import com.project.Mappers.DTO.PaymentExternalDTO;
import com.project.Mappers.DTO.PaymentInternalDTO;
import com.project.Mappers.Entity.InvoicesEntity;
import com.project.Mappers.Entity.PayloadEntity;
import com.project.Mappers.Mappers.PaymentMapper;
import com.project.Mappers.Repository.PaymentMapperRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentMapperRepo PaymentMapperRepo;
    private PaymentMapper PaymentMapper;
    public PaymentService(PaymentMapper PaymentMapper, PaymentMapperRepo PaymentMapperRepo){
        this.PaymentMapper=PaymentMapper;
        this.PaymentMapperRepo=PaymentMapperRepo;
    }
    public PayloadEntity getPaymentById(Long id) {

        return PaymentMapperRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }
    public PaymentInternalDTO convertToInternal(PaymentExternalDTO paymentExternalDTO) {
        return PaymentMapper.toInternal(paymentExternalDTO);
    }
    @Transactional
    public PayloadEntity savePayload(PaymentInternalDTO PaymentInternalDTO) {
        PayloadEntity payloadEntity = PaymentMapper.toPayloadEntity(PaymentInternalDTO);
        for (InvoicesDTO invoiceDTO : PaymentInternalDTO.getInvoices()) {
            InvoicesEntity invoiceEntity = PaymentMapper.toInvoicesEntity(invoiceDTO);
            invoiceEntity.setPayload(payloadEntity);
            payloadEntity.getInvoices().add(invoiceEntity);
        }
        return PaymentMapperRepo.save(payloadEntity);
    }
}
