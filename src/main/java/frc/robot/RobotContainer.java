// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OIConstants;


import frc.robot.commands.IndexerCMD;
import frc.robot.commands.IntakeCMD;

import frc.robot.commands.MoveIntakeArmCMD;
import frc.robot.commands.ResetHeadingCMD;
import frc.robot.commands.ShooterCMD;


import frc.robot.subsystems.IndexerSub;
import frc.robot.subsystems.IntakeOutakeSub;

import frc.robot.subsystems.ShooterSub;
import edu.wpi.first.wpilibj2.command.Command;


import frc.robot.subsystems.SwerveSub;


import com.pathplanner.lib.commands.PathPlannerAuto;


import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...




  private final SwerveSub swerveSub =  new SwerveSub();
  
  private final IntakeOutakeSub intakeOutakeSub = new IntakeOutakeSub();
  private final ShooterSub shooterSub = new ShooterSub();



  private final IndexerSub indexerSub = new IndexerSub();

  private final Joystick driverJoyStick = new Joystick(OIConstants.kDriverControllerPort);




  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    

    // Configure the trigger bindings
    // swerveSub.setDefaultCommand(new SwerveJoystickCmd(
    //   swerveSub, limeLightSub,
    //   () -> -driverJoyStick.getRawAxis(OIConstants.kDriverYAxis),
    //   () -> driverJoyStick.getRawAxis(OIConstants.kDriverXAxis),
    //   () -> driverJoyStick.getRawAxis(OIConstants.kDriverRotAxis),
    //   () -> !driverJoyStick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx),
    //   () -> !driverJoyStick.getRawButton(OIConstants.kDriveLimeOrientButtonIdx))); // by defualt will work on fields reference frame
    
    intakeOutakeSub.setDefaultCommand(new IntakeCMD(intakeOutakeSub, false, false)); //needs outake and intake bool -> both false to default the motors to 0
    
    // indexerSub.setDefaultCommand(new IndexerCMD(indexerSub,  () -> driverJoyStick.getRawAxis(OIConstants.kDriverIndexerIntakeAxis)));



    configureBindings();
  }

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
   
   new JoystickButton(driverJoyStick, OIConstants.kDriveIntakeButtonIdx).whileTrue(new IntakeCMD(intakeOutakeSub, true, false));
   new JoystickButton(driverJoyStick, OIConstants.kDriveOutakeButtonIdx).whileTrue(new IntakeCMD(intakeOutakeSub, false, true));
   new JoystickButton(driverJoyStick, OIConstants.kDriveShooterButtonIdx).whileTrue(new ShooterCMD(shooterSub,  true));

   new JoystickButton(driverJoyStick, 1).whileTrue(new IndexerCMD(indexerSub,  0.7));
    new JoystickButton(driverJoyStick, 2).whileTrue(new IndexerCMD(indexerSub,  -0.7));


  //  new JoystickTrigger(driverJoyStick, OIConstants.kIndexerButtonIdx).whileTrue(new IndexerCMD(indexerSub, 0));

   new JoystickButton(driverJoyStick, OIConstants.kDriveGyroResetButtonIdx).whileTrue(new ResetHeadingCMD(swerveSub));

      //move arm to position

   // currentIntakeArmPosSetpoint
   new JoystickButton(driverJoyStick, OIConstants.kMoveIntakeArmToUpPosButtonIdx)
   .onTrue(new MoveIntakeArmCMD(intakeOutakeSub, IntakeConstants.kIntakeArmUpPosSetpoint)); // button 7
  
   new JoystickButton(driverJoyStick, OIConstants.kMoveIntakeArmToDownPosButtonIdx)
   .onTrue(new MoveIntakeArmCMD(intakeOutakeSub, IntakeConstants.kIntakeArmDownPosSetpoint));

   new JoystickButton(driverJoyStick, OIConstants.kMoveIntakeArmToMidPosButtonIdx)
   .onTrue(new MoveIntakeArmCMD(intakeOutakeSub,IntakeConstants.kIntakeArmMidPosSetpoint)); // button 9
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous


    //PathPlannerPath path =  PathPlannerPath.fromPathFile("test"); // wil run the auto named
    // test in the pathPlanner GUI

    //return AutoBuilder.followPath(path);



    return new PathPlannerAuto("testPath_2024_11_29");


  // return new PathPlannerAuto("test");
  }
}