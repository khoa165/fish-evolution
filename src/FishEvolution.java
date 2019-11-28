import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Driver class that runs the application. Recreate the game of big fish eating
 * smaller fish.
 * 
 * @author Harry Le.
 */
public class FishEvolution extends PApplet {
  // Class fields.
  // Random variable.
  private Random rand = new Random(); // Random generator.

  // Font.
  PFont font;
  PFont boldFont;

  // Static images.
  private PImage backgroundImage; // Background image.
  private PImage coralImage; // Decoration image.
  private PImage evaporateIcon; // Icon when eating other fishes.
  private PImage deadIcon; // Icon when main fish is eaten.

  // Player's fish.
  private UserFish mainFish; // Player's fish.
  private Swallowing swallowIcon;
  private Swallowing eatenIcon;

  // Fish type collection.
  private ArrayList<String> allFishTypes; // Collection of fish type.

  // Fish collection.
  private ArrayList<Fish> allFish; // All fish.
  private ArrayList<EatableFish> fishEaten; // Fish can be eaten by player's
                                            // fish.
  private ArrayList<EatableFish> eatenBySmallFish; // Fish can be eaten by small
                                                   // fish.
  private ArrayList<EatableFish> eatenByMediumFish; // Fish can be eaten by
                                                    // medium fish.
  private ArrayList<EatableFish> eatenByBlueShark; // Fish can be eaten by
                                                   // shark.
  private ArrayList<EatableFish> eatenByBeast; // Fish can be eaten by "beasts".

  // Fish generator rate control.
  private static int smallFishControl; // Control the rate of creating small
                                       // fish.
  private static int mediumFishControl; // Control the rate of creating medium
                                        // fish.
  private static int blueSharkControl; // Control the rate of creating sharks.
  private static int beastControl; // Control the rate of creating beasts.

  // Main button collection.
  private ArrayList<Button> mainButtons; // Collection of main buttons (always
                                         // visible).
  // ArrayList of __X__Clicked means collection of displayed button when X
  // button is clicked.
  private ArrayList<Button> newProfileClicked;
  private ArrayList<Button> pauseGameClicked;
  private ArrayList<Button> settingsClicked;
  private ArrayList<Button> displayedButtons;
  private ArrayList<Button> nameRequestClicked;
  private ArrayList<Button> gameInstructionClicked;
  private ArrayList<Button> startGameClicked;
  private ArrayList<Button> restartClicked;

  // Buttons.
  private Button latestClickedButton; // Button that was clicked most lately.

  private Button newProfileButton;
  private Button pauseGameButton;
  private Button settingsButton;
  // >>> Sub-buttons of newProfileButton.
  private FillTextButton nameRequestButton;
  private Button gameInstructionButton;
  private Button newGameButton;
  private Button returnFromInstructionButton;
  private Button progressToGameButton;
  private Button declineToPlayButton;
  // >>> Sub-buttons of pauseGameButton.
  private Button restartButton;
  private Button restartNewGameButton;
  private Button continueCurrentGameButton;
  private Button resumeButton;
  private Button logoutButton;
  // >>> Sub-buttons of settingsButton.
  private Button difficultyLevelButton;
  private Button languageButton;
  private Button performanceButton;
  private Button rankingsButton;
  private Button soundButton;
  private Button gameHistoryButton;

  // Game running fields.
  private boolean runApplication; // Control if the game is running.
  private boolean gameOver; // Control if the game is over.
  private static int gameOverTimeControl; // Control the time the image appears
                                          // after losing.

  // Username.
  private String userName; // Username.
  private String nameAppear; // Name appear on information bar.
  private boolean alreadyFillName; // Check if user fills name yet.

  // Setting options.
  private String languageDisplayed; // Game language.

  /**
   * Main method runs the application by calling the PApplet main method.
   * 
   * @param args unused.
   */
  public static void main(String[] args) {
    PApplet.main("FishEvolution");
  }

  /**
   * Method settings() sets the size of the interface to play the game.
   */
  @Override
  public void settings() {
    size(1190, 630);
  }

  /**
   * Method setup() initializes fields. Called once at the beginning of program.
   */
  @Override
  public void setup() {
    // Set up general properties of PApplet.
    this.setupGeneral();
    // Set up images.
    this.setupImages();

    // Set up fish type collection.
    this.setupFishTypeCollection();
    // Set up fish collection.
    this.setupFishCollection();
    // Set up player's main fish.
    this.setupUserFish();
    // Set up fish generator control.
    this.setupFishGeneratorRateControl();

    // Set up buttons.
    this.setupButtons();

    // Set up game running.
    this.setupGameRunning();

    // Set up username.
    this.setupUserName();

    // Set up initial default game screen.
    this.setupDefaultScreen();
  }

