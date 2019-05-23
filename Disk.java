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
    double dispX = disk1.centerX-disk2.centerX;
    if( dispX > windowWidth/2.0 ) {
      dispX = dispX - windowWidth;
    } else if (dispX <= -windowWidth/2.0) {
      dispX = dispX + windowWidth;
    }

    double dispY = disk1.centerY-disk2.centerY;
    if( dispY > windowWidth/2.0 ) {
      dispY = dispY - windowWidth;
    } else if (dispY <= -windowWidth/2.0) {
      dispY = dispY + windowWidth;
    } 

    double dist = Math.sqrt(Math.pow(dispX,2.0) + Math.pow(dispY,2.0));
    return ((disk1.radius+disk2.radius) > dist);
  }

  // given two overlapping disks with velocities, compute the time
  // required to backtrack so that they just barely touch
  static double backtrackTime(Disk disk1, Disk disk2, double vx1, double vy1, double vx2, double vy2, double windowWidth) {
    if(!Disk.disksOverlap(disk1,disk2,windowWidth)) {
      return 0.0;
    }

    double x1 = disk1.centerX;
    double x2 = disk2.centerX;
    double y1 = disk1.centerY;
    double y2 = disk2.centerY;
    double r1 = disk1.radius;
    double r2 = disk2.radius;
  
    // at^2 + bt + c = 0, solve for t and return
    double a = Math.pow(vx1-vx2,2) + Math.pow(vy1-vy2,2);
    double b = 2*(vx2-vx1)*((x1-x2)%windowWidth) + 2*(vy2-vy1)*((y1-y2)%windowWidth);
    double c = Math.pow((x1-x2)%windowWidth,2) + Math.pow((y1-y2)%windowWidth,2) - Math.pow(r1+r2,2);

    return (-b + Math.sqrt(b*b-4*a*c))/(2*a);
  }
}
