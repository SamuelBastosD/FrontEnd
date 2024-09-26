// Importação das bibliotecas necessárias para trabalhar com SQL e coleções
import java.sql.*;
import java.util.*;

// A classe NavegadorDeRegistro herda de TelaDeAtualizacao para reutilizar seus componentes visuais
public class NavegadorDeRegistro extends TelaDeAtualizacao {

    // Método responsável por popular o ComboBox de IDs
    public static void popularIds() {
        try {
            // Criação de uma lista temporária para armazenar os IDs
            ArrayList<String> idsTemp = new ArrayList<>();
            idsTemp.add("Selecione aqui o id"); // Adiciona uma opção padrão

            // Estabelece conexão com o banco de dados
            Connection conexao = MySQLConnector.conectar();

            // SQL para selecionar todos os registros da tabela tbl_senac
            String strSqlPopularIds = "select * from `db_senac`.`tbl_senac` order by `id` asc;";
            
            // Criação do Statement para executar a query SQL
            Statement stmSqlPopularIds = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Execução da query e armazenando o resultado no ResultSet
            ResultSet rstSqlPopularIds = stmSqlPopularIds.executeQuery(strSqlPopularIds);

            // Itera sobre os resultados e adiciona os IDs à lista temporária
            while (rstSqlPopularIds.next()) {
                idsTemp.add(rstSqlPopularIds.getString("id"));
            }

            // Converte a lista de IDs para um array e o atribui ao ComboBox
            ids = idsTemp.toArray(new String[0]);

            // Fecha o Statement após o uso
            stmSqlPopularIds.close();
        } catch (Exception e) {
            // Exibe uma mensagem de erro em caso de falha na conexão ou execução da query
            lblNotificacoes.setText(setHtmlFormat("Não foi possível encontrar os ids! Por favor, verifique e tente novamente."));
            System.err.println("Erro: " + e); // Imprime o erro no console
        }
    }

    // Método responsável por atualizar os dados de um ID selecionado
    public static void atualizarId() {
        try {
            // Variáveis para armazenar as partes da query de atualização
            String atualizarNome = "";
            String atualizarEmail = "";
            String atualizarSenha = "";

            // Verifica se o campo Nome foi alterado
            if (!txtNome.getText().trim().equals(nomeAtual)) {
                atualizarNome = "`nome` = '" + txtNome.getText() + "'";
            }

            // Verifica se o campo Email foi alterado e formata a query
            if (!txtEmail.getText().trim().equals(emailAtual)) {
                if (atualizarNome.length() > 0) {
                    atualizarEmail = " and ";
                }
                atualizarEmail += "`email` = '" + txtEmail.getText() + "'";
            }

            // Verifica se o campo Senha foi alterado e formata a query
            if (!String.valueOf(txtSenha.getPassword()).trim().equals(senhaAtual)) {
                if (atualizarNome.length() > 0 || atualizarEmail.length() > 0) {
                    atualizarSenha = " and ";
                }
                atualizarSenha += "`senha` = '" + String.valueOf(txtSenha.getPassword()) + "'";
            }

            // Se houver alterações nos campos, executa a atualização
            if (atualizarNome.length() > 0 || atualizarEmail.length() > 0 || atualizarSenha.length() > 0) {
                Connection conexao = MySQLConnector.conectar(); // Conecta ao banco de dados

                // Monta a query SQL de atualização com os campos alterados
                String strSqlAtualizarId = "update `db_senac`.`tbl_senac` set " + atualizarNome + atualizarEmail + atualizarSenha + 
                                           " where `id` = " + cbxId.getSelectedItem().toString() + ";";
                System.out.println(strSqlAtualizarId); // Exibe a query no console para fins de depuração

                // Criação do Statement para executar a query de atualização
                Statement stmSqlAtualizarId = conexao.createStatement();
                stmSqlAtualizarId.addBatch(strSqlAtualizarId); // Adiciona a query ao batch
                stmSqlAtualizarId.executeBatch(); // Executa a query

                // Atualiza as variáveis com os novos valores dos campos
                nomeAtual = txtNome.getText();
                emailAtual = txtEmail.getText();
                senhaAtual = String.valueOf(txtSenha.getPassword());

                // Fecha o Statement após o uso
                stmSqlAtualizarId.close();

                // Exibe uma mensagem de sucesso na interface
                lblNotificacoes.setText("O id " + cbxId.getSelectedItem().toString() + " foi atualizado com sucesso!");
            } else {
                // Se não houver alterações, informa o usuário
                lblNotificacoes.setText("Não foram encontradas alterações para atualizar o id " + cbxId.getSelectedItem().toString());
            }
        } catch (Exception e) {
            // Exibe uma mensagem de erro em caso de falha na atualização
            lblNotificacoes.setText(setHtmlFormat("Não foi possível atualizar o id! Por favor, verifique e tente novamente."));
            System.err.println("Erro: " + e); // Imprime o erro no console
        }
    }

    // Método responsável por limpar os campos da interface
    public static void limparCampos() {
        txtNome.setText(""); // Limpa o campo Nome
        txtEmail.setText(""); // Limpa o campo Email
        txtSenha.setText(""); // Limpa o campo Senha
        cbxId.setSelectedIndex(0); // Reseta a seleção do ComboBox
    }

    // Método responsável por atualizar os campos da interface com os dados do ID selecionado
    public static void atualizarCampos(String id) {
        try {
            // Verifica se um ID válido foi selecionado
            if (cbxId.getSelectedIndex() > 0) {
                Connection conexao = MySQLConnector.conectar(); // Conecta ao banco de dados

                // SQL para buscar os dados do ID selecionado
                String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where `id` = " + id + ";";
                Statement stmSqlAtualizarCampos = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // Execução da query e armazenando o resultado no ResultSet
                ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);

                // Se encontrar o registro, preenche os campos com os dados
                if (rstSqlAtualizarCampos.next()) {
                    txtNome.setText(rstSqlAtualizarCampos.getString("nome")); // Atualiza o campo Nome
                    nomeAtual = txtNome.getText(); // Armazena o valor atual do Nome
                    txtEmail.setText(rstSqlAtualizarCampos.getString("email")); // Atualiza o campo Email
                    emailAtual = txtEmail.getText(); // Armazena o valor atual do Email
                    txtSenha.setText(rstSqlAtualizarCampos.getString("senha")); // Atualiza o campo Senha
                    senhaAtual = String.valueOf(txtSenha.getPassword()); // Armazena o valor atual da Senha
                    lblNotificacoes.setText("Campos atualizados com sucesso!"); // Mensagem de sucesso
                } else {
                    // Se o ID não for encontrado, informa o usuário
                    lblNotificacoes.setText("Ops! Não foi encontrado o id selecionado. Por favor, verifique e tente novamente.");
                }
                // Fecha o Statement após o uso
                stmSqlAtualizarCampos.close();
            } else {
                // Se nenhum ID válido for selecionado, pede para selecionar um ID e limpa os campos
                lblNotificacoes.setText("Selecione um id para continuar.");
                limparCampos(); // Limpa os campos
            }
        } catch (Exception e) {
            // Exibe uma mensagem de erro em caso de falha na busca dos dados
            lblNotificacoes.setText(setHtmlFormat("Não foi possível encontrar os ids! Por favor, verifique e tente novamente."));
            System.err.println("Erro: " + e); // Imprime o erro no console
        }
    }
}
