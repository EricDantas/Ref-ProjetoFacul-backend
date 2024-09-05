package com.example.Ref_ProjetoFacul_backend.models.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Escola {
    //Atributos
    @Id
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    //Metodos
    public Escola(EscolaDTO escolaDTO){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

}

