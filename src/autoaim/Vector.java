package autoaim;

public class Vector {
    double[] values;
    public Vector(double[] values) {
        this.values = values;
    }
    public double getElement(int i) {
        return values[i];
    }
    public Vector add(Vector v) {
        if(values.length != v.values.length) {
            throw new RuntimeException("Vector sizes do not match");
        }
        double[] result = new double[values.length];
        for(int i = 0; i < values.length; i++) {
            result[i] = values[i] + v.values[i];
        }
        return new Vector(result);
    }
    public Vector negate() {
        double[] result = new double[values.length];
        for(int i = 0; i < values.length; i++) {
            result[i] = -values[i];
        }
        return new Vector(result);
    }
    public double dotProduct(Vector v) {
        if(values.length != v.values.length) {
            throw new RuntimeException("Vector sizes do not match");
        }
        double result = 0.0;
        for(int i = 0; i < values.length; i++) {
            result += values[i] * v.values[i];
        }
        return result;
    }
    public double magnitude() {
        double result = 0.0;
        for(int i = 0; i < values.length; i++) {
            result += values[i] * values[i];
        }
        return Math.sqrt(result);
    }
    public Vector crossProduct(Vector v) {
        if(values.length != 3 || v.values.length != 3) {
            throw new RuntimeException("Cross product is only applicable in three dimensions");
        }
        double[] result = new double[values.length];
        result[0] = values[1] * v.values[2] - values[2] * v.values[1];
        result[1] = values[2] * v.values[0] - values[0] * v.values[2];
        result[2] = values[0] * v.values[1] - values[1] * v.values[0];
        return new Vector(result);
    }
    public Vector scale(double d) {
        double[] result = new double[values.length];
        for(int i = 0; i < values.length; i++) {
            result[i] = values[i] * d;
        }
        return new Vector(result);
    }
}