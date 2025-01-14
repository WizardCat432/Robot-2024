package frc.robot.commands.auto;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.drive.Drive;

public class DriveBack extends SequentialCommandGroup {
  public DriveBack(Drive drive, double seconds) {
    addCommands(
        Commands.runOnce(() -> drive.runVelocity(new ChassisSpeeds(-1.5, 0, 0)), drive),
        new WaitCommand(seconds),
        Commands.runOnce(() -> drive.runVelocity(new ChassisSpeeds(0, 0, 0)), drive));
  }
}
