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
import java.util.HashMap;
import java.util.Map;

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
    public JasperPrint generateInvoice(String username, Long scheduleId) throws Exception{
        InputStream filInvoice = new ClassPathResource("invoice/InvoiceCh6.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(filInvoice);
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("scheduleId", scheduleId);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, getConnection());
        return jasperPrint;
    }
}
