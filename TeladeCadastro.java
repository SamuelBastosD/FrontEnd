import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TeladeCadastro extends JFrame
{
    private final JLabel lblNome;
    private final JTextField txtNome;

    private final JLabel lblEmail;
    private final JTextField txtEmail;

    private final JLabel lblSenha;
    private final JPasswordField txtSenha;

    private final JButton btnCadastrar;

    private final JLabel lblNotificacoes;

    public TeladeCadastro()
    {
        super("Tela de Cadastro");
        setLayout(new GridLayout(5, 2, 5, 5));

        lblNome = new JLabel("Nome:");
        add(lblNome);

        txtNome = new JTextField(20);
        add(txtNome);

        lblEmail = new JLabel("Email:");
        add(lblEmail);

        txtEmail = new JTextField(20);
        add(txtEmail);

        lblSenha = new JLabel("Senha:");
        add(lblSenha);

        txtSenha = new JPasswordField(20);
        add(txtSenha);

        btnCadastrar = new JButton("Cadastrar");
        add(btnCadastrar);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        add(lblNotificacoes);

        btnCadastrar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (txtNome.getText().trim().length() <= 0) {
                        lblNotificacoes.setText(setHtmlFormat("É necessário digitar um Nome para o cadastro. Por favor, digite um nome e tente novamente."));
                        txtNome.requestFocus();
                        return;
                    }

                    if (txtEmail.getText().trim().length() <= 0) {
                        lblNotificacoes.setText(setHtmlFormat("É necessário digitar um Email para o cadastro. Por favor, digite um email e tente novamente."));
                        txtEmail.requestFocus();
                        return;
                    }

                    if (String.valueOf(txtSenha.getPassword()).trim().length() <= 0) {
                        lblNotificacoes.setText(setHtmlFormat("É necessario digitar uma Senha para o cadastro. Por favor, digite uma senha e tente novamente"));
                        txtSenha.requestFocus();
                        return;
                    }

                    try {
                        Connection conexao = MySQLConnector.conectar();
                        String strSqlEmail = "select * from `db_senac`.`tbl_senac` where `email` = '" + txtEmail.getText() + "';";
                        Statement stmSqlEmail = conexao.createStatement();
                        ResultSet rstSqlEmail = stmSqlEmail.executeQuery(strSqlEmail);
                        if (rstSqlEmail.next()) {
                            lblNotificacoes.setText(setHtmlFormat("Ops! Já existe um usuário utilizando este email. Por favor, digite outro email e tente novamente."));
                        } else {
                            lblNotificacoes.setText(setHtmlFormat("Login liberado para cadastro."));
                            String strSqlCadastrar = "insert into `db_senac`.`tbl_senac` (`nome`, `email`, `senha`) values ('" + txtNome.getText() + "', '" + txtEmail.getText() + "', '" + String.valueOf(txtSenha.getPassword()) + "');";
                            // System.out.println(strSqlCadastrar);
                            Statement stmSqlCadastrar = conexao.createStatement();
                            stmSqlCadastrar.addBatch(strSqlCadastrar);
                            stmSqlCadastrar.executeBatch();
                            lblNotificacoes.setText(setHtmlFormat("Cadastro realizado com sucesso"));
                        }
                        stmSqlEmail.close();
                    } catch (Exception e) {
                        lblNotificacoes.setText(setHtmlFormat("Não foi possível prosseguir com o cadastro! Por favor, verifique e tente novamente."));
                        System.err.println("Erro: " + e);
                    }
                }
            }
        );

        setSize(250, 600);
        setVisible(true);
    }

    private String setHtmlFormat(String strTexto){
        return "<html><body>" + strTexto + "</body></html>";
    }

    public static void main(String[] args) {
        TeladeCadastro appTeladeCadastro = new TeladeCadastro();
        appTeladeCadastro.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}