import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ManyBody implements ActionListener {
  JFrame jfrm;
  ManyBodyGraphics mbg;

  ManyBody() {
    jfrm = new JFrame("ManyBody");
    mbg = new ManyBodyGraphics();

    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.setBackground(Color.white);
    jfrm.add(mbg,BorderLayout.CENTER);
    jfrm.pack();
    jfrm.setResizable(false);
    jfrm.setVisible(true);

    int delay = 100;
    Timer timer = new Timer(delay,this);
    while(true) {
      timer.start();
    }
  }

  public void actionPerformed(ActionEvent e) {
    mbg.repaint();
  }

  public static void main(String args[]) {
    new ManyBody();
  }
}