import javax.swing.*;

/**
 * A classe MultipleSelectionTest contém o método principal que inicializa a aplicação.
 * Ela cria uma instância de MultipleSelectionFrame e define configurações básicas da janela.
 */
public class MultipleSelectionTest
{
   /**
    * O ponto de entrada para a aplicação. Cria uma instância de MultipleSelectionFrame,
    * define o comportamento de fechamento da janela e torna a janela visível.
    * 
    * @param args argumentos da linha de comando (não utilizados)
    */
   public static void main(String[] args)
   { 
      // Cria uma instância da janela MultipleSelectionFrame
      MultipleSelectionFrame multipleSelectionFrame =
         new MultipleSelectionFrame(); 
      
      // Define o comportamento da aplicação ao fechar a janela
      multipleSelectionFrame.setDefaultCloseOperation(
         JFrame.EXIT_ON_CLOSE);
      
      // Define o tamanho da janela
      multipleSelectionFrame.setSize(550, 150); 
      
      // Torna a janela visível
      multipleSelectionFrame.setVisible(true); 
   } 
} 