  /**
   * Set up general properties of PApplet.
   */
  private void setupGeneral() {
    // Set the name of the application in top left corner.
    this.getSurface().setTitle("FISH EVOLUTION, created by HARRY LE");
    // Set the current alignment for drawing text.
    this.textAlign(FishEvolution.CENTER, FishEvolution.CENTER);
    // Set the mode to draw rectangle.
    this.rectMode(FishEvolution.CORNERS);
    this.focused = true; // Confirms that our Processing program is "focused,"
                         // meaning that it is
                         // active and will accept mouse or keyboard input.
    Fish.setProcessing(this); // Set up PApplet field to pass into other
                              // classes.

    // Set up fonts.
    this.font = createFont("Times New Roman", 16, true);
    this.boldFont = createFont("Times New Roman-Bold", 16, true);

    // Set up default game language.
    this.languageDisplayed = "English";
  }

  /**
   * Set up images.
   */
  private void setupImages() {
    // Set up background and static decorations.
    this.backgroundImage =
        this.loadImage("images" + File.separator + "OceanBackground.jpg");
    this.coralImage = this.loadImage("images" + File.separator + "Coral.png");

    // Set up other images.
    // Image when user's fish eats other fish.
    this.evaporateIcon =
        this.loadImage("images" + File.separator + "Evaporate.png");
    // Image when user's fish gets eaten.
    this.deadIcon = this.loadImage("images" + File.separator + "DeadIcon.png");
  }

  /**
   * Set up fish type collection.
   */
  private void setupFishTypeCollection() {
    this.allFishTypes = new ArrayList<String>();
    this.allFishTypes.add("SmallFish");
    this.allFishTypes.add("MediumFish");
    this.allFishTypes.add("BlueShark");
    this.allFishTypes.add("KillerWhale");
  }

  /**
   * Set up fish collection.
   */
  private void setupFishCollection() {
    this.allFish = new ArrayList<Fish>();
    this.fishEaten = new ArrayList<EatableFish>();
    this.eatenBySmallFish = new ArrayList<EatableFish>();
    this.eatenByMediumFish = new ArrayList<EatableFish>();
    this.eatenByBlueShark = new ArrayList<EatableFish>();
    this.eatenByBeast = new ArrayList<EatableFish>();
  }

  /**
   * Set up player's main fish.
   */
  private void setupUserFish() {
    this.mainFish =
        new UserFish("UserFish1", 500, 300, this.fishEaten, this.swallowIcon);
    this.allFish.add(mainFish);
    // Swallow action helps to display evaporate icon when user's fish eats
    // other fish.
    this.swallowIcon = new Swallowing(this.evaporateIcon, true);
    // Swallow action helps to display dead icon when user's fish gets eaten.
    this.eatenIcon = new Swallowing(this.deadIcon, false);
  }

  /**
   * Initialize fish generator rate to control the number of fish per unit of
   * time.
   */
  private void setupFishGeneratorRateControl() {
    FishEvolution.smallFishControl = 0;
    FishEvolution.mediumFishControl = 0;
    FishEvolution.blueSharkControl = 0;
    FishEvolution.beastControl = 0;
  }

