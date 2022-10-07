package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.service.InvoiceService;
import com.farhan.bioskopapi.service.impl.InvoiceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bioskop/api/invoice")
@Tag(name = "Invoice", description = "Used to generate invoice")
public class InvoiceController {

    private InvoiceService invoiceService;
    private HttpServletResponse response;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, HttpServletResponse response) {
        this.invoiceService = invoiceService;
        this.response = response;
    }

    @Operation(summary = "Generate Invoice by username and scheduleId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses"),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/generate/{username}/{scheduleId}")
    public void getInvoice(@PathVariable("username") String username,
                           @PathVariable("scheduleId") Long scheduleId) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; fileName=\"invoice.pdf\"");
        JasperPrint jasperPrint = invoiceService.generateJasperPrint(username, scheduleId);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
