import javax.swing.*; // Importa a biblioteca Swing para criar interfaces gráficas

// Classe principal do programa que demonstra o uso de um menu em uma interface gráfica.
public class MenuTest
{
   // Método principal que é o ponto de entrada do programa.
   // @param args Argumentos da linha de comando (não utilizados neste exemplo).
   public static void main(String[] args)
   { 
      // Cria uma instância da classe MenuFrame, que deve estender JFrame e definir o menu.
      MenuFrame menuFrame = new MenuFrame(); 
      
      // Define a operação padrão de fechamento da janela para sair do programa quando a janela for fechada.
      menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Define o tamanho da janela para 500 pixels de largura e 200 pixels de altura.
      menuFrame.setSize(500, 200); 
      
      // Torna a janela visível.
      menuFrame.setVisible(true);
   } 
}
