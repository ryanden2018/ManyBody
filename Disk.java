class Disk {
  private double centerX;
  private double centerY;
  private double radius;

  Disk() {
    this.centerX = 0.0;
    this.centerY = 0.0;
    this.radius = 1.0;
  }

  Disk(double centerX,double centerY,double radius) {
    this.centerX = centerX;
    this.centerY = centerY;
    this.radius = Math.abs(radius);
  }

  double getRadius() { return radius; }
  double getCenterX() { return centerX; }
  double getCenterY() { return centerY; }

  void setRadius(double radius) { this.radius = Math.abs(radius); }
  void setCenterX(double centerX) { this.centerX = centerX; }
  void setCenterY(double centerY) { this.centerY = centerY; }

  void displaceX(double changeX) { this.centerX += changeX; }
  void displaceY(double changeY) { this.centerY += changeY; }

  static boolean disksOverlap(Disk disk1, Disk disk2, double windowWidth) {
    double dispX = fixDisp(disk1.centerX-disk2.centerX,windowWidth);
    double dispY = fixDisp(disk1.centerY-disk2.centerY,windowWidth);
    double dist = Math.sqrt(Math.pow(dispX,2.0) + Math.pow(dispY,2.0));
    return ((disk1.radius+disk2.radius) > dist);
  }

  static double fixDisp(double disp,double windowWidth) {
    if( disp > windowWidth/2.0 ) {
      return (disp - windowWidth);
    } else if (disp <= -windowWidth/2.0) {
      return (disp + windowWidth);
    }
    return disp;
  }

  // given two overlapping disks with velocities, compute the time
  // required to backtrack so that they just barely touch
  static double backtrackTime(Disk disk1, Disk disk2, double vx1, double vy1, double vx2, double vy2, double windowWidth) {
    if(!Disk.disksOverlap(disk1,disk2,windowWidth)) {
      return 0.0;
    }

    double x1 = disk1.centerX;
    double x2 = disk2.centerX;
    double dispX = fixDisp(x1-x2,windowWidth);
    double y1 = disk1.centerY;
    double y2 = disk2.centerY;
    double dispY = fixDisp(y1-y2,windowWidth);
    double r1 = disk1.radius;
    double r2 = disk2.radius;
  
    // at^2 + bt + c = 0, solve for t and return
    double a = Math.pow(vx1-vx2,2) + Math.pow(vy1-vy2,2);
    double b = 2*(vx2-vx1)*(dispX) + 2*(vy2-vy1)*(dispY);
    double c = Math.pow(dispX,2) + Math.pow(dispY,2) - Math.pow(r1+r2,2);

    return (-b + Math.sqrt(b*b-4*a*c))/(2*a);
  }
}
