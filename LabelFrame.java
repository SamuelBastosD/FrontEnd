
import java.awt.FlowLayout; 
// import javax.swing.JFrame; // provides basic window features
// import javax.swing.JLabel; // displays text and images
// import javax.swing.SwingConstants; // common constants used with swing
//import javax.swing.Icon; //interface used to manipulate images
//import javax.swing.ImageIcon; // loads images
import javax.swing.*; // carrega todos os componentes do módulo swing da biblioteca javax


public class LabelFrame extends JFrame 
{
   // variavél privada do tipo jlabel dentro da classe LabelFrame
   private final JLabel label1; // JLabel com apenas texto
   private final JLabel label2; // JLabel com texto e ícone
   private final JLabel label3; // JLabel com texto e ícone adicionados

   //qualquer método dentro de uma classe com o mesmo nome é um construtor
   public LabelFrame()
   {
      super("Testing JLabel"); // super é um método herdado do JFrame que define o título
      setLayout(new FlowLayout()); // define o layout

      // construtor com um argumento string
      label1 = new JLabel("Label with text");
      label1.setToolTipText("This is label1");
      add(label1); // adiciona label1 ao JFrame

      //
      Icon bug = new ImageIcon(getClass().getResource("bug1.png"));
      label2 = new JLabel("Label with text and icon", bug, 
         SwingConstants.LEFT);
      label2.setToolTipText("This is label2");
      add(label2); // add label2 to JFrame 

      //construtor sem argumentos
      label3 = new JLabel(); 
      label3.setText("Label with icon and text at bottom");
      label3.setIcon(bug); //add icon to JLabel
      label3.setHorizontalTextPosition(SwingConstants.CENTER);
      label3.setVerticalTextPosition(SwingConstants.BOTTOM);
      label3.setToolTipText("This is label3");
      add(label3); 
   } 
} 


/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
