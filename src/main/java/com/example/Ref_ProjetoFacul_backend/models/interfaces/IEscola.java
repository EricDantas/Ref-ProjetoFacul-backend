package com.example.Ref_ProjetoFacul_backend.models.interfaces;

import com.example.Ref_ProjetoFacul_backend.models.entities.EscolaDTO;

import java.util.Iterator;


public interface IEscola {
    void incluir(EscolaDTO escolaDTO)throws Exception;
    void alterar(int id, EscolaDTO escolaData)throws Exception;
    void excluirPorId(int id)throws Exception;
    Iterator consultarPorId(int id)throws Exception;
    Iterator listagem() throws Exception;
}
