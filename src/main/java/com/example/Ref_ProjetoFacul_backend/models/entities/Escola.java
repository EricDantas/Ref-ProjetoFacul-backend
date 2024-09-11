package com.example.Ref_ProjetoFacul_backend.models.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Escola {
    //Atributos
    @Id
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    //Metodos
    public Escola(){

    }

    public Escola(EscolaDTO escolaDTO){
        this.nome = escolaDTO.nome();
        this.telefone = escolaDTO.telefone();
        this.email = escolaDTO.email();
        this.endereco = escolaDTO.endereco();
    }

}