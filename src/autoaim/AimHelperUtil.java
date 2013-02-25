package autoaim;

public class AimHelperUtil {
//    public final Shape[] goals = new Shape[] {
//        new RectangularPrism(new Vector(new double[] {7.813, -3.092, 0.7874}), new double[] {0.2937129, ,}), // Red low goal
//        new RectangularPrism(new Vector(new double[] {-7.813, 3.092, 0.7874}), new double[] {0.2937129, ,}), // Blue low goal
//        new RectangularPrism(new Vector(new double[] {8.246, -1.842, 2.517775}), new double[] {0.1, 0.6858, 0.2921}), // Red left middle goal
//        new RectangularPrism(new Vector(new double[] {-8.246, 1.842, 2.517775}), new double[] {0.1, 0.6858, 0.2921}), // Blue left middle goal
//        new RectangularPrism(new Vector(new double[] {8.246, 1.842, 2.517775}), new double[] {0.1, 0.6858, 0.2921}), // Red right middle goal
//        new RectangularPrism(new Vector(new double[] {-8.246, -1.842, 2.517775}), new double[] {0.1, 0.6858, 0.2921}), // Blue right middle goal
//        new RectangularPrism(new Vector(new double[] {8.246, 0, 2.797175}), new double[] {0.1, 0.6858, 0.1524}), // Red high goal
//        new RectangularPrism(new Vector(new double[] {-8.246, 0, 2.797175}), new double[] {0.1, 0.6858, 0.1524}), // Blue high goal
//        {3.908, 0, 2.116})), // Red pyramid goal center post
//        {-3.908, 0, 2.116})), // Blue pyramid goal center post
//    };
//    public boolean willHitTarget() {
//        
//    }
    public static interface Shape {
        public boolean isInside(Vector point);
    }
    public static class RectangularPrism implements Shape {
        public Vector centerPoint;
        public double[] radii;
        public RectangularPrism(Vector centerPoint, double[] radii) {
            this.centerPoint = centerPoint;
            this.radii = radii;
        }
        public boolean isInside(Vector point) {
            for(int i = 0; i < radii.length; i++) {
                if(Math.abs(centerPoint.getElement(i) - point.getElement(i)) >= radii[i]) {
                    return false;
                }
            }
            return true;
        }
    }
    public static class Cylinder implements Shape {
        public Shape boundingShape;
        public Vector basePoint;
        public Vector normal;
        public double radius;
        public Cylinder(Vector basePoint, double radius, double zHeight, double floorAngle, double angleOfElevation) {
            double floorLength = zHeight / Math.tan(angleOfElevation);
            double xWidth = floorLength * Math.cos(floorAngle);
            double yWidth = floorLength * Math.sin(floorAngle);
            this.boundingShape = new RectangularPrism(basePoint.add(new Vector(new double[] {xWidth / 2, yWidth / 2, zHeight / 2})), new double[] {xWidth + 2 * radius, yWidth + 2 * radius, zHeight + 2 * radius});
            this.basePoint = basePoint;
            this.normal = new Vector(new double[] {Math.cos(angleOfElevation) * Math.cos(floorAngle), Math.cos(angleOfElevation) * Math.sin(floorAngle), Math.sin(angleOfElevation)});
            this.radius = radius;
        }
        public boolean isInside(Vector point) {
            if(!boundingShape.isInside(point)) {
                return false;
            }
            
            Vector baseToPoint = point.add(basePoint.negate());
            double distanceFromPlane = baseToPoint.dotProduct(this.normal);
            Vector projectedPoint = point.add(this.normal.scale(-distanceFromPlane));
            
            double flatDistance = basePoint.add(projectedPoint.negate()).magnitude();
            return (flatDistance < radius);
        }
    }
}