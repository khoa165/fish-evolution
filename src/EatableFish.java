import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;

public class EatableFish extends Fish {
  private PImage image;
  private int x;
  private int y;
  private int fishMovementX;
  private int fishMovementY;
  private Random rand = new Random();
  private int fishControl;
  private ArrayList<EatableFish> target;
  private Swallowing swallow;
  private int trackX;
  private int trackY;
  private PImage icon;
  private int xIconCoorindate;
  private int yIconCoordinate;

  public EatableFish(String fileName, int x, int y, ArrayList<EatableFish> target) {
    // Initialize this new fish.
    super();
    this.x = x;
    this.y = y;
    this.target = target;
    image = Fish.getProcessing().loadImage("images" + File.separator + fileName + ".png");
    this.fishMovementX = 0;
    this.fishMovementY = 0;
    this.fishControl = 0;
    this.trackX = x;
    this.trackY = y;
  }

  public Random getRandom() {
    return this.rand;
  }

  public void setImage(String newImageFile) {
    this.image = Fish.getProcessing().loadImage("images" + File.separator + newImageFile + ".png");
  }

  public PImage getImage() {
    return this.image;
  }

  public ArrayList<EatableFish> getTarget() {
    return this.target;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void set(int y) {
    this.y = y;
  }

  public int getTrackX() {
    return this.trackX;
  }

  public int getTrackY() {
    return this.trackY;
  }

  public double getPower() {
    return 0;
  }

  public int getFishMovementX() {
    return this.fishMovementX;
  }

  public int getFishMovementY() {
    return this.fishMovementY;
  }

  public int controlMovementX() {
    // System.out.println("reach x");
    this.fishMovementX = this.fishMovementX + this.rand.nextInt(5) + 1;
    return this.fishMovementX;
    // return 0;
  }

  public int controlMovementY() {
    // System.out.println("reach y");
    if (this.fishControl % 50 == 0) {
      this.fishMovementY = this.rand.nextInt(3) - 1;
    }
    this.y += this.fishMovementY;
    this.fishControl++;
    if (this.fishControl == 50) {
      this.fishControl = 0;
    }
    return this.fishMovementY;
    // return 0;
  }

  @Override
  public Swallowing update(int xIconCoordinate, int yIconCoordinate) {
    this.trackX = this.x + this.controlMovementX();
    this.trackY = this.y + this.controlMovementY();
    // Draws image at its position before returning null.
    Fish.getProcessing().image(this.image, this.trackX, this.trackY);
    this.setAge(this.getAge() + 1);
    if (this.getAge() > 1500) {
      this.deactivate();
    }
    return this.drop();
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
    if ((other.trackX + other.image.width * 0.1) > (this.trackX + this.image.width)
        || (this.trackX + this.image.width * 0.4) > (other.trackX + other.image.width)) {
      return false;
    }
    // Overlap when top side of one is less than bottom side of another.
    // Therefore, not overlap, return false when one top side is larger than another bottom side.
    if ((other.trackY + other.image.height * 0.3) > (this.trackY + this.image.height)
        || (this.trackY + this.image.height * 0.3) > (other.trackY + other.image.height)) {
      return false;
    }
    return true;
  }

  protected Swallowing drop() {
    for (int i = 0; i < this.target.size(); ++i) {
      if (this.target.get(i) != null) {
        if (this.isOver(target.get(i)) && this.target.get(i).isActive()) {
          if (this.getPower() > this.target.get(i).getPower()) {
            target.get(i).deactivate();
            this.icon = Fish.getProcessing().loadImage("images" + File.separator + "Disappear.png");
            this.xIconCoorindate = (this.getTrackX() + this.getTarget().get(i).getTrackX()) / 2;
            this.yIconCoordinate = (this.getTrackY() + this.getTarget().get(i).getTrackY()) / 2;
            return this.swallow;
          }
          // TODO review this, this else{} code is not necessary. Target only includes the fish that
          // this fish can eat, there is no way this can be deactivated.
          else {
            this.deactivate();
            return this.swallow;
          }
        }
      }
    }
    return null;
  }

  public PImage getIconImage() {
    return this.icon;
  }

  public int getXIconCoordinate() {
    return this.xIconCoorindate;
  }

  public int getYIconCoordinate() {
    return this.yIconCoordinate;
  }
}
