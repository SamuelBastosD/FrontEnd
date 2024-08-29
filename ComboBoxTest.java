import javax.swing.JFrame;

//ComboBoxTest é a classe principal que inicia a aplicação.
//Ela cria uma instância de ComboBoxFrame e configura a janela principal.
public class ComboBoxTest
{
   //Cria uma instância da classe ComboBoxFrame.
   public static void main(String[] args)
   { 
      ComboBoxFrame comboBoxFrame = new ComboBoxFrame();            // Cria uma instância da classe ComboBoxFrame.
      comboBoxFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação de fechamento da janela como saída do programa.
      comboBoxFrame.setSize(350, 150);                              // Define o tamanho da janela
      comboBoxFrame.setVisible(true);                               // Torna a janela visível.
   } 
}
