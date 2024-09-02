import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A classe MultipleSelectionFrame estende JFrame e cria uma interface gráfica com duas listas (JList)
 * e dois botões (JButton). O usuário pode selecionar itens de uma lista e copiá-los para outra lista
 * através dos botões fornecidos.
 */
public class MultipleSelectionFrame extends JFrame 
{
   // Lista de cores exibida para seleção
   private final JList<String> colorJList; 
   
   // Lista onde os itens selecionados na colorJList serão copiados
   private final JList<String> copyJList;

   // Lista onde os itens selecionados em copyJList serão copiados
   private final JList<String> copyJList2;

   // Botão para copiar itens da colorJList para copyJList
   private JButton copyJButton;

   // Botão para copiar itens de copyJList para copyJList2
   private JButton copyJButton2;
    
   // Array de nomes de cores para a colorJList
   private static final String[] colorNames = {"Black", "Blue", "Cyan",
      "Dark Gray", "Gray", "Green", "Light Gray", "Magenta", "Orange", 
      "Pink", "Red", "White", "Yellow"};

   /**
    * Construtor da classe MultipleSelectionFrame. Configura a interface gráfica da janela.
    */
   public MultipleSelectionFrame()
   {
      super("Multiple Selection Lists");
      setLayout(new FlowLayout());

      // Inicializa e configura colorJList
      colorJList = new JList<String>(colorNames); 
      colorJList.setVisibleRowCount(5); 
      colorJList.setSelectionMode(
         ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      add(new JScrollPane(colorJList)); 

      // Inicializa e configura copyJButton
      copyJButton = new JButton("Copy >>>"); 
      copyJButton.addActionListener(
         new ActionListener() 
         {  
            @Override
            public void actionPerformed(ActionEvent event)
            {
               // Copia itens selecionados de colorJList para copyJList
               copyJList.setListData(
                  colorJList.getSelectedValuesList().toArray(
                     new String[0]));
            }
         } 
      ); 
      add(copyJButton); 

      // Inicializa e configura copyJList
      copyJList = new JList<String>(); 
      copyJList.setVisibleRowCount(5); 
      copyJList.setFixedCellWidth(100); 
      copyJList.setFixedCellHeight(15); 
      copyJList.setSelectionMode(
         ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      add(new JScrollPane(copyJList)); 

      // Inicializa e configura copyJButton2
      copyJButton2 = new JButton("Copy >>>"); 
      copyJButton2.addActionListener(
         new ActionListener() 
         {  
            @Override
            public void actionPerformed(ActionEvent event)
            {
               // Copia itens selecionados de copyJList para copyJList2
               copyJList2.setListData(
                  copyJList.getSelectedValuesList().toArray(
                     new String[0]));
            }
         } 
      ); 
      add(copyJButton2); 

      // Inicializa e configura copyJList2
      copyJList2 = new JList<String>(); 
      copyJList2.setVisibleRowCount(5); 
      copyJList2.setFixedCellWidth(100); 
      copyJList2.setFixedCellHeight(15); 
      copyJList2.setSelectionMode(
         ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      add(new JScrollPane(copyJList2));
   } 
} 