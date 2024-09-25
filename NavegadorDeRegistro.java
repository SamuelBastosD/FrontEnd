import java.sql.*;
import java.util.ArrayList;

public class NavegadorDeRegistro extends TelaDeAtualizacao {

    public static void popularIds() {
        try {
            ArrayList<String> idsTemp = new ArrayList<>();
            Connection conexao = MySQLConnector.conectar();
            String strSqlPopularIds = "SELECT * FROM `tbl_senac` ORDER BY `id` ASC;";
            Statement stmSqlPopularIds = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlPopularIds = stmSqlPopularIds.executeQuery(strSqlPopularIds);
            while (rstSqlPopularIds.next()) {
                idsTemp.add(rstSqlPopularIds.getString("id"));
            }
            ids = idsTemp.toArray(new String[0]);
            stmSqlPopularIds.close();
        } catch (Exception e) {
            lblNotificacoes.setText(setHtmlFormat("Não foi possível encontrar os IDs!"));
            System.err.println("Erro: " + e);
        }
    }

    public static void carregarDados(String id) {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlBuscarDados = "SELECT * FROM `tbl_senac` WHERE `id` = ?;";
            PreparedStatement pstm = conexao.prepareStatement(strSqlBuscarDados);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                // Preencher os campos de texto com os dados do banco
                txtNome.setText(rs.getString("nome"));
                txtEmail.setText(rs.getString("email"));
                txtSenha.setText(rs.getString("senha"));
            }
            pstm.close();
        } catch (Exception e) {
            lblNotificacoes.setText(setHtmlFormat("Erro ao carregar os dados do ID: " + id));
            System.err.println("Erro: " + e);
        }
    }
}
