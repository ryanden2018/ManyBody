class ManyBodyData {
  Disk[] disks;

  double[] vx; // x velocity component
  double[] vy; // y velocity component
  double dt; // time step
  int width;

  ManyBodyData(int width, double diskRadius, int N, double dt) {
    disks = new Disk[N];
    vx = new double[N];
    vy = new double[N];
    this.width = width;
    this.dt = dt;

    for(int i=0; i < disks.length; i++) {
      double theta = 2*Math.PI*Math.random();
      vx[i] = Math.cos(theta);
      vy[i] = Math.sin(theta);

      while(true) {
        double centerX = width*Math.random();
        double centerY = width*Math.random();
        disks[i] = new Disk(centerX,centerY,diskRadius);
        if((!existsOverlap()) && everyoneInBounds()) {
          break;
        }
      }
    }
  }

  boolean everyoneInBounds() {
    for(int i = 0; i < disks.length; i++) {
      if( (disks[i]!=null) &&
        ( (disks[i].getCenterX() < disks[i].getRadius()) 
           || (disks[i].getCenterX() > width-disks[i].getRadius())
           || (disks[i].getCenterY() < disks[i].getRadius())
           || (disks[i].getCenterY() > width-disks[i].getRadius()) ) ) {
        return false;
      }
    }
    return true;
  }

  boolean existsOverlap() {
    for(int i = 0; i < disks.length; i++) {
      for(int j = 0; j < disks.length; j++) {
        if( (i!=j) && (disks[i]!=null) && (disks[j]!=null)
            && Disk.disksOverlap(disks[i],disks[j])) {
          return true;
        }
      }
    }
    return false;
  }

  void update() {
    for(int i = 0; i < disks.length; i++) {
      disks[i].displaceX(vx[i]*dt);
      disks[i].displaceY(vy[i]*dt);

      if( (disks[i].getCenterX() < disks[i].getRadius()) && (vx[i]<0) ) {
        vx[i] = -vx[i];
      }

      if((disks[i].getCenterX() > width-disks[i].getRadius()) && (vx[i]>0) ) {
        vx[i] = -vx[i];
      }

      if((disks[i].getCenterY() < disks[i].getRadius()) && (vy[i]<0) ) {
        vy[i] = -vy[i];
      }

      if((disks[i].getCenterY() > width-disks[i].getRadius()) && (vy[i]>0) ) {
        vy[i] = -vy[i];
      }
    }

    for(int i = 0; i < disks.length; i++) {
      for(int j = 0; j < disks.length; j++) {
        if( (i<j) && Disk.disksOverlap(disks[i],disks[j]) ) {
          double nx = disks[i].getCenterX() - disks[j].getCenterX();
          double ny = disks[i].getCenterY() - disks[j].getCenterY();
          double nabs = Math.sqrt(Math.pow(nx,2)+Math.pow(ny,2));
          nx = nx / nabs;
          ny = ny / nabs;
          double mult = (vx[i]-vx[j])*nx + (vy[i]-vy[j])*ny;
          if(mult < 0) {
            vx[i] = vx[i] - nx*mult;
            vy[i] = vy[i] - ny*mult;
            vx[j] = vx[j] + nx*mult;
            vy[j] = vy[j] + ny*mult;
          }
        }
      }
    }
  }
}