  /**
   * Set up buttons.
   */
  private void setupButtons() {
    // Button
    this.displayedButtons = new ArrayList<Button>();

    // Main buttons that are always visible at the top of the game window.
    this.newProfileButton = new Button("NEW PROFILE", 75, 15, this);
    this.pauseGameButton = new Button("PAUSE GAME", 225, 15, this);
    this.settingsButton = new Button("SETTINGS", 375, 15, this);
    // >>> Adding those buttons to the corresponding ArrayList.
    this.mainButtons = new ArrayList<Button>();
    this.mainButtons.add(this.newProfileButton);
    this.mainButtons.add(this.pauseGameButton);
    this.mainButtons.add(this.settingsButton);

    // Button that appears after clicking the new profile button.
    this.nameRequestButton = new FillTextButton("Please enter your name here!",
        595, 265, this, 190, 30);
    // >>> Adding the button to the corresponding ArrayList.
    this.newProfileClicked = new ArrayList<Button>();
    this.newProfileClicked.add(this.nameRequestButton);

    // Buttons that appear after clicking the name request button and finishing
    // entering name.
    this.gameInstructionButton =
        new Button(Config.getReadInstruction(this.languageDisplayed), 595, 335,
            this, 230, 30);
    this.newGameButton = new Button(Config.getStartGame(this.languageDisplayed),
        595, 375, this, 230, 30);
    // >>> Adding those buttons to the corresponding ArrayList.
    this.nameRequestClicked = new ArrayList<Button>();
    this.nameRequestClicked.add(this.gameInstructionButton);
    this.nameRequestClicked.add(this.newGameButton);

    // Button that appears after clicking the game instruction button.
    this.returnFromInstructionButton =
        new Button(Config.getReturnFromInstruction(this.languageDisplayed), 595,
            520, this, 590, 40);
    // >>> Adding those buttons to the corresponding ArrayList.
    this.gameInstructionClicked = new ArrayList<Button>();
    this.gameInstructionClicked.add(this.returnFromInstructionButton);

    // Buttons that appear after clicking the start a new game button.
    this.progressToGameButton =
        new Button(Config.getPositiveConfirm(this.languageDisplayed), 595, 275,
            this, 360, 50);
    this.declineToPlayButton =
        new Button(Config.getNegativeConfirm1(this.languageDisplayed), 595, 350,
            this, 360, 50);
    // >>> Adding those buttons to the corresponding ArrayList.
    this.startGameClicked = new ArrayList<Button>();
    this.startGameClicked.add(this.progressToGameButton);
    this.startGameClicked.add(this.declineToPlayButton);

    // Buttons that appear after clicking the pause game button.
    this.restartButton =
        new Button(Config.getRestartInLanguage(this.languageDisplayed), 395,
            325, this, 100, 50);
    this.resumeButton =
        new Button(Config.getResumeInLanguage(this.languageDisplayed), 595, 325,
            this, 100, 50);
    this.logoutButton =
        new Button(Config.getLogoutInLanguage(this.languageDisplayed), 795, 325,
            this, 100, 50);
    // >>> Adding those buttons to the corresponding ArrayList.
    this.pauseGameClicked = new ArrayList<Button>();
    this.pauseGameClicked.add(this.restartButton);
    this.pauseGameClicked.add(this.resumeButton);
    this.pauseGameClicked.add(this.logoutButton);

    // Buttons that appear after clicking the start a new game button.
    this.restartNewGameButton =
        new Button(Config.getPositiveConfirm(this.languageDisplayed), 595, 275,
            this, 360, 50);
    this.continueCurrentGameButton =
        new Button(Config.getNegativeConfirm2(this.languageDisplayed), 595, 350,
            this, 360, 50);
    // >>> Adding those buttons to the corresponding ArrayList.
    this.restartClicked = new ArrayList<Button>();
    this.restartClicked.add(this.restartNewGameButton);
    this.restartClicked.add(this.continueCurrentGameButton);

    // Buttons that appear after clicking the settings button.
    this.difficultyLevelButton = new Button(
        Config.getDifficultyLevel(this.languageDisplayed), 395, 275, this);
    this.languageButton =
        new Button(Config.getLanguage(this.languageDisplayed), 395, 350, this);
    this.performanceButton = new Button(
        Config.getPerformance(this.languageDisplayed), 595, 275, this);
    this.rankingsButton =
        new Button(Config.getRankings(this.languageDisplayed), 595, 350, this);
    this.soundButton =
        new Button(Config.getSoundOn(this.languageDisplayed), 795, 275, this);
    this.gameHistoryButton =
        new Button(Config.getHistory(this.languageDisplayed), 795, 350, this);
    // >>> Adding those buttons to the corresponding ArrayList.
    this.settingsClicked = new ArrayList<Button>();
    this.settingsClicked.add(this.difficultyLevelButton);
    this.settingsClicked.add(this.languageButton);
    this.settingsClicked.add(this.performanceButton);
    this.settingsClicked.add(this.rankingsButton);
    this.settingsClicked.add(this.soundButton);
    this.settingsClicked.add(this.gameHistoryButton);

    // Latest clicked button.
    this.latestClickedButton = null; // Set default value.
  }

  /**
   * Set up game running fields.
   */
  private void setupGameRunning() {
    this.runApplication = false; // Initialized as false, true only when new
                                 // profile is created.
    this.gameOver = false; // Initialized as false, true when user loses game.
    FishEvolution.gameOverTimeControl = 1; // Control the time the image appears
                                           // after losing.
  }

  /**
   * Set up fields used for username.
   */
  private void setupUserName() {
    this.userName = ""; // Initialized as empty string.
    this.nameAppear = "N/A"; // Displayed name on info bar initialized as N/A.
    this.alreadyFillName = false; // Initialized as false till user fills
                                  // his/her name.
  }

