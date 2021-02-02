/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DocuemntosExcel.Service;

import com.example.DocuemntosExcel.Model.Cliente;
import com.example.DocuemntosExcel.Repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class ClienteService {
     @Autowired
    ClienteRepository clienterps;
    
    public Cliente crearCliente (Cliente cliente){
        return clienterps.save(cliente);
    }
    
    public List<Cliente> listarClientes(){
        return clienterps.findAll();
    }
    
}
