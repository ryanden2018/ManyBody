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

  static boolean disksOverlap(Disk disk1, Disk disk2) {
    double dist = Math.sqrt(Math.pow(disk1.centerX-disk2.centerX,2.0) + Math.pow(disk1.centerY-disk2.centerY,2.0));
    return ((disk1.radius+disk2.radius) > dist);
  }

  /* adjustDisks(Disk disk1, Disk disk2, double vx1, double vy1, double vx2, double vy2)
   * The input is two overlapping disks with velocities (vx1,vy1) and (vx2,vy2) respectively.
   * The incoming disks are mutated in-place to cause them to meet tangentially; if they did
   * not initially overlap, neither one is changed and 0.0 is returned.
   * After adjustment, the time of backtracking required to cause a tangential meet is returned.
   */
  static double adjustDisks(Disk disk1, Disk disk2, double vx1, double vy1, double vx2, double vy2) {
    if(!Disk.disksOverlap(disk1,disk2)) {
      return 0.0;
    }

    // TODO: finish
    return 0.0; ////////
  } 
}