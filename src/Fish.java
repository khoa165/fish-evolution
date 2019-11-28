import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
  // Class fields.
  private PImage icon;
  private boolean isActive;
  private static PApplet processing;
  private int xIconCoordinate;
  private int yIconCoordinate;
  private int age;
  private boolean fishAlive;
  private boolean showGameOver;

  public Fish() {
    this.isActive = true;
    this.icon = Fish.getProcessing().loadImage("images" + File.separator + "Evaporate.png");
    this.age = 0;
    this.fishAlive = true;
  }

  public Fish(boolean showGameOver) {
    this();
    this.fishAlive = false;
    this.showGameOver = showGameOver;
    if (!showGameOver) {
      this.icon = Fish.getProcessing().loadImage("images" + File.separator + "DeadIcon.png");
    } else {
      this.icon = Fish.getProcessing().loadImage("images" + File.separator + "GameOver.png");
    }
  }

  public boolean isActive() {
    // Returns true only when isActive is true.
    if (this.isActive) {
      return true;
    }
    return false;
  }

  public void activate() {
    // Changes isActive to true.
    this.isActive = true;
  }

  public void deactivate() {
    // Changes isActive to false.
    this.isActive = false;
  }

  public Swallowing update(int xIconCoordinate, int yIconCoordinate) {
    if (!this.showGameOver) {
      this.xIconCoordinate = xIconCoordinate;
      this.yIconCoordinate = yIconCoordinate;
    } else {
      this.xIconCoordinate = 395;
      this.yIconCoordinate = 115;
    }
    Fish.processing.image(this.icon, this.xIconCoordinate, this.yIconCoordinate);
    this.age++;
    if (this.age > 200 && !this.showGameOver) {
      this.deactivate();
    } else if (this.age > 200 && this.showGameOver) {
      this.age--;
    }
    return null;
  }

  public static void setProcessing(PApplet processing) {
    // Initializes processing field.
    Fish.processing = processing;
  }

  protected static PApplet getProcessing() {
    // Getter method to retrieve this static field.
    return Fish.processing;
  }

  public int controlMovementX() {
    return 0;
  }

  public int controlMovementY() {
    return 0;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  public boolean checkFishAlive() {
    return this.fishAlive;
  }
}

