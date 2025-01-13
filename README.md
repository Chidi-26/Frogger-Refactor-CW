*****Frogger Game Coursework 2*****

This frogger game was developed using JavaFX and essentially, the player controlls the frog and guides it past the various obstacles and hazards and reach all 5 safe zones without losing all your lives via either drowning in the water, or getting hiy by a truck/car.

***Key Features***

 - Live System:
  The player starts with a set number of lives, 10 and each live is lost when either frog sinks in the water or collides with a truck or car. The game ends if all lives are lost along   with a pop up mesage on the screen which shows your ending score.

- Score Systen:
  Points are awarded for successfuly getting closer to the safe zone and once you have reached all 5 safe zones, a message pops up one the screen showing that you have won and the       amount of points that you have attained.

- Obstacles:
 Features moving obstacles such as logs, cars, trucks turtles and sinking turtles.

- Background Music:
 Features looped background music which betters the gaming experience - captivates and immerses the player.

- Game Timer:
 Keeps track of current game events with a rel time timer, tracks lives lost, wheather game has finished or not and the accumilates player's score and even starts the game initially.


***How To Play***

*Controlls*
- W - Moves frog upwards.
- A - Moves frog to the left.
- S - Moves frog downwards.
- D - Moves frog to the right.

  *Objective*
  Reach all the safe zones with the frog to win while avoiding hitting the obstacles and dying.

  *Rules
  - If you die 10 times you lose the game.
  - Gain points when you get closer to the safe zone but also lose points the more you die.
  - If the frog hits a car/ truck or drowns in the water the frog dies and gets reset back to the starting position.
 
  **Major Classes and Methods**

  **Animal** Class
  Represents the frog character in the game and it manages its movement, collisions and its state
   *Key Methods*
  
    /**
	 * {@code pressedKey} handles key pressed logic
	 * @param event triggered by a key pressed
	 */
	private void pressedKey(KeyEvent event){}

	/**
	 * {@code releasedKey} method handles key released logic
	 * @param event - triggered by user
	 */
	private void releasedKey(KeyEvent event){}

**GameManager** Class
Handles game logic - tracks the score, displays the winning/ loosing messages and also manages the frogs life

*Key Methods*
    /**
     * {@code createTimer} method that creates a timer and checks if the game has ended
     */
    public void createTimer() {}
    /**
     * {@code handlesLifeLoss} method handles logic for when the player loses a life
     */
    public void handlesLifeLoss(){

**InitGame** Class

Sets up the game environment, including the background, obstacles, and frog.

*Key Methods*
  /**
     * Creates a singleton instance
     * @param background - MyStage component that sets up the background
     * @return instance
     */
    public static InitGame getIgInstance(MyStage background){}

    
**Credits**
Developer: Chidi Ikwunze
Design inspiration: https://projects.cs.nott.ac.uk/2024-comp2013/frogger-refactor-coursework



    

    


