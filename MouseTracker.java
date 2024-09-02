import javax.swing.*;

/**
 * Classe principal para iniciar a aplicação Mouse Tracker.
 */
public class MouseTracker 
{
   /**
    * Método principal que configura e exibe a janela principal da aplicação.
    * 
    * @param args Argumentos da linha de comando (não utilizados).
    */
   public static void main(String[] args)
   { 
      MouseTrackerFrame mouseTrackerFrame = new MouseTrackerFrame(); 
      mouseTrackerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mouseTrackerFrame.setSize(300, 100); 
      mouseTrackerFrame.setVisible(true); 
   } 
} 