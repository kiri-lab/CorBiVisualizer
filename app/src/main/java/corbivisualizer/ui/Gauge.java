package corbivisualizer.ui;

public interface Gauge
{
    public Gauge setAngle(double angle);

    public Gauge setX(double x);

    public Gauge setY(double y);

    public void updateLength(double length);
}
