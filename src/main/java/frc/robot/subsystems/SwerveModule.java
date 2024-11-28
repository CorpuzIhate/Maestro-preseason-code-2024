package frc.robot.subsystems; 

import com.ctre.phoenix6.hardware.CANcoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ModuleConstants;

import frc.robot.Constants.DriveConstants;


public class SwerveModule {
    private final CANSparkMax driveMotor;
    private final CANSparkMax turningMotor;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    private final PIDController turningPidController;

    private final CANcoder absoluteEncoder;
    private final boolean absoluteEncoderReversed;
    private final double absoluteEncoderOffsetRad;

    public SwerveModule( int driveMotorID, int turningMotorID, boolean driveMotorReversed, 
    boolean turningMotorReversed, int absoluteEncoderID, double absoluteEncoderOffset, boolean absoluteEncoderReversed){

        this.absoluteEncoderOffsetRad = absoluteEncoderOffset;
        this.absoluteEncoderReversed = absoluteEncoderReversed;
        absoluteEncoder = new CANcoder(absoluteEncoderID); 



        driveMotor = new CANSparkMax((driveMotorID), MotorType.kBrushless);
        turningMotor = new CANSparkMax(turningMotorID, MotorType.kBrushless);

        driveMotor.setInverted(driveMotorReversed);
        turningMotor.setInverted(turningMotorReversed); 

        driveEncoder = driveMotor.getEncoder();
        turningEncoder = turningMotor.getEncoder();


        driveEncoder.setPositionConversionFactor(ModuleConstants.kDriveEncoderRot2Meter);
        driveEncoder.setVelocityConversionFactor(ModuleConstants.kDriveEncoderRPM2MeterPerSec);
        turningEncoder.setPositionConversionFactor(ModuleConstants.kTurningEncoderRot2Rad);
        turningEncoder.setVelocityConversionFactor(ModuleConstants.kTurningEncoderRPM2RadPerSec);
        
        turningPidController = new PIDController(ModuleConstants.kTurning, 0,0);

        turningPidController.enableContinuousInput(-Math.PI, Math.PI); // Tells PID that the system is circular
 
        resetEncoders();

    }

    public double getDrivePostion(){
        return driveEncoder.getPosition();
    }

    public double getTurningPositon(){
        return  ((absoluteEncoder.getAbsolutePosition().getValue() * 2 * Math.PI) - absoluteEncoderOffsetRad);
        //returns a values between - pi and pi
    }
    public double getDriveVelocity(){
        return driveEncoder.getVelocity();
    }

    public double getTurningVelocity(){
        return turningEncoder.getVelocity();
    }
    public double getAbsoluteEncoderRad(){

        double angle = (absoluteEncoder.getAbsolutePosition().getValue());  // give the how much percent of a rotation were readin
        angle *= 2.0 * Math.PI; // convert to radians
        angle -= absoluteEncoderOffsetRad; 
      //  SwerveJoystickCmd.CurrentModuleAngles[MotorID] = angle * (absoluteEncoderReversed ? -1.0 : 1.0);

        

        return angle * (absoluteEncoderReversed ? -1.0 : 1.0); // gives the Encoder value based on if the Encoder is reversed
    }
    public void resetEncoders(){
        driveEncoder.setPosition(0);
        turningEncoder.setPosition(getAbsoluteEncoderRad()); // reset the turning encoder to absoulute encoder value
    }

    public SwerveModuleState getState(){
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPositon()));
    }
    
    public SwerveModulePosition getSwerveModulePosition(){
        return new SwerveModulePosition(getDrivePostion(), new Rotation2d(getTurningPositon()));
    }

    public void setDesiredState(SwerveModuleState state){
        if(Math.abs(state.speedMetersPerSecond) < 0.001) // were not really moving do not reset the motors
        {
            stop();
            return;
        }

        state = SwerveModuleState.optimize(state, getState().angle);
        driveMotor.set(state.speedMetersPerSecond / DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        turningMotor.set(turningPidController.calculate(getTurningPositon(), state.angle.getRadians()));
        SmartDashboard.putString("Swerve[" + absoluteEncoder.getDeviceID() + "] state", state.toString());



    }

    public void setDriveMotor(double speed){


        driveMotor.set(speed);
        // turningMotor.set(turningPidController.calculate(getDrivePostion(),0));


    }
    public void setMotor(double driveSpeed, double turningSpeed)
    {
        driveMotor.set(driveSpeed);
        turningMotor.set(turningSpeed);
    }

    public void stop(){
        driveMotor.set(0);
        turningMotor.set(0);
        
    }
    public void sendToDashboard(){

        SmartDashboard.putNumber("Drive[" + absoluteEncoder.getDeviceID() + "] output", driveMotor.getAppliedOutput());


        SmartDashboard.putNumber("Turning[" + absoluteEncoder.getDeviceID() + "] output", turningMotor.getAppliedOutput());

        SmartDashboard.putNumber("DrivePos[" + absoluteEncoder.getDeviceID() + "]", getDrivePostion());



        SmartDashboard.putNumber("TurningPos[" + absoluteEncoder.getDeviceID() + "]", getTurningPositon());

        SmartDashboard.putNumber("AbsPos[" + absoluteEncoder.getDeviceID() + "] ", absoluteEncoder.getAbsolutePosition().getValue());


    }


}