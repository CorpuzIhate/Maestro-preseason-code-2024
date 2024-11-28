package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class ShooterCMD extends Command  {
    private final ShooterSub shooterSub;
    private final double shooterSpeed;

    public ShooterCMD(ShooterSub shooterSub, double shooterSpeed){
        this.shooterSub = shooterSub;
        this.shooterSpeed = shooterSpeed;


    }
    
    @Override
    public void initialize(){
        System.out.println("shootercmd started!");
    }

    @Override
    public void execute() {
        shooterSub.setShooterSpeed(shooterSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Shootercmd ended!");
        shooterSub.setShooterSpeed(0); //defaults end to set motors to 0
    }
}
