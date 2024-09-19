import java.awt.*; // Importa classes de layout e componentes gráficos
import java.awt.event.*; // Importa classes para tratamento de eventos
import javax.swing.*; // Importa classes para a interface gráfica
import java.sql.*; // Importa classes para conexão com banco de dados

public class TelaDePesquisa extends JFrame {
    // Declaração dos componentes da interface
    private final JLabel lblPesquisa; // Rótulo para pesquisa
    private final JTextField txtPesquisa; // Campo de texto para entrada de pesquisa

    private final JLabel lblId; // Rótulo para ID
    private final JTextField txtId; // Campo de texto para ID

    private final JLabel lblNome; // Rótulo para Nome
    private final JTextField txtNome; // Campo de texto para Nome

    private final JLabel lblEmail; // Rótulo para Email
    private final JTextField txtEmail; // Campo de texto para Email

    private final JButton btnPesquisar; // Botão para executar a pesquisa
    private final JButton btnInicio; // Botão para ir ao primeiro resultado
    private final JButton btnAnterior; // Botão para ir ao resultado anterior
    private final JButton btnProximo; // Botão para ir ao próximo resultado
    private final JButton btnUltimo; // Botão para ir ao último resultado

    private final JLabel lblNotificacoes; // Rótulo para exibir notificações

    private final int tamanhoInputs = 20; // Tamanho padrão para campos de texto

    // Construtor da classe
    public TelaDePesquisa() {
        super("Tela de Pesquisa"); // Define o título da janela
        setLayout(new GridLayout(7, 1, 5, 5)); // Define o layout da janela

        // Painel para rótulo e botão de pesquisa
        JPanel linha_lblPesquisa = new JPanel(new GridLayout(1, 2));
        lblPesquisa = new JLabel("Pesquisa:", SwingConstants.CENTER); // Cria rótulo de pesquisa
        linha_lblPesquisa.add(lblPesquisa); // Adiciona rótulo ao painel

        btnPesquisar = new JButton("🔍"); // Cria botão de pesquisa
        btnPesquisar.setToolTipText("Pesquisar"); // Define texto de ajuda para o botão
        linha_lblPesquisa.add(btnPesquisar); // Adiciona botão ao painel

        add(linha_lblPesquisa); // Adiciona painel à janela

        // Painel para campo de pesquisa
        JPanel linha_inputPesquisa = new JPanel(new GridLayout(1, 1));
        txtPesquisa = new JTextField(tamanhoInputs); // Cria campo de texto para pesquisa
        linha_inputPesquisa.add(txtPesquisa); // Adiciona campo ao painel

        add(linha_inputPesquisa); // Adiciona painel à janela

        // Painel para ID
        JPanel linha_id = new JPanel(new GridLayout(1, 2));
        lblId = new JLabel("Id:", SwingConstants.RIGHT); // Rótulo para ID
        linha_id.add(lblId); // Adiciona rótulo ao painel

        txtId = new JTextField(tamanhoInputs); // Campo de texto para ID
        linha_id.add(txtId); // Adiciona campo ao painel

        add(linha_id); // Adiciona painel à janela

        // Painel para Nome
        JPanel linha_nome = new JPanel(new GridLayout(1, 2));
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT); // Rótulo para Nome
        linha_nome.add(lblNome); // Adiciona rótulo ao painel

        txtNome = new JTextField(tamanhoInputs); // Campo de texto para Nome
        linha_nome.add(txtNome); // Adiciona campo ao painel

        add(linha_nome); // Adiciona painel à janela

        // Painel para Email
        JPanel linha_email = new JPanel(new GridLayout(1, 2));
        lblEmail = new JLabel("Email:", SwingConstants.RIGHT); // Rótulo para Email
        linha_email.add(lblEmail); // Adiciona rótulo ao painel

        txtEmail = new JTextField(10); // Campo de texto para Email
        linha_email.add(txtEmail); // Adiciona campo ao painel

        add(linha_email); // Adiciona painel à janela

        // Painel para botões de navegação
        JPanel linha_botoes = new JPanel(new GridLayout(1, 4));
        btnInicio = new JButton("<<"); // Botão para ir ao primeiro resultado
        linha_botoes.add(btnInicio); // Adiciona botão ao painel

        btnAnterior = new JButton("<"); // Botão para ir ao resultado anterior
        linha_botoes.add(btnAnterior); // Adiciona botão ao painel

        btnProximo = new JButton(">"); // Botão para ir ao próximo resultado
        linha_botoes.add(btnProximo); // Adiciona botão ao painel

        btnUltimo = new JButton(">>"); // Botão para ir ao último resultado
        linha_botoes.add(btnUltimo); // Adiciona botão ao painel

        add(linha_botoes); // Adiciona painel à janela

        // Painel para notificações
        JPanel linha_notificacoes = new JPanel(new GridLayout(1, 1));
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER); // Rótulo para notificações
        linha_notificacoes.add(lblNotificacoes); // Adiciona rótulo ao painel

        add(linha_notificacoes); // Adiciona painel à janela

        // Ação ao clicar no botão de pesquisar
        btnPesquisar.addActionListener(
            new ActionListener() {
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
                        String strSqlPesquisa = "select * from `db_senac`.`tbl_senac` where `nome` like '%" + txtPesquisa.getText() + "%' or `email` like '%" + txtPesquisa.getText() + "%';";
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
            }
        );

        setSize(250, 300); // Define o tamanho da janela
        setVisible(true); // Torna a janela visível
        txtPesquisa.requestFocus(); // Foca no campo de pesquisa
    }

    // Método para formatar texto em HTML
    private String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>"; // Retorna o texto formatado em HTML
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        TelaDePesquisa appTelaDePesquisa = new TelaDePesquisa(); // Cria uma nova instância da tela de pesquisa
        appTelaDePesquisa.setDefaultCloseOperation(EXIT_ON_CLOSE); // Define a operação de fechamento da janela
    }
}
