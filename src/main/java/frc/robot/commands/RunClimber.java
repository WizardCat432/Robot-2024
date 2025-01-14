package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;
import java.util.function.DoubleSupplier;

public class RunClimber extends Command {
  private Climber leftClimber;
  private Climber rightClimber;

  private double velocity;
  private DoubleSupplier limit;

  private boolean reversed;

  /** Creates a new ClimberToPosition */
  public RunClimber(
      Climber leftClimber,
      Climber rightClimber,
      double velocity,
      DoubleSupplier limit,
      boolean reversed) {
    this.leftClimber = leftClimber;
    this.rightClimber = rightClimber;

    this.velocity = velocity;
    this.limit = limit;

    this.reversed = reversed;

    addRequirements(leftClimber, rightClimber);
  }

  /** Called when the command is initially scheduled */
  @Override
  public void initialize() {
    leftClimber.stop();
    rightClimber.stop();

    // leftClimber.softLimitEnabled(true);
    // rightClimber.softLimitEnabled(true);
  }

  /** Called every time the scheduler runs while the command is scheduled */
  @Override
  public void execute() {
    SmartDashboard.putNumber("Climber Position", leftClimber.getPosition());
    if (!reversed
        && (leftClimber.getPosition() < limit.getAsDouble()
            && rightClimber.getPosition() < limit.getAsDouble())) {
      leftClimber.runVelocity(velocity);
      rightClimber.runVelocity(velocity);
    } else if (reversed && (leftClimber.getPosition() > 0 || rightClimber.getPosition() > 0)) {
      leftClimber.runVelocity(-velocity);
      rightClimber.runVelocity(-velocity);
    } else {
      leftClimber.stop();
      rightClimber.stop();
    }
  }

  /** Called once the command ends or is interrupted */
  @Override
  public void end(boolean interrupted) {
    leftClimber.stop();
    rightClimber.stop();

    // leftClimber.softLimitEnabled(false);
    // rightClimber.softLimitEnabled(false);
  }

  /** Returns true when the command should end */
  @Override
  public boolean isFinished() {
    return false;
  }
}
