import java.io.File;
import java.util.Random;
import processing.core.PImage;

public class UnbeatableBeast extends Fish {
  private PImage image;
  private int x;
  private int y;
  private int fishMovementX;
  private int fishMovementY;
  private Random rand = new Random();

  public UnbeatableBeast(String fileName, int x, int y) {
    // Initialize this new fish.
    // super(fileName);
    this.x = x;
    this.y = y;
    image = Fish.getProcessing().loadImage("images" + File.separator + fileName + ".png");
    this.fishMovementX = 0;
    this.fishMovementY = 0;
  }

  public PImage getImage() {
    return this.image;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int controlMovementX() {
    this.fishMovementX = this.fishMovementX + this.rand.nextInt(3) + 1;
    this.x -= this.fishMovementX;
    return this.x;
  }

  @Override
  public Swallowing update(int xIconCoordinate, int yIconCoordinate) {
    this.fishMovementX = this.fishMovementX + this.rand.nextInt(5) + 1;
    // if (this.fishControl % 50 == 0) {
    // this.fishMovementY = this.fishMovementY + this.rand.nextInt(101) - 50;
    // }
    // this.fishControl++;
    // Draws image at its position before returning null.
    Fish.getProcessing().image(this.image, this.x - this.fishMovementX, this.y + fishMovementY);
    this.setAge(this.getAge() + 1);
    if (this.getAge() > 1500) {
      this.deactivate();
    }
    return null;
  }

  public void move(int dx, int dy) {
    // Changes x by adding dx to it (and y by dy).
    this.x += dx;
    this.y += dy;
  }

  public boolean isOver(int x, int y) {
    // Return true only when point x,y is over image.
    // Check whether x is larger than the image right side or smaller than image left side.
    // Check whether y is larger than image bottom side and smaller than image top side.
    // If yes, means it's not over image => return false.
    if (x > (image.width + this.x) || x < this.x || y < this.y || y > (image.height) + this.y) {
      return false;
    }
    return true;
  }

  public boolean isOver(EatableFish other) {
    // Overlap when left side of one is less than right side of another.
    // Therefore, not overlap, return false when one left side is larger than another right side.
    if (other.getX() > (this.x + this.image.width)
        || this.x > (other.getX() + other.getImage().width)) {
      return false;
    }
    // Overlap when top side of one is less than bottom side of another.
    // Therefore, not overlap, return false when one top side is larger than another bottom side.
    if (other.getY() > (this.y + this.image.height)
        || this.y > (other.getY() + other.getImage().height)) {
      return false;
    }
    return true;
  }
}
