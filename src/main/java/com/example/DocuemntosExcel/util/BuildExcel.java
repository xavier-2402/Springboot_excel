/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DocuemntosExcel.util;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

/**
 *
 * @author usuario
 */
@Component("/list/listcustomers.xlsx")
public class BuildExcel extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse response) throws Exception {   
        response.setHeader("Content-Disposition", "attachment;filename=\"listadoclientes.xlsx\"");
        Sheet hoja = wrkbk.createSheet("Clientes");
    }
    
    
}
