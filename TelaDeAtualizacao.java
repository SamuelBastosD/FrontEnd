import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeAtualizacao extends JFrame {
    // Componentes da interface gráfica e variáveis para armazenar valores atuais
    public static JLabel lblId; // Label para o campo de ID
    public static JComboBox<String> cbxId; // ComboBox para seleção de ID
    public static String[] ids; // Array de IDs que será populado com os valores do banco

    public static JLabel lblNome; // Label para o campo de Nome
    public static JTextField txtNome; // Campo de texto para o Nome
    public static String nomeAtual; // Variável para armazenar o nome atual

    public static JLabel lblEmail; // Label para o campo de Email
    public static JTextField txtEmail; // Campo de texto para o Email
    public static String emailAtual; // Variável para armazenar o email atual

    public static JLabel lblSenha; // Label para o campo de Senha
    public static JPasswordField txtSenha; // Campo de texto para a senha (oculta)
    public static String senhaAtual; // Variável para armazenar a senha atual

    public static JLabel lblNotificacoes; // Label para exibir notificações ao usuário

    public static JButton btnAtualizar; // Botão para realizar a atualização do registro
    public static JButton btnCancelar; // Botão para limpar os campos

    public static int tamanhoInputs = 20; // Tamanho padrão dos campos de entrada

    // Construtor da tela de atualização
    public TelaDeAtualizacao() {
        super("Tela de Atualização"); // Título da janela
        setLayout(new GridLayout(6, 1, 5, 5)); // Define layout da tela com 6 linhas e 1 coluna

        // Linha de ID
        JPanel linha_id = new JPanel(new GridLayout(1, 2));
        lblId = new JLabel("Id:", SwingConstants.RIGHT); // Label para o campo de ID
        linha_id.add(lblId);

        NavegadorDeRegistro.popularIds(); // Popula o ComboBox com IDs disponíveis
        cbxId = new JComboBox<>(ids); // ComboBox para seleção de ID
        linha_id.add(cbxId);
        add(linha_id); // Adiciona a linha de ID à tela

        // Linha de Nome
        JPanel linha_nome = new JPanel(new GridLayout(1, 2));
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT); // Label para o campo de Nome
        linha_nome.add(lblNome);
        txtNome = new JTextField(tamanhoInputs); // Campo de texto para o Nome
        linha_nome.add(txtNome);
        add(linha_nome); // Adiciona a linha de Nome à tela

        // Linha de Email
        JPanel linha_email = new JPanel(new GridLayout(1, 2));
        lblEmail = new JLabel("Email:", SwingConstants.RIGHT); // Label para o campo de Email
        linha_email.add(lblEmail);
        txtEmail = new JTextField(tamanhoInputs); // Campo de texto para o Email
        linha_email.add(txtEmail);
        add(linha_email); // Adiciona a linha de Email à tela

        // Linha de Senha
        JPanel linha_senha = new JPanel(new GridLayout(1, 2));
        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT); // Label para o campo de Senha
        linha_senha.add(lblSenha);
        txtSenha = new JPasswordField(tamanhoInputs); // Campo de texto para a Senha (campo de senha oculta)
        linha_senha.add(txtSenha);
        add(linha_senha); // Adiciona a linha de Senha à tela

        // Linha de botões (Atualizar e Cancelar)
        JPanel linha_botoes = new JPanel(new GridLayout(1, 2));
        btnAtualizar = new JButton("Atualizar"); // Botão para atualizar os dados
        linha_botoes.add(btnAtualizar);
        btnCancelar = new JButton("Cancelar"); // Botão para cancelar a operação e limpar campos
        linha_botoes.add(btnCancelar);
        add(linha_botoes); // Adiciona a linha de botões à tela

        // Linha de notificações
        JPanel linha_notificacoes = new JPanel(new GridLayout(1, 1));
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER); // Label para exibir mensagens de notificação
        linha_notificacoes.add(lblNotificacoes);
        add(linha_notificacoes); // Adiciona a linha de notificações à tela

        // Adiciona o listener para o botão Atualizar
        btnAtualizar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    // Chama o método para atualizar o registro quando o botão é clicado
                    NavegadorDeRegistro.atualizarId();
                }
            }
        );

        // Adiciona o listener para o botão Cancelar
        btnCancelar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    // Chama o método para limpar os campos quando o botão é clicado
                    NavegadorDeRegistro.limparCampos();
                }
            }
        );

        // Listener para o ComboBox de IDs, que atualiza os campos quando um ID é selecionado
        cbxId.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        // Chama o método para atualizar os campos baseados no ID selecionado
                        NavegadorDeRegistro.atualizarCampos(cbxId.getSelectedItem().toString());
                    }
                }
            }
        );

        // Configurações adicionais da janela
        setSize(250, 300); // Define o tamanho da janela
        ImageIcon img = new ImageIcon("./senac-logo.png"); // Define um ícone para a janela
        setIconImage(img.getImage());
        setVisible(true); // Torna a janela visível
        cbxId.requestFocus(); // Define o foco no ComboBox de IDs ao abrir a janela
    }

    // Método para formatar texto como HTML para exibição no JLabel
    public static String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>";
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        // Cria uma instância da tela de atualização
        TelaDeAtualizacao appTelaDeAtualizacao = new TelaDeAtualizacao();
        appTelaDeAtualizacao.setDefaultCloseOperation(EXIT_ON_CLOSE); // Define comportamento ao fechar a janela
    }
}
