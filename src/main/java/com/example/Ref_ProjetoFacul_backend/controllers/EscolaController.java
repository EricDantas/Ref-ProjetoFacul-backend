package com.example.Ref_ProjetoFacul_backend.controllers;

import com.example.Ref_ProjetoFacul_backend.models.entities.EscolaDTO;
import com.example.Ref_ProjetoFacul_backend.models.repositories.EscolaRepository;
import com.example.Ref_ProjetoFacul_backend.persistence.EscolaJDBCDao;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequestMapping("api/escolas")
public class EscolaController implements EscolaRepository {
    //atributo
    private EscolaJDBCDao escolaPersistenciaBD;
    public EscolaController() throws Exception{
        try {
            escolaPersistenciaBD = new EscolaJDBCDao();
        } catch (Exception erro) {
            throw erro;
        }
    }

    public void verificarPreenchimento(@RequestBody EscolaDTO escolaData) throws Exception{
        try {
            if(escolaData.nome().isEmpty() ||  escolaData.telefone().isEmpty() || escolaData.email().isEmpty() || escolaData.endereco().isEmpty()){
                throw new Exception("Todos os campos são obrigatorio o preenchimento");
            }
        } catch (Exception erro) {
            throw erro;
        }
    }

    @PostMapping
    @Override
    public void incluir(@RequestBody EscolaDTO escolaData) throws Exception {
        try {
            verificarPreenchimento(escolaData);
            escolaPersistenciaBD.incluir(escolaData);
        } catch (Exception erro) {
            throw erro;
        }

    }
    @PutMapping("/{id}")
    @Override
    public void alterar(@PathVariable("id") int id, @RequestBody EscolaDTO escolaData) throws Exception {
        try {
            //verificarPreenchimento(escolaData);
            escolaPersistenciaBD.alterar(id, escolaData);
        } catch (Exception erro) {
            throw erro;
        }
    }
    @DeleteMapping("/{id}")
    @Override
    public void excluirPorId(@PathVariable("id") int id) throws Exception {
        escolaPersistenciaBD.excluirPorId(id);
    }

    @GetMapping("/{id}")
    @Override
    public Iterator consultarPorId(@PathVariable("id") int id) throws Exception {
        return escolaPersistenciaBD.consultarPorId(id);
    }

    @GetMapping
    @Override
    public Iterator listagem() throws Exception {
        return escolaPersistenciaBD.listagem();
    }

}

