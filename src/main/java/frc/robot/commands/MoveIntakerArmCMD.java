package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakerSub;

public class MoveIntakerArmCMD extends Command  {
    private final IntakerSub intakerSub;
    private final PIDController intakeArmPidController;
    private double intakeArmSetpoint;


    public MoveIntakerArmCMD(IntakerSub intakerSub, double intakeArmSetpoint){
        this.intakeArmSetpoint = intakeArmSetpoint;
        this.intakerSub = intakerSub;
        this.intakeArmPidController = intakerSub.intakeArmPidController;

        addRequirements(intakerSub);
 

    }
    public double getDebugIntakeArmSetpoint(){
        return intakeArmPidController.getSetpoint();
    }
    
    @Override
    public void initialize(){
        System.out.println("MoveIntakeArmCMD started!");
        intakeArmPidController.reset();
    }


    @Override
    public void execute() {
        intakerSub.setIntakeArmMotorSetpoint(intakeArmSetpoint); 
        //input a RAW setpoint into IntakeArmSetpoint

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("MoveIntakeArmCMD ended!");
        //intakeOutakeSub.setIntakeArmMotorSetpoint(intakeArmSetpoint); 
        intakerSub.setIntakeArmMotorSpeed0();
        // intakeOutakeSub.setIntakeArmMotor(0); //defaults end to set motors to 0
    }
    @Override
    public boolean isFinished(){
        return false;
    }
}