  /**
   * Set up initial default game screen.
   */
  private void setupDefaultScreen() {
    this.fill(200); // Set the fill color to light gray.
    this.rect(0, 0, 1190, 630); // Draw rectangle that covers full game screen.
    this.fill(0, 0, 255); // Set the fill color to blue.
    this.font = createFont("Times New Roman", 16, true); // Normal font.
    this.boldFont = createFont("Times New Roman-Bold", 16, true); // Bold font.
    this.textFont(this.font, 20); // Set font size.
    // Display welcome statement.
    this.text(Config.getWelcomeStatement(this.languageDisplayed), 595, 315);
  }

  /**
   * Method draw() is used to draw background and necessary objects.
   */
  @Override
  public void draw() {
    // Text size.
    this.textFont(this.font, 14); // Change font size to standard.

    // Running game.
    if (this.runApplication) {
      // Draw background and decorations.
      this.image(backgroundImage, 0, 30);
      this.image(coralImage, 0, 240);

      // Create fish.
      this.createLivingOceanCreature();

      // Update every fish.
      this.updateFish();
      this.updateMainFishImage();

      // Remove inactive fish (dead, or off-screen).
      this.removeInactiveFish();
    }

    // Set up button bar at the top of the screen and other buttons as needed.
    this.drawButtonBarAndOtherButtons();

  }

