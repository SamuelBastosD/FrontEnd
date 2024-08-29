import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// ComboBoxFrame é uma janela que contém um JComboBox e um JLabel.
// O JComboBox exibe uma lista de imagens, e o JLabel mostra a imagem selecionada.
public class ComboBoxFrame extends JFrame 
{
   private final JComboBox<String> imagesJComboBox; // ComboBox para exibir a lista de imagens.
   private final JLabel label; // Label para exibir a imagem selecionada.

   // Nomes dos arquivos de imagem.
   private static final String[] names = 
      {"bug1.gif", "bug2.gif",  "travelbug.gif", "buganim.gif"};
   // Ícones correspondentes às imagens.
   private final Icon[] icons = { 
      new ImageIcon(getClass().getResource(names[0])),
      new ImageIcon(getClass().getResource(names[1])), 
      new ImageIcon(getClass().getResource(names[2])),
      new ImageIcon(getClass().getResource(names[3]))};

   // Construtor para a classe ComboBoxFrame.
   // Configura o layout, adiciona o JComboBox e o JLabel à janela.
   public ComboBoxFrame()
   {
      
      super("Testing JComboBox");  // Configura o título da janela.
      setLayout(new FlowLayout()); // Define o layout da janela como FlowLayout.

      imagesJComboBox = new JComboBox<String>(names); // Cria o JComboBox com os nomes das imagens.
      imagesJComboBox.setMaximumRowCount(3);   // Define o número máximo de itens visíveis no JComboBox.
      imagesJComboBox.addItemListener(  // Adiciona um ItemListener ao JComboBox.
         new ItemListener()
         {
            @Override
            public void itemStateChanged(ItemEvent event)
            { 
               // Verifica se o estado do item mudou para selecionado.
               if (event.getStateChange() == ItemEvent.SELECTED)
               // Atualiza o ícone do JLabel para a imagem selecionada.
                  label.setIcon(icons[
                     imagesJComboBox.getSelectedIndex()]);
            } 
         }
      );
      add(imagesJComboBox); // Adiciona o JComboBox à janela.
      label = new JLabel(icons[0]);  // Cria o JLabel com o primeiro ícone.
      add(label); // Adiciona o JLabel à janela.
   }
}
