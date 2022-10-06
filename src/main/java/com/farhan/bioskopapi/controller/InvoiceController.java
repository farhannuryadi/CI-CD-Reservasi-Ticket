package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.service.InvoiceService;
import com.farhan.bioskopapi.service.impl.InvoiceServiceImpl;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bioskop/api/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;
    private HttpServletResponse response;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, HttpServletResponse response) {
        this.invoiceService = invoiceService;
        this.response = response;
    }

    @GetMapping
    public void getInvoice() throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; fileName=\"invoice.pdf\"");
        JasperPrint jasperPrint = invoiceService.generateJasperPrint();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
