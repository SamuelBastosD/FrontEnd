// Importação das bibliotecas necessárias para criação de interface gráfica
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Definição da classe TelaDeAtualizacao que herda de JFrame (janela gráfica)
public class TelaDeAtualizacao extends JFrame {
    
    // Declaração dos componentes visuais que serão usados na interface
    public static JLabel lblId; // Rótulo para o campo de ID
    public static JComboBox<String> cbxId; // ComboBox para seleção de IDs
    public static String[] ids; // Array de IDs (valores que populam o ComboBox)

    public static JLabel lblNome; // Rótulo para o campo de Nome
    public static JTextField txtNome; // Campo de texto para entrada do Nome
    public static String nomeAtual; // Nome atual do registro selecionado

    public static JLabel lblEmail; // Rótulo para o campo de Email
    public static JTextField txtEmail; // Campo de texto para entrada do Email
    public static String emailAtual; // Email atual do registro selecionado

    public static JLabel lblSenha; // Rótulo para o campo de Senha
    public static JPasswordField txtSenha; // Campo de senha para entrada de Senha
    public static String senhaAtual; // Senha atual do registro selecionado

    public static JLabel lblNotificacoes; // Rótulo para exibir notificações ao usuário

    public static JButton btnAtualizar; // Botão de ação para "Atualizar"
    public static JButton btnCancelar; // Botão de ação para "Cancelar"

    public static int tamanhoInputs = 20; // Tamanho padrão dos campos de entrada

    // Construtor da classe TelaDeAtualizacao
    public TelaDeAtualizacao() {
        // Definição do título da janela
        super("Tela de Atualização");
        
        // Configuração do layout da janela em grade (6 linhas, 1 coluna, espaçamento de 5px)
        setLayout(new GridLayout(6,1,5,5));

        // Criação do painel para o campo de ID (contém label e ComboBox)
        JPanel linha_id = new JPanel(new GridLayout(1, 2));
        lblId = new JLabel("Id:", SwingConstants.RIGHT); // Rótulo à direita
        linha_id.add(lblId);
        NavegadorDeRegistro.popularIds(); // Preenche o array de IDs
        cbxId = new JComboBox(ids); // ComboBox para seleção dos IDs
        linha_id.add(cbxId);
        add(linha_id); // Adiciona o painel à janela

        // Criação do painel para o campo de Nome (contém label e campo de texto)
        JPanel linha_nome = new JPanel(new GridLayout(1, 2));
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT); // Rótulo à direita
        linha_nome.add(lblNome);
        txtNome = new JTextField(tamanhoInputs); // Campo de texto para Nome
        linha_nome.add(txtNome);
        add(linha_nome); // Adiciona o painel à janela

        // Criação do painel para o campo de Email (contém label e campo de texto)
        JPanel linha_email = new JPanel(new GridLayout(1, 2));
        lblEmail = new JLabel("Email:", SwingConstants.RIGHT); // Rótulo à direita
        linha_email.add(lblEmail);
        txtEmail = new JTextField(tamanhoInputs); // Campo de texto para Email
        linha_email.add(txtEmail);
        add(linha_email); // Adiciona o painel à janela

        // Criação do painel para o campo de Senha (contém label e campo de senha)
        JPanel linha_senha = new JPanel(new GridLayout(1, 2));
        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT); // Rótulo à direita
        linha_senha.add(lblSenha);
        txtSenha = new JPasswordField(tamanhoInputs); // Campo de senha para Senha
        linha_senha.add(txtSenha);
        add(linha_senha); // Adiciona o painel à janela

        // Criação do painel para os botões de ação (Atualizar e Cancelar)
        JPanel linha_botoes = new JPanel(new GridLayout(1, 2));
        btnAtualizar = new JButton("Atualizar"); // Botão de Atualizar
        linha_botoes.add(btnAtualizar);
        btnCancelar = new JButton("Cancelar"); // Botão de Cancelar
        linha_botoes.add(btnCancelar);
        add(linha_botoes); // Adiciona o painel à janela

        // Criação do painel para o campo de notificações
        JPanel linha_notificacoes = new JPanel(new GridLayout(1, 1));
        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER); // Rótulo centralizado
        linha_notificacoes.add(lblNotificacoes);
        add(linha_notificacoes); // Adiciona o painel à janela

        // Definição da ação para o botão "Atualizar" (chama método para atualizar o ID)
        btnAtualizar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    NavegadorDeRegistro.atualizarId(); // Atualiza o registro do ID selecionado
                }
            }
        );

        // Definição da ação para o botão "Cancelar" (chama método para limpar os campos)
        btnCancelar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    NavegadorDeRegistro.limparCampos(); // Limpa os campos da interface
                }
            }
        );

        // Ação a ser realizada quando um ID é selecionado no ComboBox
        cbxId.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event) {
                    // Verifica se o evento é de seleção de item
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        NavegadorDeRegistro.atualizarCampos(cbxId.getSelectedItem().toString()); // Atualiza os campos de acordo com o ID selecionado
                    }
                } 
            }
        );

        // Define o tamanho da janela
        setSize(250, 300);

        // Carrega um ícone personalizado para a janela
        ImageIcon img = new ImageIcon("./senac-logo.png");
        setIconImage(img.getImage());

        // Torna a janela visível
        setVisible(true);
        
        // Define o foco inicial no ComboBox de IDs
        cbxId.requestFocus();
    }

    // Método para formatar texto como HTML (opcional para melhorar a apresentação visual de labels)
    public static String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>";
    }

    // Método principal para iniciar o aplicativo
    public static void main(String[] args) {
        TelaDeAtualizacao appTelaDeAtualizacao = new TelaDeAtualizacao(); // Cria a janela
        appTelaDeAtualizacao.setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha o aplicativo ao fechar a janela
    }
}
