# Pong Game - README

## Overview
This project is a simple Pong game implemented in Java using the Swing library for graphics and event handling. The game consists of paddles controlled by players and a ball that bounces around the screen. Players must move their paddles to hit the ball and prevent it from passing their side.

## Features
- Two-player mode with keyboard controls.
- Smooth paddle and ball movement.
- Ball bounces off paddles and screen edges.
- Scorekeeping system.
- Collision detection for realistic gameplay.

## Classes Breakdown
### 1. `GamePanel` Class
The `GamePanel` class is the core of the game, managing the game loop, rendering, and collision detection.

#### Key Features
- Runs the game in a separate thread.
- Handles ball and paddle movement.
- Detects collisions between the ball and paddles.
- Updates the score when a player misses the ball.

#### Important Variables
- `static final int GAME_WIDTH, GAME_HEIGHT`: Defines the game window size.
- `Paddle paddle1, paddle2`: Represents the two paddles.
- `Ball ball`: Represents the moving ball.
- `Score score`: Keeps track of player scores.

#### Key Methods
- `newBall()`: Creates a new ball at a random position.
- `newPaddle()`: Initializes both paddles at the correct position.
- `paint(Graphics g)`: Handles rendering the game elements.
- `move()`: Moves the paddles and ball.
- `checkCollision()`: Detects collisions and adjusts movement accordingly.
- `run()`: The main game loop, updating movement and repainting the screen.

### 2. `Paddle` Class
The `Paddle` class extends `Rectangle` and manages player-controlled paddles.

#### Key Features
- Moves based on player input.
- Stops at screen boundaries.
- Draws itself on the game window.

#### Important Variables
- `int id`: Identifies the paddle (Player 1 or Player 2).
- `int yVelocity`: Controls vertical movement.
- `int speed`: Defines paddle movement speed.

#### Key Methods
- `keyPressed(KeyEvent e)`: Moves the paddle when a key is pressed.
- `keyReleased(KeyEvent e)`: Stops movement when the key is released.
- `move()`: Updates paddle position.
- `draw(Graphics g)`: Renders the paddle.

### 3. `Ball` Class
The `Ball` class extends `Rectangle` and manages the ball’s movement.

#### Key Features
- Moves in a random direction at the start.
- Bounces off the paddles and screen edges.
- Increases speed after each paddle hit.

#### Important Variables
- `Random random`: Generates random movement directions.
- `int xVelocity, yVelocity`: Controls ball speed.
- `int initial_speed`: Sets the starting speed of the ball.

#### Key Methods
- `setXDirection(int direction)`: Updates horizontal velocity.
- `setYDirection(int direction)`: Updates vertical velocity.
- `move()`: Moves the ball based on velocity.
- `draw(Graphics g)`: Renders the ball.

### 4. `Score` Class
The `Score` class keeps track of player scores and displays them on the screen.

#### Key Features
- Increments score when a player misses the ball.
- Displays scores at the top of the game window.

#### Important Variables
- `int player1, player2`: Stores player scores.

#### Key Methods
- `draw(Graphics g)`: Displays the current scores.

## Controls
- **Player 1 (Left Paddle)**:
  - Move Up: `W`
  - Move Down: `S`
- **Player 2 (Right Paddle)**:
  - Move Up: `UP ARROW`
  - Move Down: `DOWN ARROW`

## How the Game Works
1. Players move their paddles to hit the ball.
2. The ball bounces off paddles and walls.
3. If a player misses the ball, the opponent gains a point.
4. The game continues indefinitely.

## Future Improvements
- Add a single-player mode with AI.
- Implement different difficulty levels.
- Add sound effects for paddle and ball interactions.

## Troubleshooting
- If controls are unresponsive, make sure the game window is selected.
- If movement is too fast, adjust the `speed` variable in the `Paddle` and `Ball` classes.

## License
This project is open-source and available for modification and distribution.

