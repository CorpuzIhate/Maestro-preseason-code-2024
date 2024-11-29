package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IndexerConstants;


public class IndexerSub extends SubsystemBase {
   private Victor indexMotor = new Victor(IndexerConstants.kIndexerPWMPort);


    public void setIndexSpeed(double indexerSpeed){
        indexMotor.set(indexerSpeed);
    }
}
