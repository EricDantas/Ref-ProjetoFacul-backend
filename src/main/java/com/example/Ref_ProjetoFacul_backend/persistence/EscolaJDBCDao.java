package com.example.Ref_ProjetoFacul_backend.persistence;

import com.example.Ref_ProjetoFacul_backend.config.postgre.ConexaoBD;
import com.example.Ref_ProjetoFacul_backend.models.entities.Escola;
import com.example.Ref_ProjetoFacul_backend.models.entities.EscolaDTO;
import com.example.Ref_ProjetoFacul_backend.models.interfaces.IEscola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class EscolaJDBCDao implements IEscola {
    // Conexao com o banco
    private Connection conexao = null;

    public EscolaJDBCDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }
    public void finalizarConexao()throws Exception{
        ConexaoBD.closeConexao();
    }

    @Override
    public void incluir(EscolaDTO escolaData) throws Exception {
        try {
            String sql;
            sql = "insert into escola(nome_escola, telefone_escola,"
                    + "email_escola, endereco_escola) values(?,?,?,?)";
            PreparedStatement preparedStamente = conexao.prepareStatement(sql);
            preparedStamente.setString(1, escolaData.nome());
            preparedStamente.setString(2, escolaData.telefone());
            preparedStamente.setString(3, escolaData.email());
            preparedStamente.setString(4, escolaData.endereco());
            preparedStamente.executeUpdate();
        }
        catch (SQLException erro) {
            // Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: "+ erro.getMessage());
        }
        catch (Exception erro){
            throw erro;
        }
    }

    @Override
    public void alterar(int id, EscolaDTO escolaData) throws Exception {
        try {
            PreparedStatement preparedStamente = null;
            preparedStamente = conexao.prepareStatement("update escola set nome_escola = ?," +
                    "telefone_escola= ?,email_escola = ?," +
                    "endereco_escola=? "+
                    "where id_escola = ?");
            preparedStamente.setString(1, escolaData.nome());
            preparedStamente.setString(2, escolaData.telefone());
            preparedStamente.setString(3, escolaData.email());
            preparedStamente.setString(4, escolaData.endereco());
            preparedStamente.setInt(5, id);
            preparedStamente.executeUpdate();
        }
        catch (SQLException erro) {
            // Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: "+ erro.getMessage());
        }
        catch (Exception erro){
            throw erro;
        }

    }

    @Override
    public void excluirPorId(int id) throws Exception {
        try {
            String sql;
            sql = "delete from escola where id_escola = ?";
            PreparedStatement preparedStamente = conexao.prepareStatement(sql);
            preparedStamente.setInt(1, id);
            preparedStamente.executeUpdate();
        }
        catch (SQLException erro) {
            // Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: "+ erro.getMessage());
        }
        catch (Exception erro){
            throw erro;
        }


    }

    @Override
    public Iterator consultarPorId(int id) throws Exception {
        try {
            List<Escola> listaDasEscolas = new LinkedList<Escola>();
            //PreparedStatement preparedStamente = null;
            PreparedStatement preparedStamente = conexao.prepareStatement("select * from escola "
                    + "where id_escola = ?");
            preparedStamente.setInt(1, id);
            ResultSet rs = preparedStamente.executeQuery();
            if(rs.next()){
                Escola obj = new Escola();
                obj.setId(rs.getInt("id_escola"));
                obj.setNome(rs.getString("nome_escola"));
                obj.setTelefone(rs.getString("telefone_escola"));
                obj.setEmail(rs.getString("email_escola"));
                obj.setEndereco(rs.getString("endereco_escola"));
                listaDasEscolas.add(obj);
            }
            return listaDasEscolas.iterator();
        }
        catch (SQLException erro) {
            // Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: "+ erro.getMessage());
        }
        catch (Exception erro){
            throw erro;
        }
    }

    @Override
    public Iterator listagem() throws Exception {
        try {
            List<Escola> listaDasEscolas = new LinkedList<Escola>();
            String sql = "select * from escola";
            PreparedStatement preparedStamente = conexao.prepareStatement(sql);
            ResultSet rs = preparedStamente.executeQuery();
            while(rs.next()){
                Escola escola = new Escola();
                escola.setId(rs.getInt("id_escola"));
                escola.setNome(rs.getString("nome_escola"));
                escola.setTelefone(rs.getString("telefone_escola"));
                escola.setEmail(rs.getString("email_escola"));
                escola.setEndereco(rs.getString("endereco_escola"));
                listaDasEscolas.add(escola);
            }
            return listaDasEscolas.iterator();
        }
        catch (SQLException erro) {
            // Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: "+ erro.getMessage());
        }
        catch (Exception erro){
            throw erro;
        }
    }


}

