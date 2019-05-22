class ManyBodyData {
  Disk[] disks;

  double[] vx; // x velocity component
  double[] vy; // y velocity component
  double dt; // time step
  int width;

  ManyBodyData(int width, double diskRadius, int N, double dt) {
    // double bigDiskRadius = width * multiplier * 0.5;
    // puck = new Disk(width*0.5,width*0.5,smallDiskRadius);
    // bigDiskNW = new Disk(0,0,bigDiskRadius);
    // bigDiskNE = new Disk(width,0,bigDiskRadius);
    // bigDiskSW = new Disk(0,width,bigDiskRadius);
    // bigDiskSE = new Disk(width,width,bigDiskRadius);

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

      if( disks[i].getCenterX() < disks[i].getRadius() ) {
        vx[i] = -vx[i];
      }

      if(disks[i].getCenterX() > width-disks[i].getRadius() ) {
        vx[i] = -vx[i];
      }

      if(disks[i].getCenterY() < disks[i].getRadius() ) {
        vy[i] = -vy[i];
      }

      if(disks[i].getCenterY() > width-disks[i].getRadius() ) {
        vy[i] = -vy[i];
      }
    }

    // Disk bigDisk = new Disk();
    // if(Disk.disksOverlap(bigDiskNW,puck)) {
    //   bigDisk = bigDiskNW;
    // } else if (Disk.disksOverlap(bigDiskNE,puck)) {
    //   bigDisk = bigDiskNE;
    // } else if (Disk.disksOverlap(bigDiskSW,puck)) {
    //   bigDisk = bigDiskSW;
    // } else if (Disk.disksOverlap(bigDiskSE,puck)) {
    //   bigDisk = bigDiskSE;
    // } else {
    //   return;
    // }
    
    // puck = bigDisk.adjustedDisk(puck,vx,vy);

    // double nx = bigDisk.getCenterX() - puck.getCenterX();
    // double ny = bigDisk.getCenterY() - puck.getCenterY();
    // double nabs = Math.sqrt(Math.pow(nx,2)+Math.pow(ny,2));
    // nx = nx / nabs;
    // ny = ny / nabs;
    // double mult = vx*nx + vy*ny;
    // vx = vx - 2*nx*mult;
    // vy = vy - 2*ny*mult;
    // double vabs = Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2)); 
    // vx = vx / vabs;
    // vy = vy / vabs;
  }
}