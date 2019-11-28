import java.util.ArrayList;

public class UserFish extends EatableFish {
  // Class fields.
  private boolean mouseWasPressed;
  private boolean isDragging;
  private int oldMouseX; // Horizontal position of mouse during last update.
  private int oldMouseY; // Vertical position of mouse during last update.
  private Swallowing swallow;
  private int power;
  private double protein;
  private boolean alreadyEaten;
  private int xIconCoorindate;
  private int yIconCoordinate;

  public UserFish(String fileName, int x, int y, ArrayList<EatableFish> target,
      Swallowing swallow) {
    // Initialize new thing.
    super(fileName, x, y, target);
    this.swallow = swallow;
    this.power = 1;
    this.protein = 0;
    this.alreadyEaten = false;

    // Initialize both mouseWasPressed and isDragging to false.
    this.mouseWasPressed = false;
    this.isDragging = false;
  }

  @Override
  public double getPower() {
    return this.power;
  }

  public double getProtein() {
    return this.protein;
  }

  public double getEvolutionRequirement() {
    return 10 * Math.pow(2, this.power);
  }

  @Override
  public boolean isOver(EatableFish other) {
    // Overlap when left side of one is less than right side of another.
    // Therefore, not overlap, return false when one left side is larger than another right side.
    if ((other.getTrackX() + other.getImage().width * 0.1) > (this.getX() + this.getImage().width)
        || (this.getX() + this.getImage().width * 0.4) > (other.getTrackX()
            + other.getImage().width)) {
      return false;
    }
    // Overlap when top side of one is less than bottom side of another.
    // Therefore, not overlap, return false when one top side is larger than another bottom side.
    if ((other.getTrackY() + other.getImage().height * 0.3) > (this.getY() + this.getImage().height)
        || (this.getY() + this.getImage().height * 0.3) > (other.getTrackY()
            + other.getImage().height)) {
      return false;
    }
    return true;
  }

  @Override
  public Swallowing update(int xIconCoordinate, int yIconCoordinate) {
    Fish.getProcessing().image(this.getImage(), this.getX(), this.getY());
    int dx = 0;
    int dy = 0;
    // Check if mouse is over image and assign to isOverItem variable.
    boolean isOverItem = this.isOver(Fish.getProcessing().mouseX, Fish.getProcessing().mouseY);
    // Check if mouse is being pressed and assign to mouseBeingPressed.
    boolean mouseBeingPressed = Fish.getProcessing().mousePressed;

    if (!mouseWasPressed && isOverItem && mouseBeingPressed) {
      mouseWasPressed = true;
      isDragging = true;
    }
    if (mouseBeingPressed) {
      mouseWasPressed = true;
    }
    if (mouseBeingPressed && mouseWasPressed && isDragging) {
      if (oldMouseX != 0 || oldMouseY != 0) {
        dx = Fish.getProcessing().mouseX - this.oldMouseX;
        dy = Fish.getProcessing().mouseY - this.oldMouseY;
        move(dx, dy);
      }
      // Assign current coordinates of mouse to old coordinate variables.
      this.oldMouseX = Fish.getProcessing().mouseX;
      this.oldMouseY = Fish.getProcessing().mouseY;
    }
    if (!mouseBeingPressed && mouseWasPressed && isDragging) {
      isDragging = false;
      mouseWasPressed = false;
    }
    if (!mouseBeingPressed) {
      mouseWasPressed = false;
    }
    if (this.protein > this.getEvolutionRequirement()) {
      this.power++;
    }
    return this.drop();

  }

  @Override
  protected Swallowing drop() {
    for (int i = 0; i < this.getTarget().size(); ++i) {
      if (this.getTarget().get(i) != null) {
        // System.out.println(i + " over or not " + this.isOver(target.get(i))); // --> problem
        // System.out.println("active or not: " + this.target.get(i).isActive());
        if (this.isOver(this.getTarget().get(i)) && this.getTarget().get(i).isActive()) {
          if (this.power > this.getTarget().get(i).getPower()) {
            this.getTarget().get(i).deactivate();
            this.protein += this.getTarget().get(i).getPower();
            this.xIconCoorindate = this.getTarget().get(i).getTrackX();
            this.yIconCoordinate = this.getTarget().get(i).getTrackY();
            this.alreadyEaten = false;
            return this.swallow;
          } else {
            this.deactivate();
            this.xIconCoorindate = this.getTarget().get(i).getTrackX();
            this.yIconCoordinate = this.getTarget().get(i).getTrackY();
            this.alreadyEaten = true;
            return this.swallow;
          }
        }
      }
    }
    return null;
  }

  public boolean checkIfAlive() {
    return !this.alreadyEaten;
  }

  public int getXIconCoordinate() {
    return this.xIconCoorindate;
  }

  public int getYIconCoordinate() {
    return this.yIconCoordinate;
  }
}
