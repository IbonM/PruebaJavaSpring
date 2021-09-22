package com.example.prueba;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import javax.servlet.http.HttpServletResponse;



@RestController
class ControlCoche {
	

  private final Repositorio repository;

  ControlCoche(Repositorio repository) {
    this.repository = repository;
  }


  @GetMapping("/coches")
  List<Coche> all() {
    return repository.findAll();
      
  }
  
  @GetMapping("/coches/{marca}")
  List<Coche> allMarca(@PathVariable String marca) {
	  List<Coche> coches = repository.findAll();
	  List<Coche> cochesMarca = new ArrayList<>();
	  for (Coche coche : coches)
	  {
		  if(coche.getMarca().equalsIgnoreCase(marca))
		  {
			  cochesMarca.add(coche);
		  }
	  }
	  return cochesMarca;
    
      
  }
  

  @PostMapping("/coches")
  Coche newCoche(@RequestBody Coche newCoche) {
    return repository.save(newCoche);
  }


  @DeleteMapping("/coches/{id}")
  void deleteCoche(@PathVariable Long id) {
    repository.deleteById(id);
  }
  
  @GetMapping("/coches/pdf")
  public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
      response.setContentType("application/pdf");
      DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
      String currentDateTime = dateFormatter.format(new Date(System.currentTimeMillis()));
       
      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
      response.setHeader(headerKey, headerValue);
       
      List<Coche> coches = repository.findAll();
       
      ExportarPDF exporter = new ExportarPDF(coches,"");
      exporter.export(response);
       
  }
  
  @GetMapping("/coches/pdf/{marca}")
  public void exportMarcaToPDF(HttpServletResponse response,@PathVariable String marca) throws DocumentException, IOException {

      response.setContentType("application/pdf");
      DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
      String currentDateTime = dateFormatter.format(new Date(System.currentTimeMillis()));
       
      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=users_" + currentDateTime + marca.toLowerCase() + ".pdf";
      response.setHeader(headerKey, headerValue);
       
      List<Coche> coches = repository.findAll();
       
      ExportarPDF exporter = new ExportarPDF(coches,marca);
      exporter.export(response);
       
  }
  

}