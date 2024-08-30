import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

// A classe ListFrame é uma extensão de JFrame que demonstra o uso de listas
// para alterar a cor de fundo e o tamanho da janela com base na seleção dos itens nas listas.
public class ListFrame extends JFrame 
{
   private final JList<String> colorJList; // Lista de cores a ser exibida na JList
   // Nomes das cores disponíveis
   private static final String[] colorNames = {"Black", "Blue", "Cyan",
      "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
      "Orange", "Pink", "Red", "White", "Yellow"};
   // Cores correspondentes aos nomes
   private static final Color[] colors = {Color.BLACK, Color.BLUE,
      Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, 
      Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, 
      Color.RED, Color.WHITE, Color.YELLOW};

   private final JList<String> sizeJList;  // Lista de tamanhos a ser exibida na JList
   private static final String[] sizeNames = {"small","Medium","Big"}; // Nomes dos tamanhos disponíveis

   // Construtor da classe ListFrame.
   //Configura o layout da janela, cria as listas de cores e tamanhos,e adiciona ouvintes para alterar a cor de fundo e o tamanho da janela com base nas seleções.
   public ListFrame()
   {
      super("List Test");// Define o título da janela
      setLayout(new FlowLayout()); // Define o layout do painel

      // Cria e configura a JList de cores
      colorJList = new JList<String>(colorNames); 
      colorJList.setVisibleRowCount(5); // Define o número de linhas visíveis
      colorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite seleção única
      add(new JScrollPane(colorJList)); // Adiciona a JList ao painel com rolagem

      // Adiciona um ouvinte para alterações na seleção da JList de cores
      sizeJList = new JList<String>(sizeNames);
      sizeJList.setVisibleRowCount(5);
      sizeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      add(new JScrollPane(sizeJList));

      colorJList.addListSelectionListener(
         new ListSelectionListener() 
         {   
            
            @Override
            public void valueChanged(ListSelectionEvent event)
            { 
                // Altera a cor de fundo da janela com base na cor selecionada
               getContentPane().setBackground(
                  colors[colorJList.getSelectedIndex()]);
            } 
         } 
      ); 
      
      // Adiciona um ouvinte para alterações na seleção da JList de tamanhos
      sizeJList.addListSelectionListener(
         new ListSelectionListener() 
         { 
            @Override
            public void valueChanged(ListSelectionEvent event)
            {
               // Altera o tamanho da janela com base no tamanho selecionado
               switch (sizeJList.getSelectedIndex()) {
                  case 0:
                     setSize(350,150); // Tamanho pequeno
                     break;
                  case 1:
                     setSize(550,350); // Tamanho medio
                     break;
                  case 2:
                     setSize(750,550); // Tamanho grande
                     break;

                  default:
                     setSize(350,150); // Valor padrão
                     break;
               }
            } 
         } 
      ); 
   } 
}