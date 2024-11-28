
package frc.robot.subsystems;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.LimeLightConsants;

public class LimeLightSub extends SubsystemBase {
  //limelight values
   //= LimelightHelpers.getTX("limeLight");

   public PIDController LLThetaPIDController = new PIDController(LimeLightConsants.kLimelightThetaControllerP, LimeLightConsants.kLimelightThetaControllerI, LimeLightConsants.kLimelightThetaControllerD);

   public PIDController LLDistancePIDController = new PIDController(LimeLightConsants.kLimelightDistanceControllerP, LimeLightConsants.kLimelightDistanceControllerI, LimeLightConsants.kLimelightDistanceControllerD);

   
   
 
  public double getLimelightDistancePIDOutput(double distanceOffset){
    SmartDashboard.putNumber("Distance Offset", distanceOffset);
   
    LLDistancePIDController.setSetpoint(distanceOffset);
    return LLDistancePIDController.calculate(0);

  }

   public double getLimelightThetaPIDOutput(double ty){
 
   SmartDashboard.putNumber("Heading Error",ty);
     
   LLThetaPIDController.setTolerance(LimeLightConsants.kLimelightAngleDeadband);
    LLThetaPIDController.setSetpoint(ty);
    return LLThetaPIDController.calculate(LimeLightConsants.kTyOffset);
 

   }
   public double calculateDistanceFromTarget(){


     // what is the offset angle of the Limelight to the target
     double targetOffsetAngle_Vertical = LimelightHelpers.getTY("limelight");


      // get in degrees and converts into radians
     double angleToGoalDegrees = LimeLightConsants.limelightMountAngleDegrees + targetOffsetAngle_Vertical;
     double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0); // a2
    //d = (h2-h1) / tan(a1+a2)
    // conduct distance formulas using the values from constants and the recent ty value

     double distanceFromLimelightToGoalInches = (LimeLightConsants.goalHeightInches - LimeLightConsants.limelightLensHeightInches) / Math.tan(angleToGoalRadians);

     SmartDashboard.putNumber("Distance", distanceFromLimelightToGoalInches);

     

    
     return distanceFromLimelightToGoalInches;

   }


   @Override
   public void periodic() {
     calculateDistanceFromTarget();

     if(calculateDistanceFromTarget() < LimeLightConsants.kMaxShoot && calculateDistanceFromTarget() > LimeLightConsants.kMinShoot){
      SmartDashboard.putBoolean("shoot", true);
     }else{
      SmartDashboard.putBoolean("shoot",false);
     }
     
   }
    
   
    
}