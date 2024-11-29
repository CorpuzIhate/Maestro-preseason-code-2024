package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IntakeOutakeSub;

public class MoveIntakeArmCMD extends Command  {
    private final IntakeOutakeSub intakeOutakeSub;
    private final PIDController intakeArmPidController;
    private double intakeArmSetpoint;


    public MoveIntakeArmCMD(IntakeOutakeSub intakeOutakeSub, double intakeArmSetpoint){
        this.intakeArmSetpoint = intakeArmSetpoint;
        this.intakeOutakeSub = intakeOutakeSub;
        this.intakeArmPidController = intakeOutakeSub.intakeArmPidController;
       // intakeArmPidController.setSetpoint(intakeArmSetpoint);


        addRequirements(intakeOutakeSub);
 

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
        intakeOutakeSub.setIntakeArmMotorSetpoint(intakeArmSetpoint); 
        //input a RAW setpoint into IntakeArmSetpoint

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("MoveIntakeArmCMD ended!");
        //intakeOutakeSub.setIntakeArmMotorSetpoint(intakeArmSetpoint); 
        intakeOutakeSub.setIntakeArmMotorSpeed0();
        // intakeOutakeSub.setIntakeArmMotor(0); //defaults end to set motors to 0
    }
    @Override
    public boolean isFinished(){
        return false;
    }
}
