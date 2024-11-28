package frc.robot.auto.AutosToSelect;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;


public class DoNothingAuto extends SequentialCommandGroup {
    
public DoNothingAuto(RobotContainer robot){ 
    SmartDashboard.putBoolean( "Do Nothing?", true);

}

}
