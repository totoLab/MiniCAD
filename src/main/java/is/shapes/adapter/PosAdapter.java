package is.shapes.adapter;

import is.interpreter.Pos;

import java.awt.geom.Point2D;

public class PosAdapter extends Point2D {

    Pos pos;

    public PosAdapter(Pos pos) {
        this.pos = pos;
    }

    @Override
    public double getX() {
        return pos.getX().getValue();
    }

    @Override
    public double getY() {
        return pos.getY().getValue();
    }

    @Override
    public void setLocation(double v, double v1) {

    }
}
