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

}
