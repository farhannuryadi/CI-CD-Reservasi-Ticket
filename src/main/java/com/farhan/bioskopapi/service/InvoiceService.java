package com.farhan.bioskopapi.service;

import net.sf.jasperreports.engine.JasperPrint;

public interface InvoiceService {

    JasperPrint generateInvoice(String username, Long scheduleId)throws Exception;
}
