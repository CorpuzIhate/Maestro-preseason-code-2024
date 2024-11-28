package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakerSub extends SubsystemBase {
    public PIDController intakeArmPidController = new PIDController(IntakeConstants.kArmP, IntakeConstants.kArmI, IntakeConstants.kArmD);

    private CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotorID, MotorType.kBrushless);
    
    private CANSparkMax leftIntakeArmMotor = new CANSparkMax(IntakeConstants.kLeftIntakeArmMotorID, MotorType.kBrushless);
    private CANSparkMax rightIntakeArmMotor = new CANSparkMax(IntakeConstants.kRightIntakeArmMotorID, MotorType.kBrushless);

    public DutyCycleEncoder intakeArmEncoder = new DutyCycleEncoder(IntakeConstants.kIntakeArmEncoderPort);
    public void IntakeSub(){
    }

    public void setIntakeSpeed(boolean intakeBool, boolean outakeBool){
        if(intakeBool){
            intakeMotor.set(IntakeConstants.kIntakeSpeed);
        
        }else if (outakeBool){
            intakeMotor.set(-IntakeConstants.kIntakeSpeed);

        } else {
            intakeMotor.set(0);
        }
    } // 

    public void setIntakeArmMotorSpeed0 (){
        rightIntakeArmMotor.set(0);
        leftIntakeArmMotor.set(0);
    }
    public void setIntakeArmMotorSetpoint (double intakeArmSetpoint){

        intakeArmPidController.setSetpoint(intakeArmSetpoint);
        // sets the setpoint in the PID  Controller


        double intakeArmSpeed = intakeArmPidController.calculate(intakeArmEncoder.getAbsolutePosition());

        // Converts it into speeds with manual limiter

        //ASSUMES DOWN IS NEGATIVE

        if (intakeArmSpeed > IntakeConstants.kIntakeArmMaxDownSpeed){ 
            // if the desired speed is greater than the MaxUpSpeed, set to max up speed
            rightIntakeArmMotor.set(-IntakeConstants.kIntakeArmMaxDownSpeed);// go up at max up speed
            leftIntakeArmMotor.set(IntakeConstants.kIntakeArmMaxDownSpeed);
            } 
        else if (intakeArmSpeed < -IntakeConstants.kIntakeArmMaxUpSpeed){ 
            //negative is needed because arm speed is + in Constants.java
            // < is needed because higher negative magnitude is less than lower negative magnitude

            // if the desired speed is less (more in neg magntitude) than the MaxdownSpeed, set to max down speed

            //negative signs flipped because the desired max speed is negative
        rightIntakeArmMotor.set(IntakeConstants.kIntakeArmMaxUpSpeed);//go down at max down speed
        leftIntakeArmMotor.set(-IntakeConstants.kIntakeArmMaxUpSpeed);
        } 
        else{ //set it to its desired speed, which is already safe
            //intake speed here will be naturally negative if need be; no need for sign flip
            rightIntakeArmMotor.set(-intakeArmSpeed);
            leftIntakeArmMotor.set(intakeArmSpeed);
        }
    }
            // Maestro likes rightIntakeArmMotor.set(-intakeArmSpeed) & leftIntakeArmMotor.set(intakeArmSpeed) (2024-03-28)

    @Override
    public void periodic(){
        /// telmetry

        SmartDashboard.putNumber("IntakeArmPos", intakeArmEncoder.get());
        SmartDashboard.putNumber("IntakeArmAbsPos", intakeArmEncoder.getAbsolutePosition());
        SmartDashboard.putNumber("IntakeArmPower", rightIntakeArmMotor.getAppliedOutput());
        SmartDashboard.putNumber("IntakeDesired", intakeArmPidController.getSetpoint());


        //Debug Setpoint
    }
    public void resetEncoders(){
        intakeArmEncoder.reset();
    }

    

}