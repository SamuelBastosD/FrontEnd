import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.*;
import javax.swing.*;

//  Classe CheckBoxFrame que estende JFrame para criar uma interface gráfica
//  onde o estilo da fonte de um JTextField pode ser alterado usando JCheckBoxes.
public class CheckBoxFrame extends JFrame 
{
   // Componentes da interface
   private final JTextField textField; 
   private final JCheckBox boldJCheckBox; 
   private final JCheckBox italicJCheckBox; 
   private final JCheckBox underlineJCheckBox;
   
   public CheckBoxFrame()
   {
      // Construtor da classe CheckBoxFrame.
      // Configura a interface gráfica e adiciona os componentes.
      super("JCheckBox Test"); // Define o título da janela
      setLayout(new FlowLayout()); // Define o layout do JFrame

      // Inicializa e adiciona o JTextField
      textField = new JTextField("Watch the font style change", 20);
      textField.setFont(new Font("Serif", Font.PLAIN, 14)); // Define a fonte inicial
      add(textField);

      // Inicializa e adiciona as caixas de seleção
      boldJCheckBox = new JCheckBox("Bold"); 
      italicJCheckBox = new JCheckBox("Italic"); 
      underlineJCheckBox = new JCheckBox("Underline");
      add(boldJCheckBox); 
      add(italicJCheckBox); 
      add(underlineJCheckBox);

      // Cria o manipulador de eventos e o associa às caixas de seleção
      CheckBoxHandler handler = new CheckBoxHandler();
      boldJCheckBox.addItemListener(handler);
      italicJCheckBox.addItemListener(handler);
      underlineJCheckBox.addItemListener(handler);
   } 

    // Classe interna CheckBoxHandler que implementa ItemListener.
   // Manipula eventos de seleção das JCheckBoxes e atualiza a fonte do JTextField.
   private class CheckBoxHandler implements ItemListener 
   {
      //    Método chamado quando o estado de um Item (JCheckBox) muda.
      //    Atualiza a fonte do JTextField de acordo com as caixas de seleção. 
      //    @param event O evento gerado quando o estado do Item muda.
      @Override
      public void itemStateChanged(ItemEvent event)
      {
         Font font = null;

         // Determina o estilo da fonte com base nas caixas de seleção
         if (boldJCheckBox.isSelected() && italicJCheckBox.isSelected())
            font = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
         else if (boldJCheckBox.isSelected())
            font = new Font("Serif", Font.BOLD, 14);
         else if (italicJCheckBox.isSelected())
            font = new Font("Serif", Font.ITALIC, 14);
         else
            font = new Font("Serif", Font.PLAIN, 14);

         textField.setFont(font);
   
         // Aplica ou remove o sublinhado com base na caixa de seleção
         if (underlineJCheckBox.isSelected()){
            font = textField.getFont();
            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            textField.setFont(font.deriveFont(attributes));
            font = new Font(attributes);
         }
      } 
   }
}
