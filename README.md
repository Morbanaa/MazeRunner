# MazeRunner
============================================================

PROGRAM DESCRIPTION:
Maze Runner is a multi-level, terminal-based maze navigation
game where the player must find and reach the exit tile (E)
to progress through increasingly complex maze layouts.

The game features six handcrafted levels that increase in
size and difficulty. The player begins at position (1,1)
each level and must strategically navigate around walls
to reach the exit.

------------------------------------------------------------
OBJECTIVE:

- Navigate through each maze
- Reach the Exit tile (E) to advance
- Complete all 6 levels to win the game

Victory is achieved upon completing Level 6.

------------------------------------------------------------
CONTROLS:

W  - Move Up
S  - Move Down
A  - Move Left
D  - Move Right
Q  - Quit Game

Movement is blocked by walls (@).
You may only move onto:
- Empty spaces (' ')
- Exit tile ('E')

------------------------------------------------------------
GAME FEATURES:

- 6 Progressive handcrafted maze levels
- Move counter tracking total player moves
- Level tracking system
- Win condition detection
- Clean screen redraw between turns
- ANSI color rendering:
    Player (P)      - Bright Cyan
    Exit (E)       - Bright Red
    Walls (@)      - Bright Yellow

------------------------------------------------------------
TECHNICAL DETAILS:

- 2D char array map system
- Player position tracking (x, y)
- Dynamic map loading via pick_map()
- State updates returned using int[] data structure
- Scanner-based input handling
- Basic screen clearing method for terminal refresh

------------------------------------------------------------
Built With:
Java | Terminal Rendering | ANSI Escape Codes

============================================================
