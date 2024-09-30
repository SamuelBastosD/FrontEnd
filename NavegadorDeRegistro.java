import java.sql.*;
import java.util.*;

public class NavegadorDeRegistro extends TelaDeAtualizacao {

    // Método responsável por popular o combobox com os IDs disponíveis no banco de dados
    public static void popularIds() {
        try {
            // Lista temporária para armazenar os IDs
            ArrayList<String> idsTemp = new ArrayList<>();
            // Adiciona uma opção padrão no início
            idsTemp.add("Selecione aqui o id");
            
            // Conecta ao banco de dados
            Connection conexao = MySQLConnector.conectar();
            // Consulta SQL para selecionar todos os registros ordenados por ID
            String strSqlPopularIds = "select * from `db_senac`.`tbl_senac` order by `id` asc;";
            Statement stmSqlPopularIds = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Executa a consulta
            ResultSet rstSqlPopularIds = stmSqlPopularIds.executeQuery(strSqlPopularIds);

            // Itera sobre o resultado da consulta e adiciona os IDs à lista
            while (rstSqlPopularIds.next()) {
                idsTemp.add(rstSqlPopularIds.getString("id"));
            }

            // Converte a lista temporária em um array de Strings e atribui à variável 'ids'
            ids = idsTemp.toArray(new String[0]);

            // Fecha o Statement para liberar recursos
            stmSqlPopularIds.close();
        } catch (Exception e) {
            // Exibe mensagem de erro no componente de notificações
            lblNotificacoes.setText(setHtmlFormat("Não foi possível encontrar os ids! Por favor, verifique e tente novamente."));
            // Exibe o erro no console
            System.err.println("Erro: " + e);
        }
    }

    // Método responsável por atualizar um registro baseado no ID selecionado
    public static void atualizarId() {
        try {
            String atualizarNome = "";
            String atualizarEmail = "";
            String atualizarSenha = "";

            // Verifica se o nome foi alterado
            if (!txtNome.getText().trim().equals(nomeAtual)) {
                atualizarNome = "`nome` = '" + txtNome.getText() + "'";
            }

            // Verifica se o email foi alterado
            if (!txtEmail.getText().trim().equals(emailAtual)) {
                // Se o nome já tiver sido alterado, adiciona um "and" para concatenar a SQL
                if (atualizarNome.length() > 0) {
                    atualizarEmail = " and ";
                }
                atualizarEmail += "`email` = '" + txtEmail.getText() + "'";
            }

            // Verifica se a senha foi alterada
            if (!String.valueOf(txtSenha.getPassword()).trim().equals(senhaAtual)) {
                // Se o nome ou email já tiverem sido alterados, concatena o "and"
                if (atualizarNome.length() > 0 || atualizarEmail.length() > 0) {
                    atualizarSenha = " and ";
                }
                atualizarSenha += "`senha` = '" + String.valueOf(txtSenha.getPassword()) + "'";
            }

            // Se algum dos campos foi alterado, realiza a atualização no banco de dados
            if (atualizarNome.length() > 0 || atualizarEmail.length() > 0 || atualizarSenha.length() > 0) {
                // Conecta ao banco de dados
                Connection conexao = MySQLConnector.conectar();
                // Monta a consulta SQL de atualização
                String strSqlAtualizarId = "update `db_senac`.`tbl_senac` set " + atualizarNome + atualizarEmail + atualizarSenha + " where `id` = " + cbxId.getSelectedItem().toString() + ";";
                
                // Cria o Statement para executar a consulta
                Statement stmSqlAtualizarId = conexao.createStatement();
                // Adiciona a consulta ao batch
                stmSqlAtualizarId.addBatch(strSqlAtualizarId);
                // Executa o batch de atualização
                stmSqlAtualizarId.executeBatch();

                // Atualiza os valores atuais com os novos valores
                nomeAtual = txtNome.getText();
                emailAtual = txtEmail.getText();
                senhaAtual = String.valueOf(txtSenha.getPassword());

                // Fecha o Statement
                stmSqlAtualizarId.close();
                
                // Notifica o sucesso da atualização
                lblNotificacoes.setText("O id " + cbxId.getSelectedItem().toString() + " foi atualizado com sucesso!");
            } else {
                // Se não houve alteração nos campos, notifica que não há nada para atualizar
                lblNotificacoes.setText("Não foram encontradas alterações para atualizar o id " + cbxId.getSelectedItem().toString());
            }
        } catch (Exception e) {
            // Notifica falha ao tentar atualizar o registro
            lblNotificacoes.setText(setHtmlFormat("Não foi possível atualizar o id! Por favor, verifique e tente novamente."));
            // Exibe o erro no console
            System.err.println("Erro: " + e);
        }
    }

    // Método para limpar os campos de texto e redefinir a seleção do combobox
    public static void limparCampos() {
        // Limpa os campos de nome, email e senha
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        // Reseta o combobox para o primeiro item (índice 0)
        cbxId.setSelectedIndex(0);
    }

    // Método para atualizar os campos de texto com os dados de um registro baseado no ID selecionado
    public static void atualizarCampos(String id) {
        try {
            // Verifica se o ID selecionado no combobox é válido
            if (cbxId.getSelectedIndex() > 0) {
                // Conecta ao banco de dados
                Connection conexao = MySQLConnector.conectar();
                // Consulta SQL para buscar o registro correspondente ao ID selecionado
                String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where `id` = " + id + ";";
                Statement stmSqlAtualizarCampos = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                // Executa a consulta
                ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);

                // Se o registro for encontrado, preenche os campos de texto com os dados
                if (rstSqlAtualizarCampos.next()) {
                    txtNome.setText(rstSqlAtualizarCampos.getString("nome"));
                    nomeAtual = txtNome.getText();
                    txtEmail.setText(rstSqlAtualizarCampos.getString("email"));
                    emailAtual = txtEmail.getText();
                    txtSenha.setText(rstSqlAtualizarCampos.getString("senha"));
                    senhaAtual = String.valueOf(txtSenha.getPassword());

                    // Notifica sucesso na atualização dos campos
                    lblNotificacoes.setText("Campos atualizados com sucesso!");
                } else {
                    // Notifica que o ID selecionado não foi encontrado
                    lblNotificacoes.setText("Ops! Não foi encontrado o id selecionado. Por favor, verifique e tente novamente.");
                }

                // Fecha o Statement
                stmSqlAtualizarCampos.close();
            } else {
                // Se o ID não for válido, notifica o usuário para selecionar um ID e limpa os campos
                lblNotificacoes.setText("Selecione um id para continuar.");
                limparCampos();
            }
        } catch (Exception e) {
            // Notifica falha ao tentar encontrar os IDs
            lblNotificacoes.setText(setHtmlFormat("Não foi possível encontrar os ids! Por favor, verifique e tente novamente."));
            // Exibe o erro no console
            System.err.println("Erro: " + e);
        }
    }
}
