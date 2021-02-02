/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DocuemntosExcel.Controller;

import com.example.DocuemntosExcel.Model.Cliente;
import com.example.DocuemntosExcel.Service.ClienteService;
import com.example.DocuemntosExcel.util.ExcelGenerator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("/customer")
@CrossOrigin(origins="*")
public class ClienteController {
    
    @Autowired
    ClienteService clienteservice;
    
    
    //POST
    @PostMapping(path="/savecustomer")
    public ResponseEntity saveCustomer(@RequestBody Cliente cliente){
        clienteservice.crearCliente(cliente);
        return ResponseEntity.ok("Added customer with CI "+cliente.getCedula());
    }
    
    
    //GET
    @GetMapping(path="/listcustomers")
    public ResponseEntity listCustomers(){
        System.out.println(clienteservice.listarClientes());
        for(int i=0; i<clienteservice.listarClientes().size();i++){
            System.out.println(clienteservice.listarClientes().get(i).getNombre());
            
        }
        return ResponseEntity.ok(clienteservice.listarClientes());
    }
    
    //GET EXCEL
   @GetMapping(path = "/download/customers.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
        List<Cliente> clientes = (List<Cliente>) clienteservice.listarClientes();
    
    ByteArrayInputStream in = ExcelGenerator.customersToExcel(clientes);
    // return IOUtils.toByteArray(in);
    
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
}
