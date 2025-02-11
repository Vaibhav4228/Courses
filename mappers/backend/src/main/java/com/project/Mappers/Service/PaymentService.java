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

    public void deletePaymentById(Long id){
        if(!paymentMapperRepo.existsById(id)){
            throw new RuntimeException("Payment with id " + id + "not found");
        }
        paymentMapperRepo.deleteById(id);
    }

    public List<PayloadEntity> getAllSuccessfulPayments() {
        return paymentMapperRepo.findAll();
    }
    public List<FailedPaymentsEntity> getAllFailedPayments(){
        return failedPaymentsRepo.findAll();
    }



    private ValidationResult validatePayment(PaymentInternalDTO paymentInternalDTO) {
        ValidationResult validationResult = new ValidationResult();

        if (!isValidText(paymentInternalDTO.getPaymentHeaders().getPaymentName())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("paymentName, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentHeaders().getPay_id())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("payId, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentHeaders().getPay_type())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("payType, ");
        }
        if (!PatTypeEnum.isValid(paymentInternalDTO.getPaymentHeaders().getPay_type())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("Invalid payType, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentReqDetails().getPaymentReceiverName())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("paymentReceiverName, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentReqDetails().getCompanyCode())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("companyCode, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentReqDetails().getPlant())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("plant, ");
        }
        if (!isValidText(paymentInternalDTO.getPaymentReqDetails().getAmount())) {
            validationResult.errors.add("payment error");
            validationResult.failedFields.append("amount cannot be null, ");
        } else {
            try {
                double amount = Double.parseDouble(paymentInternalDTO.getPaymentReqDetails().getAmount());
                if (amount < 10000 && "BANK_TRANSACTION".equalsIgnoreCase(paymentInternalDTO.getPaymentHeaders().getPay_type())) {
                    validationResult.errors.add("payment error");
                    validationResult.failedFields.append("lowAmount for BANK_TRANSACTION, ");
                }
            } catch (NumberFormatException e) {
                validationResult.errors.add("payment error");
                validationResult.failedFields.append("amountFormat, ");
            }
        }

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
                if (!isValidText(invoice.getInvoice_type())) {
                    validationResult.errors.add("invoice error");
                    validationResult.failedFields.append("invoiceType, ");
                }
                if (!isValidText(invoice.getInvoice_amount())) {
                    validationResult.errors.add("invoice error");
                    validationResult.failedFields.append("invoiceAmount, ");
                }
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
