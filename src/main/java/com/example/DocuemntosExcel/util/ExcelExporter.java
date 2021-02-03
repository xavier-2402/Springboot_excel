/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DocuemntosExcel.util;

import com.example.DocuemntosExcel.Model.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author usuario
 */
public class ExcelExporter {
    
    private XSSFWorkbook xssfworkbook;
    private XSSFSheet xssfsheet;
    private List<Cliente> listclientes;
    
    
    private void header(){
        xssfsheet = xssfworkbook.createSheet("LiSTA CLIENTES");
        Row row = xssfsheet.createRow(0);
        
        CellStyle style =  xssfworkbook.createCellStyle();
        XSSFFont font = xssfworkbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        
        createCell(row, 0, "Nombre", style);
        createCell(row, 1, "Apellido", style);
        createCell(row, 2, "Correo", style);
        createCell(row, 3, "Cedula", style);
        
    }
    
    private void createCell(Row row,int countColumn, Object value, CellStyle style){
        xssfsheet.autoSizeColumn(countColumn);
        Cell cell = row.createCell(countColumn);
        if( value instanceof Integer){
            cell.setCellValue((double)value);
        }
        else if ( value instanceof Boolean){
            cell.setCellValue((boolean)value);
        }
        else{
            cell.setCellValue((String)value);
        }
        cell.setCellStyle(style);
        
    }
    
    private void writeData(){
        int rowCount=1;
        
        CellStyle style = xssfworkbook.createCellStyle();
        XSSFFont font = xssfworkbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);
        
        for ( Cliente cliente : listclientes){
            Row row = xssfsheet.createRow(rowCount++);
            int countColumn =0;
            
            createCell(row, countColumn++, cliente.getNombre(), style);
            createCell(row, countColumn++, cliente.getApellido(), style);
            createCell(row, countColumn++, cliente.getCorreo(), style);
            createCell(row, countColumn++, cliente.getCedula(), style);
        }
    }
    
    public ExcelExporter (List<Cliente> list){
        this.listclientes=list;
        xssfworkbook = new XSSFWorkbook();
    }
    
    public void exportData(HttpServletResponse response) throws IOException{
        header();
        writeData();
        ServletOutputStream out = response.getOutputStream();
        xssfworkbook.write(out);
        xssfworkbook.close();
        out.close();
    }
}
