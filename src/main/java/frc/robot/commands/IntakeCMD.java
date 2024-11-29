package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeOutakeSub;


public class IntakeCMD extends Command {
    private final IntakeOutakeSub intakeOutakeSub;
    private final boolean intakeBool;
    private final boolean outakeBool;

    public IntakeCMD(IntakeOutakeSub intakeOutakeSub, boolean intakeBool, boolean outakeBool){
        this.intakeOutakeSub = intakeOutakeSub;
        this.intakeBool = intakeBool;
        this.outakeBool = outakeBool;
        addRequirements(intakeOutakeSub);
    }

    @Override
    public void initialize(){
   
    }

    @Override
    public void execute() {
        intakeOutakeSub.setIntakeSpeed(intakeBool, outakeBool);
    }

    @Override
    public void end(boolean interrupted) {
        
        intakeOutakeSub.setIntakeSpeed(false, false); //defaults end to set motors to 0
    }
    @Override
    public boolean isFinished(){
    return false;
    }
}

