package com.example.prueba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
class Coche {

  private @Id @GeneratedValue Long id;
  private String marca;
  private String modelo;
  private String matricula;

  Coche() {}

  Coche(String marca, String modelo, String matricula) {

    this.marca = marca;
    this.modelo = modelo;
    this.matricula = matricula;
  }

  public String getAll() {
    return this.marca + " " + this.modelo + " " + this.matricula;
  }

  public void setAll(String data) {
    String[] parts = data.split(" ");
    this.marca = parts[0];
    this.modelo = parts[1];
    this.matricula = parts[2];
  }

  public Long getId() {
    return this.id;
  }

  public String getMarca() {
    return this.marca;
  }

  public String getModelo() {
    return this.modelo;
  }

  public String getMatricula() {
    return this.matricula;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

}