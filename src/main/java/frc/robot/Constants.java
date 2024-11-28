// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class ModuleConstants{
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4);    
    public static final double kDriveMotorGearRatio = 6.75 / 1;
    public static final double kTurningMotorGearRatio = 12.8/1.0;
    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters; 
    // I want to divide  10 ^^
    public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
    public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
    public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
    public static final double kTurning = 0.4; 
  }
   public static final class DriveConstants {

        public static final double kTrackWidth = Units.inchesToMeters(19.75);
        // Distance between right and left wheels
        public static final double kWheelBase = Units.inchesToMeters(26.5);
        // Distance between front and back wheels
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

        public static final int kFrontLeftDriveMotorPort = 5;
        public static final int kBackLeftDriveMotorPort = 8;
        public static final int kFrontRightDriveMotorPort = 7;
        public static final int kBackRightDriveMotorPort = 3;

        public static final int kFrontLeftTurningMotorPort = 10;
        public static final int kBackLeftTurningMotorPort = 2;
        public static final int kFrontRightTurningMotorPort = 6;
        public static final int kBackRightTurningMotorPort = 4;



        public static final boolean kFrontLeftTurningEncoderReversed = true;
        public static final boolean kBackLeftTurningEncoderReversed = true;
        public static final boolean kFrontRightTurningEncoderReversed = true;
        public static final boolean kBackRightTurningEncoderReversed = true;

        public static final boolean kFrontLeftDriveEncoderReversed = false;
        public static final boolean kBackLeftDriveEncoderReversed = false;
        public static final boolean kFrontRightDriveEncoderReversed = true;
        public static final boolean kBackRightDriveEncoderReversed = true;

        public static final int kFrontLeftDriveAbsoluteEncoderPort = 20;
        public static final int kBackLeftDriveAbsoluteEncoderPort = 21;
        public static final int kFrontRightDriveAbsoluteEncoderPort = 19;
        public static final int kBackRightDriveAbsoluteEncoderPort = 22;

        public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = false;
        public static final boolean kBackLeftDriveAbsoluteEncoderReversed = false;
        public static final boolean kFrontRightDriveAbsoluteEncoderReversed = false;
        public static final boolean kBackRightDriveAbsoluteEncoderReversed = false;

        public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = 2.212;
        public static final double kBackLeftDriveAbsoluteEncoderOffsetRad =  1.395;
        public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = -1.872;
        public static final double kBackRightDriveAbsoluteEncoderOffsetRad = 0.584;

        public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 1.15;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 3;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
    }
    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;

        public static final int kDriverXAxis = 0;
        public static final int kDriverYAxis = 1;
        public static final int kDriverRotAxis = 4;

        // -1 = Inverted
        public static final double kDriverXAxisInversion = 1;
        public static final double kDriverYAxisInversion = -1;
        public static final double kDriverRotAxisInversion = 1;


        public static final int kIndexerAxis = 3;
        public static final int kIntakeAxis = 4;
      
        public static final int kIntakeButtonIdx = 6;

        public static final int kOutakeIntakerButtonIdx = 8; 
        

        public static final int kDriverFieldOrientedButtonIdx = 7;

        public static final int kDriveGyroResetButtonIdx = 9;
        public static final int kLimeLightOrientButton = 1;
      
   
        public static final int kDriveShooterOutButtonIdx = 5;
        public static final int kDriveShooterInButtonIdx = 2;
        public static final int kMoveIntakeArmToUpPosButtonIdx  = 4;
        public static final int kMoveIntakeArmToDownPosButtonIdx  = 3;

        //Trigger needs to be pressed more than deadband to activate the indexer CMDs
        public static final double kIndexerTriggerDeadband = 0.1;




        public static final double kSwerveSpeedsDeadband = 0.1;


    }

    public static final class IntakeConstants {
      public static final double kIntakeSpeed = 0.35;
      public static final int kIntakeMotorID = 9;
      
      public static final int kLeftIntakeArmMotorID = 11;
      public static final int kRightIntakeArmMotorID = 12;
      public static final int kIntakeArmEncoderPort = 0; //DIO port; White Red Black (white closest to RIO)

      public static final int kThroughBoreEncoderTicks = 8192;
      public static final double kIntakeArmDownPosSetpoint = 0.76; // 0.765 2024-03-22 5:23pm
      public static final double kIntakeArmUpPosSetpoint = 0.612;    // 0.6 2024-03-22 5:23pm
    

      // MUST BE POSITIVE!!
      public static final double kIntakeArmMaxUpSpeed = 0.4; //(x percent speed)
      public static final double kIntakeArmMaxDownSpeed = 0.13; //(x percent speed)
      //

      public static final double kArmP = 5;
      
      public static final double kArmI = 0.1;
      public static final double kArmD = 0;




    }
    public static final class ShooterConstants{
      public static final int kUpperShooterMotorID = 14;
      public static final int kLowerShooterPort = 13;
      public static final double kShooterOutakeSpeed = 1;
      public static final double kShooterIntakeSpeed = -0.2;
    
      
    }
    public static final class  shooterArmConstants {
        public static final double kShooterArmGearRatio =  1 / 125;
        public static final int kShooterEncoderPort = 1;

    
      
    }
    public static final class AutoConstants {
      public static final double kMaxSpeedMetersPerSecond = DriveConstants.kPhysicalMaxSpeedMetersPerSecond / 2;
      public static final double kMaxAngularSpeedRadiansPerSecond = //
              DriveConstants.kPhysicalMaxAngularSpeedRadiansPerSecond / 10;
      public static final double kMaxAccelerationMetersPerSecondSquared = 3;
      public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
      public static final double kPXController = 0.5;
      public static final double kPYController = 0.5;
      public static final double kPThetaController = 0.5;

      public static final TrapezoidProfile.Constraints kThetaControllerConstraints = //
              new TrapezoidProfile.Constraints(
                      kMaxAngularSpeedRadiansPerSecond,
                      kMaxAngularAccelerationRadiansPerSecondSquared);



    //Custom Auto
    public static final double kDriveAutoP = 0.3;                      
    public static final double kDriveAutoI = 0;   
    public static final double kDriveAutoD = 0;  
    
    public static final double kTurningAutoP = 0.3;
    public static final double kTurningAutoI = 0;
    public static final double kTurningAutoD = 0;

  }

  public static class LimeLightConsants{

    public static final double Kp = -0.1f;

    public static final double kminCommand = 0.05f;
    public static final double kLimelightAngleDeadband = 1;
    public static final double kMinShoot = 24;
    public static final double kMaxShoot = 31;

    public static final double kLimelightThetaControllerP = 0.1;
    public static final double kLimelightThetaControllerI = 0.01;
    public static final double kLimelightThetaControllerD = 0;
    
    public static final double kLimelightDistanceControllerP = 0.1;
    public static final double kLimelightDistanceControllerI = 0.01;
    public static final double kLimelightDistanceControllerD = 0;
    public static final double kTyOffset = 5;



    
    //d = (h2-h1) / tan(a1+a2)


    // how many degrees back is your limelight rotated from perfectly vertical?
    public static final double limelightMountAngleDegrees = 48.0; //a1

    // distance from the center of the Limelight lens to the floor
    public static double limelightLensHeightInches = 19.5;  //h1

    // distance from the target to the floor
    public static double goalHeightInches = 56.375; // h2 




  }
  public static class IndexerConstants {

     /* Lower Indexer Motor is the Redline that controls orange 4" wheels
     * 
     * Upper Indexer Motor is Neo550 that controls black 2" wheels
     */
    public static final int kLowerIndexerPWMPort = 2;
    public static final int kUpperIndexerID = 16;
  
    public static final double kUpperMotorIndexerOutSpeed = -1;
    public static final double kLowerMotorIndexerOutSpeed = -0.5;

    public static final double kUpperMotorIndexerInSpeed = 1;
    public static final double kLowerMotorIndexerInSpeed = 0.5;

    public static final double kUpperMotorIndexerShooterInSpeed = 0.25;
    public static final double kLowerMotorIndexerShooterInSpeed = 0;
    
  }
}