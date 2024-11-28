package frc.robot.auto.auto_commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ShooterSub;

public class InitalizeShooterAutoCMD extends Command  {
    private final ShooterSub shooterSub;

    private final Timer timer;
    private double totalTime;



    public InitalizeShooterAutoCMD(ShooterSub shooterSub, double totalTime){
        this.shooterSub = shooterSub;
        this.totalTime = totalTime;

        timer = new Timer();



    }
    
    @Override
    public void initialize(){
        System.out.println("shootercmd started!");
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        shooterSub.setShooterSpeed(1);
        // indexerSub.setIndexSpeed(-IndexerConstants.kIndexerMaxSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Shootercmd ended!");
        shooterSub.setShooterSpeed(0
        ); //defaults end to set motors to 0
        // indexerSub.setIndexSpeed(0);
    }
    @Override
    public boolean isFinished() {
      return timer.get() >= totalTime;
    }
}
