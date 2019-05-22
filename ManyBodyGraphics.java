import javax.swing.*;
import java.awt.*;
import java.util.*;

class ManyBodyGraphics extends JComponent {
  int WIDTH = 650;
  int HEIGHT = WIDTH;
  int DISK_RADIUS = 20;
  int N = 40;
  double DT = 0.2;
  ManyBodyData mbd;

  ManyBodyGraphics() {
    setPreferredSize(new Dimension(WIDTH,HEIGHT));
    mbd = new ManyBodyData(WIDTH,DISK_RADIUS,N,DT);
  }

  @Override
  public void paintComponent(Graphics g) {
    mbd.update();

    super.paintComponent(g);
  
    for(int i = 0; i < mbd.disks.length; i++) {
      g.fillOval((int) (mbd.disks[i].getCenterX()-mbd.disks[i].getRadius()), 
        (int) (mbd.disks[i].getCenterY()-mbd.disks[i].getRadius()),
        (int) (2.0*mbd.disks[i].getRadius()), (int) (2.0*mbd.disks[i].getRadius()) );
    }
  }
}
