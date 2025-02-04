package com.project.Mappers.Mappers;

import com.project.Mappers.DTO.*;
import com.project.Mappers.Entity.InvoicesEntity;
import com.project.Mappers.Entity.PayloadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "paymentName", target = "paymentHeaders.paymentName")
    @Mapping(source = "pay_id", target = "paymentHeaders.pay_id")
    @Mapping(source = "pay_type", target = "paymentHeaders.pay_type")
    @Mapping(source = "invoices",target = "invoices")
    @Mapping(source = "paymentReceiverName", target = "paymentReqDetails.paymentReceiverName")
    @Mapping(source = "amount", target = "paymentReqDetails.amount")
    @Mapping(source = "companyCode", target = "paymentReqDetails.companyCode")
    @Mapping(source = "transactionCode", target = "paymentReqDetails.transactionCode")
    @Mapping(source = "plant", target = "paymentReqDetails.plant")
    @Mapping(source = "gst", target = "paymentReqDetails.gst", qualifiedByName = "convertGst")
    PaymentInternalDTO toInternal(PaymentExternalDTO PaymentExternalDTO);

    @Mapping(source = "invoice_type", target = "invoiceType")
    @Mapping(source = "invoice_date", target = "invoiceDate", qualifiedByName = "convertInvoiceDate")
    @Mapping(source = "invoice_amount", target = "invoiceAmount")
    InvoicesEntity toInvoicesEntity(InvoicesDTO invoicesDTO);

    List<InvoicesEntity> toInvoicesEntities(List<InvoicesDTO> invoicesDTOList);

    @Named("convertGst")
    default Integer convertGst(String gst) {
        if (gst == null || gst.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(gst);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Named("convertInvoiceDate")
    default LocalDate convertInvoiceDate(String invoiceDate) {
        if (invoiceDate == null || invoiceDate.isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(invoiceDate, formatter);
        } catch (Exception e) {
            return null;
        }
    }
    @Mapping(source = "paymentHeaders.paymentName", target = "paymentName")
    @Mapping(source = "paymentHeaders.pay_id", target = "payId")
    @Mapping(source = "paymentHeaders.pay_type", target = "payType")
    @Mapping(source = "paymentReqDetails.paymentReceiverName", target = "paymentReceiverName")
    @Mapping(source = "paymentReqDetails.amount", target = "amount")
    @Mapping(source = "paymentReqDetails.companyCode", target = "companyCode")
    @Mapping(source = "paymentReqDetails.transactionCode", target = "transactionCode")
    @Mapping(source = "paymentReqDetails.plant", target = "plant")
    @Mapping(source = "paymentReqDetails.gst", target = "gst", qualifiedByName = "convertGst")
    @Mapping(source = "invoices", target = "invoices")
    PayloadEntity toPayloadEntity(PaymentInternalDTO PaymentInternalDTO);
}
