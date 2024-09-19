import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * A classe TelaDePesquisa é uma janela de pesquisa que permite ao usuário buscar 
 * informações em um banco de dados baseado em nome ou email. Ela apresenta campos 
 * de entrada para pesquisa e exibe notificações sobre o resultado da pesquisa.
 */
public class TelaDePesquisa extends JFrame {
    // Componentes da interface
    private final JLabel lblPesquisa;
    private final JTextField txtPesquisa;
    private final JLabel lblId;
    private final JTextField txtId;
    private final JLabel lblNome;
    private final JTextField txtNome;
    private final JLabel lblEmail;
    private final JTextField txtEmail;
    private final JButton btnPesquisar;
    private final JButton btnInicio;
    private final JButton btnAnterior;
    private final JButton btnProximo;
    private final JButton btnUltimo;
    private final JLabel lblNotificacoes;
    private final int tamanhoInputs = 20; // Tamanho padrão para os campos de texto

    // Construtor da classe
    public TelaDePesquisa() {
        super("Tela de Pesquisa"); // Define o título da janela
        setLayout(new GridLayout(7, 1, 5, 5)); // Define o layout da janela

        // Painel para o rótulo e botão de pesquisa
        JPanel linha_lblPesquisa = new JPanel(new GridLayout(1, 2));
        lblPesquisa = new JLabel("Pesquisa:", SwingConstants.CENTER);
        linha_lblPesquisa.add(lblPesquisa);
        
        btnPesquisar = new JButton("🔍");
        btnPesquisar.setToolTipText("Pesquisar");
        linha_lblPesquisa.add(btnPesquisar);
        add(linha_lblPesquisa);

        // Painel para o campo de pesquisa
        JPanel linha_inputPesquisa = new JPanel(new GridLayout(1, 1));
        txtPesquisa = new JTextField(tamanhoInputs);
        linha_inputPesquisa.add(txtPesquisa);
        add(linha_inputPesquisa);

        // Painel para o ID
        JPanel linha_id = new JPanel(new GridLayout(1, 2));
        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        linha_id.add(lblId);
        txtId = new JTextField(tamanhoInputs);
        linha_id.add(txtId);
        add(linha_id);

        // Painel para o Nome
        JPanel linha_nome = new JPanel(new GridLayout(1, 2));
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        linha_nome.add(lblNome);
        txtNome = new JTextField(tamanhoInputs);
        linha_nome.add(txtNome);
        add(linha_nome);

        // Painel para o Email
        JPanel linha_email = new JPanel(new GridLayout(1, 2));
        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        linha_email.add(lblEmail);
        txtEmail = new JTextField(10); // Tamanho fixo para o email
        linha_email.add(txtEmail);
        add(linha_email);

        // Painel para os botões de navegação
        JPanel linha_botoes = new JPanel(new GridLayout(1, 4));
        btnInicio = new JButton("<<");
        linha_botoes.add(btnInicio);
        btnAnterior = new JButton("<");
        linha_botoes.add(btnAnterior);
        btnProximo = new JButton(">");
        linha_botoes.add(btnProximo);
        btnUltimo = new JButton(">>");
        linha_botoes.add(btnUltimo);
        add(linha_botoes);

        // Painel para as notificações
        JPanel linha_notificacoes = new JPanel(new GridLayout(1, 1));
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        linha_notificacoes.add(lblNotificacoes);
        add(linha_notificacoes);

        // Ação ao clicar no botão de pesquisar
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Verifica se o campo de pesquisa está vazio
                if (txtPesquisa.getText().trim().length() <= 0) {
                    lblNotificacoes.setText(setHtmlFormat("Por favor, digite algo e tente novamente.")); // Notificação de erro
                    txtPesquisa.requestFocus(); // Foca no campo de pesquisa
                    return; // Sai do método
                }

                try {
                    // Conexão com o banco de dados
                    Connection conexao = MySQLConnector.conectar();
                    // Monta a consulta SQL para pesquisar
                    String strSqlPesquisa = "SELECT * FROM `db_senac`.`tbl_senac` WHERE `nome` LIKE '%" + txtPesquisa.getText() + "%' OR `email` LIKE '%" + txtPesquisa.getText() + "%';";
                    Statement stmSqlPesquisa = conexao.createStatement(); // Cria um objeto Statement para executar a consulta
                    ResultSet rstSqlPesquisa = stmSqlPesquisa.executeQuery(strSqlPesquisa); // Executa a consulta

                    // Verifica se há resultados
                    if (rstSqlPesquisa.next()) {
                        lblNotificacoes.setText(setHtmlFormat("Legal! Foi(Foram) encontrado(s) resultado(s).")); // Notificação de sucesso
                    } else {
                        lblNotificacoes.setText(setHtmlFormat("Poxa vida! Não foram encontrados resultados para: \"" + txtPesquisa.getText() + "\".")); // Notificação de não encontrado
                    }
                    stmSqlPesquisa.close(); // Fecha o Statement
                } catch (Exception e) {
                    lblNotificacoes.setText(setHtmlFormat("Não foi possível prosseguir com a pesquisa! Por favor, verifique e tente novamente.")); // Notificação de erro
                    System.err.println("Erro: " + e); // Imprime o erro no console
                }
            }
        });
    }

    /**
     * Formata uma string para HTML.
     * 
     * @param strTexto Texto a ser formatado.
     * @return String formatada em HTML.
     */
    private String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>";
    }

    /**
     * Método principal para executar a aplicação.
     * 
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        TelaDePesquisa appTelaDePesquisa = new TelaDePesquisa();
        appTelaDePesquisa.setVisible(true); // Exibe a tela
    }
}
