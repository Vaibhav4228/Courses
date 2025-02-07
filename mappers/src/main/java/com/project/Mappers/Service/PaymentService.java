package com.project.Mappers.Service;

import com.project.Mappers.DTO.InvoicesDTO;
import com.project.Mappers.DTO.PaymentExternalDTO;
import com.project.Mappers.DTO.PaymentInternalDTO;
import com.project.Mappers.Entity.FailedPaymentsEntity;
import com.project.Mappers.Entity.InvoicesEntity;
import com.project.Mappers.Entity.PayloadEntity;
import com.project.Mappers.Enums.PatTypeEnum;
import com.project.Mappers.Mappers.PaymentMapper;
import com.project.Mappers.Repository.FailedPaymentsRepo;
import com.project.Mappers.Repository.PaymentMapperRepo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentMapperRepo paymentMapperRepo;
    private final PaymentMapper paymentMapper;
    private final FailedPaymentsRepo failedPaymentsRepo;
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public PaymentService(PaymentMapper paymentMapper, PaymentMapperRepo paymentMapperRepo, FailedPaymentsRepo failedPaymentsRepo) {
        this.paymentMapper = paymentMapper;
        this.paymentMapperRepo = paymentMapperRepo;
        this.failedPaymentsRepo = failedPaymentsRepo;
    }

    public PayloadEntity getPaymentById(Long id) {
        return paymentMapperRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public PaymentInternalDTO convertToInternal(PaymentExternalDTO paymentExternalDTO) {
        return paymentMapper.toInternal(paymentExternalDTO);
    }

    public PayloadEntity savePayload(PaymentInternalDTO paymentInternalDTO) {
        ValidationResult validationResult = validatePayment(paymentInternalDTO);

        if (!validationResult.errors.isEmpty()) {

            FailedPaymentsEntity failedRecord = new FailedPaymentsEntity();
            failedRecord.setErrorMessage(validationResult.mainError);
            failedRecord.setRawData(validationResult.failedFields.toString());
            failedPaymentsRepo.save(failedRecord);
            throw new RuntimeException(validationResult.mainError);
        }

        // Convert DTO to PayloadEntity
        PayloadEntity payloadEntity = paymentMapper.toPayloadEntity(paymentInternalDTO);
        payloadEntity.setPayType(paymentInternalDTO.getPaymentHeaders().getPay_type().toUpperCase());

        if (payloadEntity.getInvoices() == null) {
            payloadEntity.setInvoices(new ArrayList<>());
        }

        for (InvoicesDTO invoiceDTO : paymentInternalDTO.getInvoices()) {
            InvoicesEntity invoiceEntity = paymentMapper.toInvoicesEntity(invoiceDTO);
            invoiceEntity.setPayload(payloadEntity);
            payloadEntity.getInvoices().add(invoiceEntity);
        }

        return paymentMapperRepo.save(payloadEntity);
    }


    private ValidationResult validatePayment(PaymentInternalDTO paymentInternalDTO) {
        ValidationResult validationResult = new ValidationResult();

        if (!isValidText(paymentInternalDTO.getPaymentHeaders().getPaymentName())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("paymentName, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentReqDetails().getCompanyCode())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("companyCode, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentReqDetails().getPlant())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("plant, ");
        }

        // Invoice Date Validation
        if (paymentInternalDTO.getInvoices() == null || paymentInternalDTO.getInvoices().isEmpty()) {
            validationResult.errors.add("date error");
            validationResult.failedFields.append("invoiceDate, ");
        } else {
            for (InvoicesDTO invoice : paymentInternalDTO.getInvoices()) {
                if (!isValidText(invoice.getInvoice_date())) {
                    validationResult.errors.add("date error");
                    validationResult.failedFields.append("invoiceDate for invoice type " + invoice.getInvoice_type() + ", ");
                } else if (!isValidDateFormat(invoice.getInvoice_date())) {
                    validationResult.errors.add("date error");
                    validationResult.failedFields.append("Invalid format for invoice type " + invoice.getInvoice_type() + ", ");
                } else if (!isValidFutureDate(invoice.getInvoice_date())) {
                    validationResult.errors.add("date error");
                    validationResult.failedFields.append("Past date for invoice type " + invoice.getInvoice_type() + ", ");
                }
            }
        }

        String payTypeStr = paymentInternalDTO.getPaymentHeaders().getPay_type();
        if (!PatTypeEnum.isValid(payTypeStr)) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("payType, ");
        }

        String amountStr = paymentInternalDTO.getPaymentReqDetails().getAmount();
        if (amountStr != null) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount < 10000) {
                    validationResult.errors.add("payment error");
                    validationResult.failedFields.append("lowAmount, ");
                }
            } catch (NumberFormatException e) {
                validationResult.errors.add("payment error");
                validationResult.failedFields.append("amountFormat, ");
            }
        }

        if (!validationResult.errors.isEmpty()) {
            validationResult.mainError = validationResult.errors.get(0);
        }

        return validationResult;
    }


    private boolean isValidText(String value) {
        return value != null && !value.trim().isEmpty();
    }


    private boolean isValidDateFormat(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    private boolean isValidFutureDate(String dateStr) {
        try {
            LocalDate parsedDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return !parsedDate.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    private static class ValidationResult {
        String mainError;
        List<String> errors = new ArrayList<>();
        StringBuilder failedFields = new StringBuilder();
    }



}