package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.service.InvoiceService;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class InvoiceServiceImpl implements InvoiceService {


    private DataSource dataSource;

    @Autowired
    public InvoiceServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JasperPrint generateJasperPrint() throws Exception{
        InputStream filInvoice = new ClassPathResource("invoice/Invoice_Ticket.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(filInvoice);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, getConnection());
        return jasperPrint;
    }
}
