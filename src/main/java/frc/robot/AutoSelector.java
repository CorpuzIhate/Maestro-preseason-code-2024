package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto.AutosToSelect.DoNothingAuto;
import frc.robot.auto.AutosToSelect.JustShootAuto;




public class AutoSelector {
    private SendableChooser<Command> autoSelector = new SendableChooser<>();

    public AutoSelector(RobotContainer container) {

        /*
         * Add all the options
         */
        autoSelector.setDefaultOption("DO nothing!!!", DoNothingAuto(container));
        // autoSelector.addOption("(Center)Move Foward and shoot Swerve", MoveForwardAndShootAuto(container));
        
        autoSelector.addOption("Just Shoot", JustShootAuto(container));        
        // autoSelector.addOption("(Center)Move Forward", MoveForwardAuto(container));
        // autoSelector.addOption("(Center) LL Move Forward", MoveForwardLLAuto(container));
        // autoSelector.addOption("(Center)  Move PID ", CenterMoveForwardPID(container));


        // autoSelector.addOption("(Side) Move Forward  ", SideMoveForwardAuto(container));
        // autoSelector.addOption("(Side) Shoot and Move", SideShootAndMoveForwardAuto(container));

        // autoSelector.addOption("(Side) Shoot and Move V2", SideShootAndMoveForwardAutoV2(container));
        // autoSelector.addOption("(Side) SUNDAY Shoot and Move ", SideSundayMoveForwardShoot(container));
        
        
    }


    public SendableChooser<Command> getAutoChooser() {
        return autoSelector;
    }

    /*
     * Allow calling the autos as a method
     */
    private SequentialCommandGroup DoNothingAuto(RobotContainer container) {
        return new DoNothingAuto(container);
    }
    private SequentialCommandGroup JustShootAuto(RobotContainer container) {
        return new JustShootAuto(container);
    }
    
}