import processing.core.PApplet;

public class Button {
  protected PApplet processing;
  protected int width = 150;
  protected int height = 30;
  protected float positionX; // Center x position.
  protected float positionY; // Center y position.
  protected String label;

  public Button(String label, float x, float y, PApplet processing) {
    this.processing = processing;
    this.positionX = x;
    this.positionY = y;
    this.label = label;
  }

  public Button(String label, float x, float y, PApplet processing, int width, int height) {
    this(label, x, y, processing);
    this.width = width;
    this.height = height;
  }

  public void draw() {
    this.processing.stroke(0); // Set line value to black.
    this.processing.strokeWeight(1); // Set line width to 1.
    if (this.isMouseOver())
      this.processing.fill(150); // Set the fill color to dark gray if the mouse is over the option.
    else
      this.processing.fill(255); // White
    // Draw the button (rectangle with a centered text)
    this.processing.rect(this.positionX - this.width / 2.0f, this.positionY - this.height / 2.0f,
        this.positionX + this.width / 2.0f, this.positionY + this.height / 2.0f);
    this.processing.fill(0); // Set the fill color to black.
    this.processing.text(label, this.positionX, this.positionY); // Display the text of the option.
  }

  /**
   * Checks whether the mouse is over this option.
   * 
   * @return true if the mouse is over this option, false otherwise.
   */
  public boolean isMouseOver() {
    if (this.processing.mouseX > (this.positionX - (this.width / 2.0f))
        && this.processing.mouseX < (this.positionX + (this.width / 2.0f))
        && this.processing.mouseY > (this.positionY - (this.height / 2.0f))
        && this.processing.mouseY < (this.positionY + (this.height / 2.0f))) {
      return true; // Mouse is over this option.
    }
    return false; // Mouse is not over this option.
  }
}
