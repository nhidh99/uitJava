package Shape2D;

public abstract class Shape {
	public abstract void inputInfo();
	public abstract String getInfo();
	public abstract double getPerimeter();
	public abstract double getSurface();
	public abstract boolean isContainPoint(Point2D point);
}