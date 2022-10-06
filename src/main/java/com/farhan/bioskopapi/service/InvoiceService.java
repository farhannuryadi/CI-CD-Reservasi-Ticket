package com.farhan.bioskopapi.service;

import net.sf.jasperreports.engine.JasperPrint;

public interface InvoiceService {

    JasperPrint generateJasperPrint()throws Exception;
}
