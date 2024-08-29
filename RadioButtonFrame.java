import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//  RadioButtonFrame é uma classe que cria uma interface gráfica simples usando Swing.
//  Ela estende JFrame e exibe um JTextField e um conjunto de JRadioButtons que permitem ao usuário
//  selecionar diferentes estilos de fonte para o texto exibido no JTextField.
public class RadioButtonFrame extends JFrame 
{
   // Campos para armazenar referências aos componentes da interface gráfica
   private JTextField textField;

   private Font plainFont;       // Fonte normal
   private Font boldFont;        // Fonte em negrito
   private Font italicFont;      // Fonte em itálico
   private Font boldItalicFont;  // Fonte em negrito e itálico

   private Color blueColor;
   private Color redColor;
   private Color yellowColor;
   private Color blackColor;

   private JRadioButton plainJRadioButton;        // Botão de rádio para fonte normal
   private JRadioButton boldJRadioButton;         // Botão de rádio para fonte em negrito
   private JRadioButton italicJRadioButton;       // Botão de rádio para fonte em itálico
   private JRadioButton boldItalicJRadioButton;   // Botão de rádio para fonte em negrito e itálico
   private ButtonGroup radioGroup;                // Grupo de botões de rádio para garantir a seleção única

   private JRadioButton blueJRadioButton;   // Botão de rádio para cor azul
   private JRadioButton redJRadioButton;    // Botão de rádio para cor vermelha
   private JRadioButton yellowJRadioButton; // Botão de rádio para cor amarela
   private JRadioButton blackJRadioButton;  // Botão de rádio para cor Preta
   private ButtonGroup colorRadioGroup;     // Grupo de botões de rádio para garantir a seleção única de cor

   //  Construtor da classe RadioButtonFrame.
   // Configura a janela do JFrame, adiciona componentes e define comportamentos.
   public RadioButtonFrame()
   {
      super("RadioButton Test"); // Define o título da janela
      setLayout(new FlowLayout()); // Define o layout da janela

      // Cria e adiciona um campo de texto
      textField = new JTextField("Watch the font style change", 25);
      add(textField);

      // Cria e adiciona botões de rádio para diferentes estilos de fonte
      plainJRadioButton = new JRadioButton("Plain", true);
      boldJRadioButton = new JRadioButton("Bold", false);
      italicJRadioButton = new JRadioButton("Italic", false);
      boldItalicJRadioButton = new JRadioButton("Bold/Italic", false);
      add(plainJRadioButton);
      add(boldJRadioButton);
      add(italicJRadioButton);
      add(boldItalicJRadioButton); 

      // Agrupa os botões de rádio para permitir apenas uma seleção por vez
      radioGroup = new ButtonGroup();
      radioGroup.add(plainJRadioButton);
      radioGroup.add(boldJRadioButton);
      radioGroup.add(italicJRadioButton);
      radioGroup.add(boldItalicJRadioButton);

      // Cria e adiciona botões de rádio para diferentes estilos de cor
      blueJRadioButton = new JRadioButton("Blue", true);
      redJRadioButton = new JRadioButton("Red", false);
      yellowJRadioButton = new JRadioButton("Yellow", false);
      blackJRadioButton = new JRadioButton("Black", false);
      add(blueJRadioButton);
      add(redJRadioButton);
      add(yellowJRadioButton);
      add(blackJRadioButton);

      // Agrupa os botões de rádio para permitir apenas uma seleção por vez
      colorRadioGroup = new ButtonGroup();
      colorRadioGroup.add(blueJRadioButton); 
      colorRadioGroup.add(redJRadioButton);
      colorRadioGroup.add(yellowJRadioButton);
      colorRadioGroup.add(blackJRadioButton);

      // Define as fontes para cada estilo
      plainFont = new Font("Serif", Font.PLAIN, 14);
      boldFont = new Font("Serif", Font.BOLD, 14);
      italicFont = new Font("Serif", Font.ITALIC, 14);
      boldItalicFont = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
      textField.setFont(plainFont); // Define a fonte inicial do texto

      blueColor = new Color(0,0,255);
      redColor = new Color(255,0,0);
      yellowColor = new Color(255,255,0);
      blackColor = new Color(0,0,0);

      // Adiciona ouvintes de eventos para os botões de rádio
      plainJRadioButton.addItemListener(
         new RadioButtonHandler(plainFont));
      boldJRadioButton.addItemListener(
         new RadioButtonHandler(boldFont));
      italicJRadioButton.addItemListener(
         new RadioButtonHandler(italicFont));
      boldItalicJRadioButton.addItemListener(
         new RadioButtonHandler(boldItalicFont));

      blueJRadioButton.addItemListener(
         new ColorRadioButtonHandler(blueColor));
      redJRadioButton.addItemListener(
         new ColorRadioButtonHandler(redColor));
      yellowJRadioButton.addItemListener(
         new ColorRadioButtonHandler(yellowColor));
      blackJRadioButton.addItemListener(
         new ColorRadioButtonHandler(blackColor));
   } 
      // RadioButtonHandler é uma classe interna que implementa a interface ItemListener.
      // Ela é usada para atualizar a fonte do JTextField quando um botão de rádio é selecionado.
   private class RadioButtonHandler implements ItemListener 
   {
      private Font font; // Fonte associada ao botão de rádio

      // Construtor da classe RadioButtonHandler.
      // @param f A fonte a ser usada quando o botão de rádio for selecionado.
      public RadioButtonHandler(Font f)
      {
         font = f; 
      } 
      // Método que é chamado quando o estado do botão de rádio muda.
      // Atualiza a fonte do JTextField para a fonte associada ao botão de rádio.
      // @param event O evento de mudança de estado do botão de rádio.
      @Override
      public void itemStateChanged(ItemEvent event)
      {
         textField.setFont(font); 
      } 
   }

      private class ColorRadioButtonHandler implements ItemListener 
      {
         private Color color;
   
         public ColorRadioButtonHandler(Color c)
         {
            color = c; 
         } 
               // Método que é chamado quando o estado do botão de rádio muda.
      // Atualiza a fonte do JTextField para a fonte associada ao botão de rádio.
      // @param event O evento de mudança de estado do botão de rádio.
      @Override
      public void itemStateChanged(ItemEvent event)
      {
         textField.setForeground(color); 
      } 
   } 
}