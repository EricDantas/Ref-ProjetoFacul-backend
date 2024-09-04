package com.example.Ref_ProjetoFacul_backend.config.postgre;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Padrao Singleton
public class ConexaoBD {

    //Atributo
    private static Connection conexao = null;

    //Metodos
    public ConexaoBD(){}

    public static Connection getConexao() throws Exception{
        try {
            if(conexao == null){
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/faculdade_BD";
                String user = "postgres";
                String password = "12345";

                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
            }

        } catch (ClassNotFoundException erro) {
            //Erro de não encontrar o drive do banco no projeto
            throw new Exception("Erro No Drive do Banco De Dados: "+erro.getMessage());
        }
        catch(SQLException erro){
            //Erro no banco de dados: usuario, senha ou banco de dados
            throw new Exception("Erro no Banco de Dados: " + erro.getMessage());
        }

        return conexao;
    }

    public static void closeConexao() throws Exception{
        try {
            if(conexao != null) conexao.close();
        } catch (SQLException erro) {
            //Erro no banco de dados
            throw new Exception("Erro no Banco de Dados: " + erro.getMessage());
        }
    }
}