import javax.swing.*;

// Ponto de entrada para a aplicação ListTest.
// Cria uma instância de ListFrame e a exibe.
public class ListTest 
{
   public static void main(String[] args)
   { 
      ListFrame listFrame = new ListFrame();                     // Cria uma instância de ListFrame
      listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Define a operação de fechamento padrão para encerrar a aplicação quando a janela for fechada
      listFrame.setSize(350, 150);                               // Define o tamanho da janela
      listFrame.setVisible(true);                                // Torna a janela visível
   } 
} 