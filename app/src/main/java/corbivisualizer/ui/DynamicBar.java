package corbivisualizer.ui;

import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

public class DynamicBar extends Group implements Gauge
{
    private double x;
    private double y;
    private double angle;
    private Rectangle rect;
    private Rotate rotate;

    public DynamicBar(double x, double y, double angle, double length, double width)
    {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.rect = new Rectangle(x, y, width, length);
        this.rotate = new Rotate();

        updateRotate();

        this.rect.getTransforms().add(rotate);
        this.getChildren().add(rect);
    }

    public DynamicBar()
    {
        this(0, 0, 0, 0, 0);
    }

    public DynamicBar setAngle(double angle)
    {
        this.angle = angle;
        updateRotate();
        return this;
    }

    public DynamicBar setX(double x)
    {
        this.x = x;
        this.rect.setX(x);
        updateRotate();
        return this;
    }

    public DynamicBar setY(double y)
    {
        this.y = y;
        this.rect.setY(y);
        updateRotate();
        return this;
    }

    public DynamicBar setWidth(double width)
    {
        this.rect.setWidth(width);
        return this;
    }

    private void updateRotate()
    {
        this.rotate.setPivotX(this.x);
        this.rotate.setPivotY(this.y);
        this.rotate.setAngle(this.angle);
    }

    public void updateLength(double length)
    {
        Platform.runLater(() ->
        {
            this.rect.setHeight(length);
        });
    }
}
