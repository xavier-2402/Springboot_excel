/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DocuemntosExcel.util;

import com.example.DocuemntosExcel.Model.Cliente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author usuario
 */
public class ExcelGenerator {

    public static ByteArrayInputStream customersToExcel(List<Cliente> clientes) throws IOException {
        String[] COLUMNs = {"Cedula", "Nombre", "Apellido", "Correo"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();) {

            Sheet sheet = workbook.createSheet("Clientes");

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
            }
            int rowIdx = 1;
            for (Cliente cl : clientes) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(cl.getCedula());
                row.createCell(1).setCellValue(cl.getNombre());
                row.createCell(2).setCellValue(cl.getApellido());
                row.createCell(3).setCellValue(cl.getCorreo());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
