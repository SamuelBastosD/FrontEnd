import javax.swing.JOptionPane; 

public class Addition // Aqui é criada a classe Addition
{
   public static void main(String[] args) // aqui é criada a classe main
   {
      // Aqui é pedido para o usuário para inserir dois números
      String firstNumber = 
         JOptionPane.showInputDialog("Enter first integer");
      String secondNumber =
         JOptionPane.showInputDialog("Enter second integer");

      // A soma dos dois números que o usuario forneceu
      int number1 = Integer.parseInt(firstNumber); 
      int number2 = Integer.parseInt(secondNumber);

      // soma de dois números inteiros
      int sum = number1 + number2; 

      // Exibe soma dos números exibindo um dialogo dependo do resultado
      JOptionPane.showMessageDialog(null, "The sum is " + sum, 
         "Sum of Two Integers", JOptionPane.PLAIN_MESSAGE);
   } 
} //Aqui e o fim do código


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
