package autoaim;

import com.sun.squawk.util.MathUtils;

public class FrisbeeSimulation {
    //****************  Constants  ****************
    // Gravity
    public static final double GRAVITY = -9.81; // m/s^2
    // Mass of the frisbee
    public static final double FRISBEE_MASS = 0.175; // kg
    // Rotational inertia about nonsymmetric axis
    public static final double NONSYMMETRIC_ROTATIONAL_INERTIA = .00095016; // kg m^2
    // Rotational inertia about symmetric axis
    public static final double SYMMETRIC_ROTATIONAL_INERTIA = .001868981; // kg m^2
    // Density of air
    public static final double DENSITY_OF_AIR = 1.23; // kg/m^3
    // Area of a frisbee
    public static final double AREA_OF_FRISBEE = 0.0568; // m^2
    // Lift coefficient at alpha = 0
    public static final double CL0 = 0.1;
    // Lift coefficient dependent on alpha
    public static final double CLA = 1.4;
    // Drag coefficient at alpha = 0
    public static final double CD0 = 0.08;
    // Drag coefficient dependent on alpha
    public static final double CDA = 2.72;
    
    public static interface FrisbeeSimulationClient {
        public boolean registerDisplacement(Vector displacement); // Return true if we should keep simulating, false if we should stop
    }
    public static void simulateFrisbee(FrisbeeSimulationClient client, Vector displacement, Vector velocity, double angleOfAttackFromHorizontal, double angleOfRollFromHorizontal, double deltaTime) { // All angles are in radians, positive angle of roll is when the frisbee is banking left
        Vector acceleration = null;
        Vector force = null;
            Vector forceGravity = new Vector(new double[] {0, 0, GRAVITY * FRISBEE_MASS});
            Vector forceLift = null;
            Vector forceDrag = null;
        while(client.registerDisplacement(displacement)) {
            double angleOfAttackFromVelocity = angleOfAttackFromHorizontal - MathUtils.atan(velocity.getElement(2) / Math.sqrt(velocity.getElement(0)*velocity.getElement(0)+velocity.getElement(1)*velocity.getElement(1)));
            double coefficientOfLift = CL0 + CLA * angleOfAttackFromVelocity;
            double coefficientOfDrag = CD0 + CDA * angleOfAttackFromVelocity * angleOfAttackFromVelocity;
            
            double velocityMagnitude = velocity.magnitude();
            double forceLiftMagnitude = 0.5 * DENSITY_OF_AIR * velocityMagnitude * velocityMagnitude * AREA_OF_FRISBEE * coefficientOfLift;
            double forceLiftFrisbeeXComponent = 
            forceLift = new Vector(new double[] {});
        }
    }
}
