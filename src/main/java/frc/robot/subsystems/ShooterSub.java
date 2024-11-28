package frc.robot.subsystems;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ShooterConstants;

public class ShooterSub extends SubsystemBase {
   private CANSparkMax upperShooterMotor = new CANSparkMax(ShooterConstants.kUpperShooterMotorID, MotorType.kBrushless);

    private CANSparkMax lowerShooterMotor = new CANSparkMax(ShooterConstants.kLowerShooterPort,MotorType.kBrushless);
    
    
    public void shooterSub(){
        

    }
    public void setShooterSpeed( double shooterSpeed){

            upperShooterMotor.set(-shooterSpeed);
            lowerShooterMotor.set(-shooterSpeed); // hard negative
        
        
    }
}