  /**
   * Draw visible top button bar and other buttons if needed.
   */
  private void drawButtonBarAndOtherButtons() {
    this.fill(255); // Set the fill color to white.
    this.rect(0, 0, 1190, 30); // Draw the white bar of game screen width.
    this.rect(890, 0, 1190, 30); // Box containing power and protein
                                 // information.
    this.fill(255, 0, 0); // // Set the fill color to red.
    this.text(Config.getPlayerInLanguage(this.languageDisplayed) + ": "
        + this.nameAppear + "   "
        + Config.getLevelInLanguage(this.languageDisplayed) + ": "
        + this.mainFish.getPower() + "   " + "XP: " + this.mainFish.getProtein()
        + " / " + this.mainFish.getEvolutionRequirement(), 1040, 15); // Print
                                                                      // player's
                                                                      // name,
                                                                      // level,
                                                                      // XP.
    for (int i = 0; i < this.mainButtons.size(); ++i) { // Loop to draw main
                                                        // buttons.
      this.mainButtons.get(i).draw();
    }
    for (Button button : this.displayedButtons) { // Loop to draw buttons that
                                                  // need displaying.
      button.draw();
    }
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.newProfileButton)) {
      // Only draw name request button when new profile button was clicked.
      this.nameRequestButton.draw();
    }
  }

  /**
   * Create fish using generator rate control variables.
   */
  private void createLivingOceanCreature() {
    for (int i = 0; i < this.allFishTypes.size(); ++i) { // Loop through all
                                                         // fish types to
                                                         // create.
      this.controlFishRate(this.allFishTypes.get(i));
    }
  }

  /**
   * Control the rate of creating corresponding fish according to typeFish
   * given.
   * 
   * @param typeFish type of fish needed controlling and creating.
   */
  private void controlFishRate(String typeFish) {
    if (typeFish.equals("SmallFish")) { // Small fish.
      if (FishEvolution.smallFishControl % 10 == 0) { // Create 1 every 10
                                                      // update.
        createSpecificFish(typeFish);
      }
      FishEvolution.smallFishControl++; // Increment control.
      if (FishEvolution.smallFishControl == 10) { // Reset control.
        FishEvolution.smallFishControl = 0;
      }
    } else if (typeFish.equals("MediumFish")) { // Medium fish.
      if (FishEvolution.mediumFishControl % 80 == 0) { // Create 1 every 80
                                                       // update
        createSpecificFish(typeFish);
      }
      FishEvolution.mediumFishControl++; // Increment control.
      if (FishEvolution.mediumFishControl == 80) { // Reset control.
        FishEvolution.mediumFishControl = 0;
      }
    } else if (typeFish.equals("BlueShark")) { // Blue shark.
      if (FishEvolution.blueSharkControl % 150 == 0) { // Create 1 every 150
                                                       // update.
        createSpecificFish(typeFish);
      }
      FishEvolution.blueSharkControl++; // Increment control.
      if (FishEvolution.blueSharkControl == 150) { // Reset control.
        FishEvolution.blueSharkControl = 0;
      }
    } else {
      // TODO. Not fully implemented yet. Beast is temporarily harmless.
      if (FishEvolution.beastControl % 300 == 0) { // Beast.
        createSpecificFish(typeFish);
      }
      FishEvolution.beastControl++; // Increment control.
      if (FishEvolution.beastControl == 300) { // Reset control.
        FishEvolution.beastControl = 0;
      }
    }
  }

  /**
   * Create corresponding fish according to typeFish given.
   * 
   * @param typeFish type of fish needed creating.
   */
  private void createSpecificFish(String typeFish) {
    if (typeFish.equals("SmallFish")) {
      EatableFish aSmallFish = // Create a small fish.
          new SmallFish("SmallFish", 0, this.rand.nextInt(500) + 50,
              this.eatenBySmallFish);
      this.allFish.add(aSmallFish); // Add to the collection of all fish.
      this.fishEaten.add(aSmallFish); // User's fish can eat small fish.
      this.eatenByMediumFish.add(aSmallFish); // Medium fish can eat small fish.
      this.eatenByBlueShark.add(aSmallFish); // Blue shark can eat small fish.
      this.eatenByBeast.add(aSmallFish); // Beast can eat small fish.
    } else if (typeFish.equals("MediumFish")) {
      MediumFish aMediumFish = // Create a medium fish.
          new MediumFish("MediumFish", -100, this.rand.nextInt(500) + 50,
              this.eatenByMediumFish);
      this.allFish.add(aMediumFish); // Add to the collection of all fish.
      this.fishEaten.add(aMediumFish); // User's fish can eat medium fish (with
                                       // condition).
      this.eatenByBlueShark.add(aMediumFish); // Blue shark can eat medium fish.
      this.eatenByBeast.add(aMediumFish); // Beast can eat medium fish.
    } else if (typeFish.equals("BlueShark")) {
      BlueShark aBlueShark = new BlueShark("BlueShark", -199,
          this.rand.nextInt(300) + 150, this.eatenByBlueShark);
      this.allFish.add(aBlueShark); // Add to the collection of all fish.
      this.fishEaten.add(aBlueShark); // User's fish can eat blue shark (with
                                      // condition).
      this.eatenByBeast.add(aBlueShark); // Beast can eat blue shark.
    } else {
      // TODO needs more beasts beside whale, also makes whale can eat other
      // animals.
      this.allFish.add(new UnbeatableBeast("KillerWhale", 1100,
          this.rand.nextInt(500) + 50));
    }
  }

  /**
   * Traverse through the list and call update for every fish.
   */
  private void updateFish() {
    if (gameOver) { // Check for game over.
      if (FishEvolution.gameOverTimeControl % 75 == 0) {
        this.gameOver = false; // Change to false for next game.
        // Add fish representation of game over (like dead fish) to trigger game
        // over image.
        this.allFish.add(new Fish(true));
        FishEvolution.gameOverTimeControl = 0; // Reset to avoid overflow.
      }
      FishEvolution.gameOverTimeControl++;
    }
    for (int i = 0; i < this.allFish.size(); ++i) { // Loop through all fish.
      Swallowing fishSwallowing =
          this.allFish.get(i).update(this.mainFish.getXIconCoordinate(),
              this.mainFish.getYIconCoordinate()); // Update fish.
      if (fishSwallowing != null) { // Only non-null if it's user's fish and
                                    // some other conditions.
        if (this.allFish.get(i) instanceof UserFish) { // Check for safety.
          if (this.mainFish.checkIfAlive()) { // Check alive.
            // Passing true to show that user's fish eats other fish.
            this.swallowIcon = new Swallowing(this.evaporateIcon, true);
            this.swallowIcon.swallow(this.allFish); // Activate the evaporate
                                                    // icon.
          } else { // User's fish is dead.
            this.eatenIcon = new Swallowing(this.deadIcon, false);
            // Passing false with deadIcon to show that user's fish gets eaten.
            this.eatenIcon.swallow(this.allFish);
            this.removeAllIcon(); // Remove all icons on screen.
            this.gameOver = true; // Losing game.
          }
        }
      }
    }
  }

  /**
   * Change user's fish image based on level.
   */
  private void updateMainFishImage() {
    if (this.mainFish.getPower() < 4) {
      this.mainFish.setImage("UserFish" + Math.round(this.mainFish.getPower()));
    }
  }

  /**
   * Remove all icons on screen.
   */
  private void removeAllIcon() {
    for (int i = 0; i < this.allFish.size(); ++i) { // Loop through all fish.
      // Eliminate all real fish (EatableFish, UnbeatableBeast) which leaves
      // only "fake" fish that
      // represents icon.
      if ((this.allFish.get(i) instanceof Fish)
          && !(this.allFish.get(i) instanceof EatableFish)
          && !(this.allFish.get(i) instanceof UnbeatableBeast)) {
        Fish fishRepresentIcon = (Fish) this.allFish.get(i); // Get fish
                                                             // instance.
        if (fishRepresentIcon.checkFishAlive()) { // Check alive.
          this.allFish.remove(i); // Remove fish.
          i--; // Decrement index after removing.
        }
      }
    }
  }

  /**
   * Remove all inactive fish.
   */
  private void removeInactiveFish() {
    for (int i = 0; i < this.allFish.size(); ++i) { // Loop through all fish.
      if (!this.allFish.get(i).isActive()) { // Check if inactive.
        this.allFish.remove(i); // Remove the Thing.
        i--; // Since we just remove a fish, has to decrement the index.
      }
    }
  }

  /**
   * Called when mouse is pressed.
   */
  @Override
  public void mousePressed() {
    // Helper method for main buttons that are always visible.
    this.mainButtonMousePressed();
    // Helper method for button that requests username.
    this.nameRequestMousePressed();
    // Helper method for game instruction button.
    this.gameInstructionMousePressed();
    // Helper method for button to return from game instruction.
    this.returnFromInstructionMousePressed();
    // Helper method for button to start game.
    this.startNewGameMousePressed();
    // Helper method for button to confirm progress to play game or decline and
    // log out.
    this.progressOrDeclineMousePressed();
    // Helper method for button to restart game.
    this.restartMousePressed();
    // Helper method for button to resume game.
    this.startNewGameOrContinueCurrentGame();
    // Helper method for button to resume game.
    this.resumeMousePressed();
    // Helper method for button to logout game.
    this.logoutMousePressed();
  }

  /**
   * Helper method for main buttons that are always visible.
   */
  private void mainButtonMousePressed() {
    // User clicks new profile option.
    if (this.newProfileButton.isMouseOver()) {
      // Call another helper method for new profile button.
      this.newProfileMousePressed();
      // Change the latest clicked button.
      this.latestClickedButton = this.newProfileButton;
    }
    // User clicks pause game option.
    else if (this.pauseGameButton.isMouseOver()) {
      // Call another helper method for pause game button.
      this.pauseGameMousePressed();
      // Change the latest clicked button.
      this.latestClickedButton = this.pauseGameButton;
    }
    // User clicks settings option.
    else if (this.settingsButton.isMouseOver()) {
      // Call another helper method for settings button.
      this.settingsMousePressed();
      // Change the latest clicked button.
      this.latestClickedButton = this.settingsButton;
    }
  }

  /**
   * Helper method for button to create a new profile.
   */
  private void newProfileMousePressed() {
    // Empty the displayed button collection.
    this.displayedButtons = new ArrayList<Button>();
    // Add all sub-buttons.
    this.displayedButtons.addAll(this.newProfileClicked);
    // Background box that contains text and buttons.
    this.mainButtonClickedDisplay();
    // Request name from user.
    this.text(Config.getNameRequest(this.languageDisplayed), 585, 225);
  }

  /**
   * Helper method for button to pause game.
   */
  private void pauseGameMousePressed() {
    // Empty the displayed button collection.
    this.displayedButtons = new ArrayList<Button>();
    // Add all sub-buttons.
    this.displayedButtons.addAll(this.pauseGameClicked);
    // Background box that contains text and buttons.
    this.mainButtonClickedDisplay();
    // Options when pause game.
    this.text(Config.getPauseWarning(this.languageDisplayed), 592, 250);
  }

  /**
   * Helper method for button of settings.
   */
  private void settingsMousePressed() {
    // Empty the displayed button collection.
    this.displayedButtons = new ArrayList<Button>();
    // Add all sub-buttons.
    this.displayedButtons.addAll(this.settingsClicked);
    // Background box that contains text and buttons.
    this.mainButtonClickedDisplay();
    // Settings options.
    this.text(Config.getPresentOption(this.languageDisplayed), 592, 220);
  }

  /**
   * Helper method for background box that contains text and buttons.
   */
  private void mainButtonClickedDisplay() {
    this.runApplication = false; // Stop game when any button is clicked.
    this.stroke(0); // Set line value to black.
    this.strokeWeight(1); // Set line width to 1.
    this.fill(100); // Set the fill color to gray.
    this.rect(0, 200, 1189, 400);
    this.fill(255); // Set the fill color to white.
  }

  /**
   * Helper method for button that requests username.
   */
  private void nameRequestMousePressed() {
    // Check user clicked new profile button.
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.newProfileButton)) {
      // this.nameRequestButton.isMouseOver().
      if (this.displayedButtons.get(0).isMouseOver()) {
        // Update latest clicked button.
        this.latestClickedButton = this.nameRequestButton;
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
      }
    }
  }

  /**
   * Helper method for button to read game instruction.
   */
  private void gameInstructionMousePressed() {
    // Check if user already filled username and the latest clicked button is
    // nameRequestButton.
    if (this.alreadyFillName && this.latestClickedButton != null
        && this.latestClickedButton.equals(this.nameRequestButton)) {
      // this.gameInstructionButton.isMouseOver().
      if (this.displayedButtons.get(0).isMouseOver()) {
        // Call helper method to display game instruction.
        this.gameInstruction();
        // Update latest clicked button.
        this.latestClickedButton = this.gameInstructionButton;
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
      }
    }
  }

  /**
   * Helper method for button to start new game.
   */
  private void startNewGameMousePressed() {
    // Check if user already filled username and the latest clicked button is
    // nameRequestButton.
    if (this.alreadyFillName && this.latestClickedButton != null
        && this.latestClickedButton.equals(this.nameRequestButton)) {
      // this.newGameButton.isMouseOver().
      if (this.displayedButtons.get(1).isMouseOver()) {
        // Background box that contains text and buttons.
        this.mainButtonClickedDisplay();
        // Confirm new game.
        this.text(Config.getConfirmNewGame(this.languageDisplayed), 590, 225);
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
        // Add all sub-buttons.
        this.displayedButtons.addAll(this.startGameClicked);
        // Update latest clicked button.
        this.latestClickedButton = this.newGameButton;
      }
    }
  }

  /**
   * Helper method for button to return from game instruction.
   */
  private void returnFromInstructionMousePressed() {
    // Check latest clicked.
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.gameInstructionButton)) {
      // Check if user clicks return.
      if (this.returnFromInstructionButton.isMouseOver()) {
        // Update latestClickedButton to the state before clicking the game
        // instruction, which is nameRequestButton.
        this.latestClickedButton = this.nameRequestButton;
        // Re-draw the screen after filling name.
        this.drawContentInProfileOption();
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
        // Add all sub-buttons.
        this.displayedButtons.addAll(this.nameRequestClicked);
      }
    }
  }

  /**
   * Helper method for button to start playing.
   */
  private void progressOrDeclineMousePressed() {
    // Check if user clicked new game button.
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.newGameButton)) {
      // this.progressToGameButton.isMouseOver().
      if (this.displayedButtons.get(0).isMouseOver()) {
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
        this.runApplication = true; // Change to true so game can start.
        this.latestClickedButton = null; // Reset latest clicked button.
      }
      // this.declineToPlayButton.isMouseOver().
      else if (this.displayedButtons.get(1).isMouseOver()) {
        this.setup();
      }
    }
  }

  /**
   * Helper method for button that restarts game.
   */
  private void restartMousePressed() {
    // Check user clicked new profile button.
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.pauseGameButton)) {
      // this.restartButton.isMouseOver().
      if (this.displayedButtons.get(0).isMouseOver()) {
        // Background box that contains text and buttons.
        this.mainButtonClickedDisplay();
        // Confirm new game.
        this.text(Config.getConfirmNewGame(this.languageDisplayed), 590, 225);
        // Update latest clicked button.
        this.latestClickedButton = this.restartButton;
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
        // Add all sub-buttons.
        this.displayedButtons.addAll(this.restartClicked);
      }
    }
  }

  /**
   * Helper method for button to start playing.
   */
  private void startNewGameOrContinueCurrentGame() {
    // Check if user clicked new game button.
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.restartButton)) {
      // this.restartNewGameButton.isMouseOver().
      if (this.displayedButtons.get(0).isMouseOver()) {
        String username = this.userName; // Save name before reset everything.
        this.nameAppear = username;
        this.setup(); // Reset everything
        this.runApplication = true; // Run application.
      }
      // this.continueCurrentGameButton.isMouseOver().
      else if (this.displayedButtons.get(1).isMouseOver()) {
        this.runApplication = true; // Change to true so game can resume.
      }
    }
  }


  /**
   * Helper method for button that resumes game.
   */
  private void resumeMousePressed() {
    // Check user clicked new profile button.
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.pauseGameButton)) {
      // this.resumeButton.isMouseOver().
      if (this.displayedButtons.get(1).isMouseOver()) {
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
        this.runApplication = true; // Change to true so game can resume.
        this.latestClickedButton = null; // Reset latest clicked button.
      }
    }
  }

  /**
   * Helper method for button to start playing.
   */
  private void logoutMousePressed() {
    // Check if user clicked new game button.
    if (this.latestClickedButton != null
        && this.latestClickedButton.equals(this.pauseGameButton)) {
      // this.logoutButton.isMouseOver().
      if (this.displayedButtons.get(2).isMouseOver()) {
        this.setup();
      }
    }
  }

  /**
   * Called whenever a key on keyboard is pressed.
   */
  @Override
  public void keyPressed() {
    // Check if newProfileButton was clicked.
    if (this.latestClickedButton != null
        // Progress if clicked.
        && this.latestClickedButton.equals(this.nameRequestButton)) {
      if (this.key != FishEvolution.ENTER) { // Check if not ENTER key.
        // If BACKSPACE key, delete last char.
        if (this.key == FishEvolution.BACKSPACE) {
          if (this.userName.length() > 0) {
            this.userName =
                this.userName.substring(0, this.userName.length() - 1);
            this.printUserName();
          }
        }
        // If not BACKSPACE key, if key is letter or number, add to userName.
        else if (Character.isLetter(this.key) || Character.isDigit(this.key)) {
          this.userName += this.key;
          this.printUserName();
        }

      } else { // ENTER key is pressed.
        this.drawContentInProfileOption(); // Draw the screen confirming name.
        this.alreadyFillName = true; // User already filled name.
        // Empty the displayed buttons collection.
        this.displayedButtons = new ArrayList<Button>();
        // Add all sub-buttons.
        this.displayedButtons.addAll(this.nameRequestClicked);
      }
    }

    // TODO
    // Suggestion ideas:
    // 1. CONTROL + P --> Pause.
    // 2. TAB --> move between options
    // 3. ENTER --> Choose.
  }

  /**
   * Helper method to help print username.
   */
  private void printUserName() {
    this.fill(0); // Set the fill color to white.
    this.rect(500, 250, 689, 280); // Create a rectangle for entering name.
    this.fill(255); // Set the fill color to black.
    this.text(this.userName.toUpperCase() + "|", 595, 265); // Print name.
  }

  /**
   * Helper method to draw screen confirming name.
   */
  private void drawContentInProfileOption() {
    this.fill(200); // Set the fill color to light gray.
    this.rect(0, 0, 1190, 630); // Clear screen.
    this.fill(100); // Set the fill color to dark gray.
    // Gray background that contains text and buttons.
    this.rect(0, 200, 1189, 400);
    this.fill(0); // Set the fill color to black.
    this.rect(0, 200, 1189, 270); // Black background to highlight username.
    this.fill(255, 0, 0); // Set the fill color to red.
    this.textFont(this.boldFont, 30); // Change font size to 30.
    // Print username (3 times to make it bolder).
    this.text(this.userName.toUpperCase(), 595, 235);
    this.text(this.userName.toUpperCase(), 595, 235);
    this.text(this.userName.toUpperCase(), 595, 235);
    // Copy userName to nameAppear to display.
    this.nameAppear = this.userName.toUpperCase();
    this.textFont(this.boldFont, 14); // Change font size to 14.
    this.fill(255); // Set the fill color to white.
    this.text(Config.getConfirmName(this.languageDisplayed)
        + this.userName.toUpperCase(), 595, 285); // Confirm name statement.
    this.fill(255, 255, 0); // Set the fill color to yellow.
    // Warning statement.
    this.text(Config.getWarningModifyName(this.languageDisplayed), 595, 300);
  }

  /**
   * Helper method to display game instruction.
   */
  private void gameInstruction() {
    this.fill(200); // Set the fill color to light gray.
    this.rect(0, 0, 1190, 630); // Clear screen.
    this.fill(255, 255, 0); // Set the fill color to yellow.
    this.rect(300, 30, 890, 630); // Background for text.
    this.fill(0, 0, 255); // Set the fill color to blue.
    this.textFont(this.boldFont, 19); // Change font size.
    this.textAlign(FishEvolution.LEFT, FishEvolution.CENTER); // Set align.
    // For loop to display text line by line to avoid overflow out of box.
    for (int i =
        0; i < Config.getInstruction(this.languageDisplayed).length; ++i) {
      this.text(Config.getInstruction(this.languageDisplayed)[i], 330,
          150 + 25 * i);
    }
    // Change back to standard align.
    this.textAlign(FishEvolution.CENTER, FishEvolution.CENTER);
    this.fill(255); // Set the fill color to white.
    this.rect(300, 500, 890, 540); // Return from game instruction.
    this.fill(255, 0, 0); // Set the fill color to red.
    this.text(Config.getReturnFromInstruction(this.languageDisplayed), 595,
        520);
  }
}

