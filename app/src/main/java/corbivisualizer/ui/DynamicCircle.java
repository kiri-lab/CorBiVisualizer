package corbivisualizer.ui;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Rotate;

public class DynamicCircle extends Group implements Gauge
{
    private double x;
    private double y;
    private double angle;
    private Arc arc;
    private Rotate rotate;
    private double minValue = 0;
    private double maxValue = 65535;

    public DynamicCircle(double x, double y, double angle, double radiusX, double radiusY, double length,
            double minValue, double maxValue)
    {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.arc = new Arc(x, y, radiusX, radiusY, 0, length);
        this.arc.setType(ArcType.ROUND);
        this.rotate = new Rotate();
        this.minValue = minValue;
        this.maxValue = maxValue;

        updateRotate();

        this.arc.getTransforms().add(rotate);
        this.getChildren().add(arc);
    }

    public DynamicCircle(double x, double y, double angle, double radiusX, double radiusY, double length)
    {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.arc = new Arc(x, y, radiusX, radiusY, 0, length);
        this.arc.setType(ArcType.ROUND);
        this.rotate = new Rotate();

        updateRotate();

        this.arc.getTransforms().add(rotate);
        this.getChildren().add(arc);
    }

    public DynamicCircle()
    {
        this(0, 0, 0, 0, 0, 0);
    }

    public DynamicCircle setAngle(double angle)
    {
        this.angle = angle;
        updateRotate();
        return this;
    }

    public DynamicCircle setX(double x)
    {
        this.x = x;
        this.arc.setCenterX(x);
        updateRotate();
        return this;
    }

    public DynamicCircle setY(double y)
    {
        this.y = y;
        this.arc.setCenterY(y);
        updateRotate();
        return this;
    }

    public DynamicCircle setRadiusX(double radiusX)
    {
        this.arc.setRadiusX(radiusX);
        updateRotate();
        return this;
    }

    public DynamicCircle setRadiusY(double radiusY)
    {
        this.arc.setRadiusY(radiusY);
        updateRotate();
        return this;
    }

    private void updateRotate()
    {
        this.rotate.setPivotX(this.x);
        this.rotate.setPivotY(this.y);
        this.rotate.setAngle(this.angle);
    }

    public void updateLength(double value)
    {
        double length = (value - this.minValue) / (this.maxValue - this.minValue) * 360;
        Platform.runLater(() ->
        {
            this.arc.setLength(length);
        });
    }
}
