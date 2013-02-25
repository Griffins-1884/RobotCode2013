package autoaim;

import com.sun.squawk.util.MathUtils;

public class FrisbeeSimulation {
    //****************  Constants  ****************
    // Gravity
    public static final double GRAVITY = -9.794; // m/s^2
    // Mass of the frisbee
    public static final double FRISBEE_MASS = 0.175; // kg
    public static final double INVERSE_FRISBEE_MASS = 1.0/FRISBEE_MASS; // kg^-1
    // Rotational inertia about nonsymmetric axis
    public static final double NONSYMMETRIC_ROTATIONAL_INERTIA = .00122; // kg m^2
    // Rotational inertia about symmetric axis
    public static final double SYMMETRIC_ROTATIONAL_INERTIA = .00235; // kg m^2
    // Density of air
    public static final double DENSITY_OF_AIR = 1.23; // kg/m^3
    // Diameter of a frisbee
    public static final double DIAMETER_OF_FRISBEE = 0.269; // m^2
    public static final double RADIUS_OF_FRISBEE = DIAMETER_OF_FRISBEE / 2; // m^2
    // Area of a frisbee
    public static final double AREA_OF_FRISBEE = 0.057; // m^2
    // Lift coefficient at alpha = 0
    public static final double CL0 = 0.2;
    // Lift coefficient dependent on alpha
    public static final double CLA = 2.96;
    // Drag coefficient at alpha = 0
    public static final double CD0 = 0.08;
    // Drag coefficient dependent on alpha
    public static final double CDA = 2.72;
    // Pitch moment coefficient at alpha = 0
    public static final double CM0 = -0.02;
    // Pitch moment coefficient dependent on alpha
    public static final double CMA = 0.13;
    // Pitch moment damping coefficient
    public static final double CMQ = -0.014;
    // Roll moment damping coefficient
    public static final double CRP = -0.013;
    // Roll moment damping coefficient due to spin
    public static final double CRR = -0.003;
    // Spin down moment coefficient
    public static final double CNR = -0.000034;
    // The angle at which the frisbee produces the least lift
    public static final double LOWEST_LIFT_ANGLE = -4 * Math.PI / 180.0;
    
