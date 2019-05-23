import javax.swing.*;
import java.awt.*;
import java.util.*;

class ManyBodyGraphics extends JComponent {
  int WIDTH = 650;
  int HEIGHT = WIDTH;
  int DISK_RADIUS = 20;
  int N = 40;
  double DT = 5.0;
  ManyBodyData mbd;

  ManyBodyGraphics() {
    setPreferredSize(new Dimension(WIDTH,HEIGHT));
    mbd = new ManyBodyData(WIDTH,DISK_RADIUS,N,DT);
  }

  void drawDisk(Graphics g,Disk disk,double horizOffset,double vertOffset) {
    g.fillOval((int) (disk.getCenterX()-disk.getRadius()+horizOffset), 
        (int) (disk.getCenterY()-disk.getRadius()+vertOffset),
        (int) (2.0*disk.getRadius()), (int) (2.0*disk.getRadius()) );
  }

  @Override
  public void paintComponent(Graphics g) {
    mbd.update();

    super.paintComponent(g);
  
    for(int i = 0; i < mbd.disks.length; i++) {
      drawDisk(g,mbd.disks[i],0.0,0.0);
      drawDisk(g,mbd.disks[i],WIDTH,0.0);
      drawDisk(g,mbd.disks[i],-WIDTH,0.0);
      drawDisk(g,mbd.disks[i],0.0,HEIGHT);
      drawDisk(g,mbd.disks[i],0.0,-HEIGHT);
      drawDisk(g,mbd.disks[i],WIDTH,HEIGHT);
      drawDisk(g,mbd.disks[i],WIDTH,-HEIGHT);
      drawDisk(g,mbd.disks[i],-WIDTH,HEIGHT);
      drawDisk(g,mbd.disks[i],-WIDTH,-HEIGHT);
    }
  }
}
