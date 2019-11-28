import java.util.ArrayList;
import processing.core.PImage;

public class Swallowing {
  private PImage image;
  private Fish deadFishRepresentImage;

  public Swallowing(PImage image, boolean aliveOrNot) {
    this.image = image;
    if (aliveOrNot) {
      this.deadFishRepresentImage = new Fish();
    } else {
      this.deadFishRepresentImage = new Fish(false);
    }
  }

  public void swallow(ArrayList<Fish> allFish) {
    if (this.image != null) {
      this.deadFishRepresentImage.activate();
      allFish.add(this.deadFishRepresentImage);
    }
  }
}
