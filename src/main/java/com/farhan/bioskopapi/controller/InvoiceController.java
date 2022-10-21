package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bioskop/api/invoice")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Invoice", description = "Used to generate invoice")
public class InvoiceController {

    public static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

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
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/generate/{username}/{scheduleId}")
    public void getInvoice(@PathVariable("username") String username,
                           @PathVariable("scheduleId") Long scheduleId) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; fileName=\"invoice.pdf\"");
            JasperPrint jasperPrint = invoiceService.generateInvoice(username, scheduleId);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            logger.info("sukses generate invoice for user : {}", username);
        }catch (Exception ex){
            logger.warn("error generate invoice with message : {}", ex.getMessage());
        }
    }
}
