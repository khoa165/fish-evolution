import processing.core.PApplet;

public class FillTextButton extends Button {
  public FillTextButton(String label, float x, float y, PApplet processing, int width, int height) {
    super(label, x, y, processing, width, height);
  }

  public void draw() {
    this.processing.stroke(0); // Set line value to black.
    this.processing.strokeWeight(1); // Set line width to 1.
    if (this.isMouseOver())
      this.processing.fill(200); // Set the fill color to dark gray if the mouse is over the option.
    else
      this.processing.fill(255); // White
    // Draw the button (rectangle with a centered text)
    this.processing.rect(this.positionX - this.width / 2.0f, this.positionY - this.height / 2.0f,
        this.positionX + this.width / 2.0f, this.positionY + this.height / 2.0f);
    if (!this.isMouseOver()) {
      this.processing.fill(150); // Set the fill color to black.
      // Display the text of the option.
      this.processing.text(label, this.positionX, this.positionY);
    }
  }
}
