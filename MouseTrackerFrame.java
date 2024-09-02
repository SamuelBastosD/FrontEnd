import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe que define a janela principal e lida com eventos do mouse.
 */
public class MouseTrackerFrame extends JFrame
{
   private final JPanel mousePanel;  // Painel para monitorar eventos do mouse
   private final JLabel statusBar;   // Barra de status para exibir informações sobre os eventos do mouse

   /**
    * Construtor que configura a janela e a interface.
    */
   public MouseTrackerFrame()
   {
      super("Demonstrating Mouse Events");

      mousePanel = new JPanel(); 
      mousePanel.setBackground(Color.WHITE); 
      add(mousePanel, BorderLayout.CENTER); 

      statusBar = new JLabel("Mouse outside JPanel"); 
      add(statusBar, BorderLayout.SOUTH);

      MouseHandler handler = new MouseHandler(); 
      mousePanel.addMouseListener(handler); 
      mousePanel.addMouseMotionListener(handler); 
   } 

   /**
    * Classe interna que lida com eventos do mouse.
    */
   private class MouseHandler implements MouseListener, MouseMotionListener 
   {
      @Override
      public void mouseClicked(MouseEvent event)
      {
         statusBar.setText(String.format("Clicked at [%d, %d]", 
            event.getX(), event.getY()));
      } 

      @Override
      public void mousePressed(MouseEvent event)
      {
         statusBar.setText(String.format("Pressed at [%d, %d]", 
            event.getX(), event.getY()));
      }

      @Override
      public void mouseReleased(MouseEvent event)
      {
         statusBar.setText(String.format("Released at [%d, %d]", 
            event.getX(), event.getY()));
      }

      @Override
      public void mouseEntered(MouseEvent event)
      {
         statusBar.setText(String.format("Mouse entered at [%d, %d]", 
            event.getX(), event.getY()));
         mousePanel.setBackground(Color.GREEN);
      }

      @Override
      public void mouseExited(MouseEvent event)
      {
         statusBar.setText("Mouse outside JPanel");
         mousePanel.setBackground(Color.WHITE);
      }

      @Override
      public void mouseDragged(MouseEvent event)
      {
         statusBar.setText(String.format("Dragged at [%d, %d]", 
            event.getX(), event.getY()));
      } 

      @Override
      public void mouseMoved(MouseEvent event)
      {
         statusBar.setText(String.format("Moved at [%d, %d]", 
            event.getX(), event.getY()));
      } 
   }
}