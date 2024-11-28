// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;

import frc.robot.commands.IndexerCMD;
import frc.robot.commands.IntakerCMD;
import frc.robot.commands.MoveIntakerArmCMD;
import frc.robot.commands.ResetHeadingCMD;
import frc.robot.commands.ShooterCMD;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.IndexerSub;
import frc.robot.subsystems.IntakerSub;
import frc.robot.subsystems.LimeLightSub;
import frc.robot.subsystems.ShooterSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.SwerveSub;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final CommandXboxController m_driverController =
      new CommandXboxController(OIConstants.kDriverControllerPort);

  private final SwerveSub swerveSub =  new SwerveSub();
  private final LimeLightSub limeLightSub = new LimeLightSub();
  private final IntakerSub intakeOutakeSub = new IntakerSub();
  private final ShooterSub shooterSub = new ShooterSub();
  private final IndexerSub indexerSub = new IndexerSub();

  private final Joystick driverJoyStick = new Joystick(OIConstants.kDriverControllerPort);


  private final Trigger indexOutTrigger  = new Trigger(() -> m_driverController.getLeftTriggerAxis() > 0.1);
  private final Trigger indexInTrigger  = new Trigger(() -> m_driverController.getRightTriggerAxis() > 0.1);

  //autos

  private AutoSelector autoSelector = new AutoSelector(this);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    SmartDashboard.putData("gyro Reset?", new InstantCommand(() -> swerveSub.zeroHeading()) );
    
    ShuffleboardTab mainTab = Shuffleboard.getTab("Main");
    mainTab.add("autoMode", autoSelector.getAutoChooser()).withSize(2,1)
    .withPosition(0, 0);



    //Configure the trigger bindings
    swerveSub.setDefaultCommand(new SwerveJoystickCmd(
      swerveSub, limeLightSub,
      () -> OIConstants.kDriverYAxisInversion * driverJoyStick.getRawAxis(OIConstants.kDriverYAxis),
      () -> OIConstants.kDriverXAxisInversion * driverJoyStick.getRawAxis(OIConstants.kDriverXAxis),
      () -> OIConstants.kDriverRotAxisInversion * driverJoyStick.getRawAxis(OIConstants.kDriverRotAxis),
      () -> driverJoyStick.getRawButton(OIConstants.kLimeLightOrientButton),
      () -> !driverJoyStick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx))); // by defualt will work on fields reference frame
    
    //intakeOutakeSub.setDefaultCommand(new IntakeCMD(intakeOutakeSub, false, false)); //needs outake and intake bool -> both false to default the motors to 0
    
    // indexerSub.setDefaultCommand(new IndexerCMD(indexerSub,  () -> driverJoyStick.getRawAxis(OIConstants.kDriverIndexerIntakeAxis)));



    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
   


  //Reset Gyro
   new JoystickButton(driverJoyStick, OIConstants.kDriveGyroResetButtonIdx).whileTrue(new ResetHeadingCMD(swerveSub));
   


  //SHOOTER ACTIONS


  //Shooter Out
   new JoystickButton(driverJoyStick, OIConstants.kDriveShooterOutButtonIdx).whileTrue(new ShooterCMD(shooterSub,  
   ShooterConstants.kShooterOutakeSpeed));

  //Shooter in (Collecting from Source)
   new JoystickButton(driverJoyStick, OIConstants.kDriveShooterInButtonIdx).whileTrue(new ShooterCMD(shooterSub,  
   ShooterConstants.kShooterIntakeSpeed));
   //Index In tied to same button
   new JoystickButton(driverJoyStick, OIConstants.kDriveShooterInButtonIdx).whileTrue(new IndexerCMD(indexerSub, IndexerConstants.kUpperMotorIndexerShooterInSpeed, IndexerConstants.kLowerMotorIndexerShooterInSpeed));
   


  //INDEXER ACTIONS


  //Indexer Out
  indexOutTrigger.whileTrue(new IndexerCMD(indexerSub, IndexerConstants.kUpperMotorIndexerOutSpeed, IndexerConstants.kLowerMotorIndexerOutSpeed));

  //Indexer In
  indexInTrigger.whileTrue(new IndexerCMD(indexerSub, IndexerConstants.kUpperMotorIndexerInSpeed, IndexerConstants.kLowerMotorIndexerInSpeed));



  //INTAKER ACTIONS


  //Intaker In
  new JoystickButton(driverJoyStick,OIConstants.kIntakeButtonIdx ).whileTrue(new IntakerCMD(intakeOutakeSub, true, false));

  //Intaker Out
  new JoystickButton(driverJoyStick,OIConstants.kOutakeIntakerButtonIdx ).whileTrue(new IntakerCMD(intakeOutakeSub, false, true));



  //INTAKER ARM ACTIONS


  //Move Intaker Arm Up
   new JoystickButton(driverJoyStick, OIConstants.kMoveIntakeArmToUpPosButtonIdx)
   .onTrue(new MoveIntakerArmCMD(intakeOutakeSub, IntakeConstants.kIntakeArmUpPosSetpoint)); // button 7
  
  //Move Intaker Arm Down
   new JoystickButton(driverJoyStick, OIConstants.kMoveIntakeArmToDownPosButtonIdx)
   .onTrue(new MoveIntakerArmCMD(intakeOutakeSub, IntakeConstants.kIntakeArmDownPosSetpoint));


  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() { 

    return autoSelector.getAutoChooser().getSelected();

  }

  public SwerveSub getSwerveSub(){
    return swerveSub;
  }
  public ShooterSub getShooterSub(){
    return shooterSub;
  }
  public IndexerSub getIndexerSub(){
    return indexerSub;
  }
  public IntakerSub getIntakeOutakeSub(){
    return intakeOutakeSub;
  }
  public LimeLightSub getliLimeLightSub(){
    return limeLightSub;
  }
}
