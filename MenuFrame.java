import java.awt.*; // Importa classes para criar componentes gráficos e gerenciar eventos.
import java.awt.event.*; // Importa classes para lidar com eventos de interface gráfica.
import javax.swing.*; // Importa classes da biblioteca Swing para criar interfaces gráficas.

// Classe que estende JFrame e demonstra o uso de menus e opções de formatação de texto.
public class MenuFrame extends JFrame 
{
   // Array de cores disponíveis para escolha
   private final Color[] colorValues = 
      {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};   

   // Arrays para armazenar itens de menu
   private final JRadioButtonMenuItem[] colorItems;
   private final JRadioButtonMenuItem[] fonts;
   private final JCheckBoxMenuItem[] styleItems;
   
   // Label para exibir o texto formatado
   private final JLabel displayJLabel;
   
   // Grupos de botões para seleção exclusiva de cor e fonte
   private final ButtonGroup fontButtonGroup;
   private final ButtonGroup colorButtonGroup;
   
   // Estilo de fonte atual
   private int style;

   // Construtor da classe MenuFrame que configura a interface gráfica.
   public MenuFrame()
   {
      // Configura o título da janela
      super("Using JMenus");     

      // Cria o menu "File" e adiciona itens
      JMenu fileMenu = new JMenu("File");
      fileMenu.setMnemonic('F');

      JMenuItem aboutItem = new JMenuItem("About...");
      aboutItem.setMnemonic('A');
      fileMenu.add(aboutItem);
      aboutItem.addActionListener(
         new ActionListener()
         {  
            @Override
            public void actionPerformed(ActionEvent event)
            {
               // Exibe uma caixa de diálogo com informações sobre o programa
               JOptionPane.showMessageDialog(MenuFrame.this,
                  "This is an example\nof using menus",
                  "About", JOptionPane.PLAIN_MESSAGE);
            } 
         } 
      ); 
 
      // Adiciona um item para sair do programa
      JMenuItem exitItem = new JMenuItem("Exit");
      exitItem.setMnemonic('x');
      fileMenu.add(exitItem);
      exitItem.addActionListener(
         new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               // Encerra o programa
               System.exit(0);
            } 
         }
      ); 

      // Cria a barra de menus e adiciona o menu "File"
      JMenuBar bar = new JMenuBar();
      setJMenuBar(bar); 
      bar.add(fileMenu);

      // Cria o menu "Format" com submenus para cor e fonte
      JMenu formatMenu = new JMenu("Format");
      formatMenu.setMnemonic('r');

      // Cria o menu "Color"
      String[] colors = {"Black", "Blue", "Red", "Green"};
      JMenu colorMenu = new JMenu("Color");
      colorMenu.setMnemonic('C');

      colorItems = new JRadioButtonMenuItem[colors.length];
      colorButtonGroup = new ButtonGroup();
      ItemHandler itemHandler = new ItemHandler();    

      // Adiciona itens de cor ao menu e define o grupo de botões
      for (int count = 0; count < colors.length; count++) 
      {
         colorItems[count] = 
            new JRadioButtonMenuItem(colors[count]);
         colorMenu.add(colorItems[count]); 
         colorButtonGroup.add(colorItems[count]); 
         colorItems[count].addActionListener(itemHandler);
      }

      colorItems[0].setSelected(true);

      // Adiciona o menu "Color" ao menu "Format"
      formatMenu.add(colorMenu);
      formatMenu.addSeparator();

      // Cria o menu "Font" e adiciona itens
      String[] fontNames = {"Serif", "Monospaced", "SansSerif", "Arial"};
      JMenu fontMenu = new JMenu("Font");
      fontMenu.setMnemonic('n');

      fonts = new JRadioButtonMenuItem[fontNames.length];
      fontButtonGroup = new ButtonGroup();

      // Adiciona itens de fonte ao menu e define o grupo de botões
      for (int count = 0; count < fonts.length; count++) 
      {
         fonts[count] = new JRadioButtonMenuItem(fontNames[count]);
         fontMenu.add(fonts[count]);
         fontButtonGroup.add(fonts[count]);
         fonts[count].addActionListener(itemHandler);
      } 

      fonts[0].setSelected(true);
      fontMenu.addSeparator();

      // Cria o menu para estilos de fonte (negrito, itálico)
      String[] styleNames = {"Bold", "Italic"};
      styleItems = new JCheckBoxMenuItem[styleNames.length];
      StyleHandler styleHandler = new StyleHandler();

      // Adiciona itens de estilo ao menu
      for (int count = 0; count < styleNames.length; count++) 
      {
         styleItems[count] = 
            new JCheckBoxMenuItem(styleNames[count]);
         fontMenu.add(styleItems[count]);
         styleItems[count].addItemListener(styleHandler);
      }

      // Adiciona o menu "Font" ao menu "Format" e o menu "Format" à barra de menus
      formatMenu.add(fontMenu);
      bar.add(formatMenu); 
     
      // Configura o JLabel para exibir o texto formatado
      displayJLabel = new JLabel("Sample Text", SwingConstants.CENTER);
      displayJLabel.setForeground(colorValues[0]);
      displayJLabel.setFont(new Font("Serif", Font.PLAIN, 72));

      // Configura o fundo do painel e adiciona o JLabel
      getContentPane().setBackground(Color.CYAN);
      add(displayJLabel, BorderLayout.CENTER);
   }

   // Classe interna para lidar com ações de seleção de cor e fonte.
   private class ItemHandler implements ActionListener 
   {
      @Override
      public void actionPerformed(ActionEvent event)
      {
         // Atualiza a cor do texto com base na seleção do menu
         for (int count = 0; count < colorItems.length; count++)
         {
            if (colorItems[count].isSelected()) 
            {
               displayJLabel.setForeground(colorValues[count]);
               break;
            } 
         } 

         // Atualiza a fonte do texto com base na seleção do menu
         for (int count = 0; count < fonts.length; count++)
         {
            if (event.getSource() == fonts[count]) 
            {
               displayJLabel.setFont(
                  new Font(fonts[count].getText(), style, 72));
            }
         }

         repaint();
      } 
   } 

   // Classe interna para lidar com a seleção de estilos de fonte (negrito, itálico).
   private class StyleHandler implements ItemListener 
   {
      @Override
      public void itemStateChanged(ItemEvent e)
      {
         String name = displayJLabel.getFont().getName();
         Font font;

         // Atualiza o estilo da fonte com base nas seleções de estilo
         if (styleItems[0].isSelected() && 
              styleItems[1].isSelected())
            font = new Font(name, Font.BOLD + Font.ITALIC, 72);
         else if (styleItems[0].isSelected())
            font = new Font(name, Font.BOLD, 72);
         else if (styleItems[1].isSelected())
            font = new Font(name, Font.ITALIC, 72);
         else
            font = new Font(name, Font.PLAIN, 72);

         displayJLabel.setFont(font);
         repaint();
      } 
   }
}
