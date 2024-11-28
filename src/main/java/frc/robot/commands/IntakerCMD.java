package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakerSub;


public class IntakerCMD extends Command {
    private final IntakerSub intakerSub;
    private final boolean intakeBool;
    private final boolean outakeBool;

    public IntakerCMD(IntakerSub intakerSub, boolean intakeBool, boolean outakeBool){
        this.intakerSub = intakerSub;
        this.intakeBool = intakeBool;
        this.outakeBool = outakeBool;
        addRequirements(intakerSub);
    }

    @Override
    public void initialize(){
        System.out.println("IntakeCMD started!");
    }

    @Override
    public void execute() {
        intakerSub.setIntakeSpeed(intakeBool, outakeBool);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("IntakeCMD ended!");
        intakerSub.setIntakeSpeed(false, false); //defaults end to set motors to 0
    }
    @Override
    public boolean isFinished(){
    return false;
    }
}

