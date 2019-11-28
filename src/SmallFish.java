import java.util.ArrayList;
import java.util.Random;

public class SmallFish extends EatableFish {
  private int fishMovementX;
  private int fishMovementY;
  private int fishControl;
  private Random rand = new Random();
  private final static double power = 0.5;

  public SmallFish(String fileName, int x, int y, ArrayList<EatableFish> target) {
    super(fileName, x, y, target);
  }

  @Override
  public double getPower() {
    return SmallFish.power;
  }

  @Override
  public int controlMovementX() {
    this.fishMovementX = this.fishMovementX + this.rand.nextInt(5) + 1;
    return this.fishMovementX;
  }

  @Override
  public int controlMovementY() {
    if (this.fishControl % 50 == 0) {
      this.fishMovementY = this.rand.nextInt(5) - 2;
    }
    this.fishControl++;
    this.set(this.getY() + this.fishMovementY);
    if (this.fishControl == 50) {
      this.fishControl = 0;
    }
    return this.fishMovementY;
  }

  @Override
  public Swallowing update(int xIconCoordinate, int yIconCoordinate) {
    super.update(xIconCoordinate, yIconCoordinate);
    return null;
  }
}