    public static interface FrisbeeSimulationClient {
        public boolean registerDisplacement(Vector displacement); // Return true if we should keep simulating, false if we should stop
    }
    public static void simulateFrisbee(FrisbeeSimulationClient client, Vector displacement, Vector velocity, double angleOfAttackFromHorizontal, double angleOfRollAboutVelocity,                                                              double deltaTime) {
                                // The client asking for a simulation,initial displacement,initial velocity,initial angle of attack from horiz., initial angle of roll about velocity (positive is counterclockwise from robot's perspective), change in time between steps where lower increases accuracy
        Vector acceleration = null;
        Vector force = null;
            Vector forceGravity = new Vector(new double[] {0, 0, GRAVITY * FRISBEE_MASS});
            Vector forceLift = null;
            Vector forceDrag = null;
        double angleOfAttackFromHorizontalVelocity = 0.0;
        double angleOfAttackFromHorizontalAcceleration = 0.0;
        double angleOfRollAboutVelocityVelocity = 0.0;
        double angleOfRollAboutVelocityAcceleration = 0.0;
        while(client.registerDisplacement(displacement)) {
            //****************  Deal with translational motion ****************
            double angleOfAttackFromVelocity = angleOfAttackFromHorizontal - MathUtils.atan(velocity.getElement(2) / Math.sqrt(velocity.getElement(0)*velocity.getElement(0)+velocity.getElement(1)*velocity.getElement(1)));
            double coefficientOfLift = CL0 + CLA * angleOfAttackFromVelocity;
            double coefficientOfDrag = CD0 + CDA * (angleOfAttackFromVelocity - LOWEST_LIFT_ANGLE) * (angleOfAttackFromVelocity - LOWEST_LIFT_ANGLE);
            
            double velocityMagnitude = velocity.magnitude();
            double forceLiftMagnitude = 0.5 * DENSITY_OF_AIR * velocityMagnitude * velocityMagnitude * AREA_OF_FRISBEE * coefficientOfLift;
            double forceLiftFrisbeeXComponent = -forceLiftMagnitude * Math.sin(angleOfRollAboutVelocity);
            double forceLiftFrisbeeYComponent = -forceLiftMagnitude * Math.cos(angleOfRollAboutVelocity) * Math.sin(angleOfAttackFromHorizontal);
            double forceLiftFrisbeeZComponent = forceLiftMagnitude * Math.cos(angleOfRollAboutVelocity) * Math.cos(angleOfAttackFromHorizontal);
            double horizontalAngleOfVelocity = MathUtils.atan2(velocity.getElement(1), velocity.getElement(0));
            forceLift = new Vector(new double[] {forceLiftFrisbeeXComponent * Math.sin(horizontalAngleOfVelocity) + forceLiftFrisbeeYComponent * Math.cos(horizontalAngleOfVelocity),
                                                 -forceLiftFrisbeeXComponent * Math.cos(horizontalAngleOfVelocity) + forceLiftFrisbeeYComponent * Math.sin(horizontalAngleOfVelocity),
                                                 forceLiftFrisbeeZComponent});
            
            double forceDragMagnitude = 0.5 * DENSITY_OF_AIR * velocityMagnitude * velocityMagnitude * AREA_OF_FRISBEE * coefficientOfDrag;
            double forceDragFrisbeeXComponent = 0.0;
            double forceDragFrisbeeYComponent = -forceDragMagnitude * Math.cos(angleOfAttackFromHorizontal);
            double forceDragFrisbeeZComponent = -forceDragMagnitude * Math.sin(angleOfAttackFromHorizontal);
            forceDrag = new Vector(new double[] {forceDragFrisbeeXComponent * Math.sin(horizontalAngleOfVelocity) + forceDragFrisbeeYComponent * Math.cos(horizontalAngleOfVelocity),
                                                 -forceDragFrisbeeXComponent * Math.cos(horizontalAngleOfVelocity) + forceDragFrisbeeYComponent * Math.sin(horizontalAngleOfVelocity),
                                                 forceDragFrisbeeZComponent});
            
            force = forceLift.add(forceDrag).add(forceGravity);
            acceleration = force.scale(INVERSE_FRISBEE_MASS);
            
            displacement = displacement.add(velocity.scale(deltaTime)).add(acceleration.scale(0.5 * deltaTime * deltaTime));
            velocity = velocity.add(acceleration.scale(deltaTime));
            
            
            //****************  Deal with rotational motion ****************
            angleOfAttackFromHorizontalAcceleration = (CM0 + CMA * angleOfAttackFromVelocity + CMQ * angleOfAttackFromHorizontalVelocity) * 0.5 * DENSITY_OF_AIR * velocityMagnitude * velocityMagnitude * AREA_OF_FRISBEE * DIAMETER_OF_FRISBEE;// / NONSYMMETRIC_ROTATIONAL_INERTIA;
            angleOfAttackFromHorizontal = angleOfAttackFromHorizontal + angleOfAttackFromHorizontalVelocity * deltaTime + 0.5 * angleOfAttackFromHorizontalAcceleration * deltaTime * deltaTime;
            angleOfAttackFromHorizontalVelocity = angleOfAttackFromHorizontalVelocity + angleOfAttackFromHorizontalAcceleration * deltaTime;
            
            angleOfRollAboutVelocityAcceleration = (CRR * RADIUS_OF_FRISBEE + CRP * angleOfRollAboutVelocityVelocity) * 0.5 * DENSITY_OF_AIR * velocityMagnitude * velocityMagnitude * AREA_OF_FRISBEE * DIAMETER_OF_FRISBEE;// / NONSYMMETRIC_ROTATIONAL_INERTIA;
            angleOfRollAboutVelocity = angleOfRollAboutVelocity + angleOfRollAboutVelocityVelocity * deltaTime + 0.5 * angleOfRollAboutVelocityAcceleration * deltaTime * deltaTime;
            angleOfRollAboutVelocityVelocity = angleOfRollAboutVelocityVelocity + angleOfRollAboutVelocityAcceleration * deltaTime;
        }
    }
}