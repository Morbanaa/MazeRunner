import java.util.Scanner;

/*
 * ============================================
 *              MAZE RUNNER
 *           Morbanaa Studios
 *              By Teddy Rodd
 * --------------------------------------------
 * A terminal-based maze game where the player
 * navigates through levels to reach the exit.
 *
 * Controls:
 *   W - Move Up
 *   S - Move Down
 *   A - Move Left
 *   D - Move Right
 *   Q - Quit Game
 * ============================================
 */

public class Main{
    public static void main(String[]args){
        // Starts Program
        game_loop();
    }

    /*
     * ============================================
     *               GAME LOOP
     * --------------------------------------------
     * Handles:
     * - Player input
     * - Player movement
     * - Level progression
     * - Win / Quit conditions
     * ============================================
     */

    public static void game_loop(){
        int game_level = 1;
        Scanner scanner = new Scanner(System.in);
        char [][] game_map = pick_map(game_level);
        int player_x_pos = 1;
        int player_y_pos = 1;
        int victory = 0;
        int moves = 0;

        ClearScreen();
        display_data(game_level,moves);
        game_map = pick_map(game_level);
        draw_map(game_map,player_y_pos,player_x_pos);

        boolean running = true;
        while(running){
            String choice = "";
            System.out.print("Enter (W,S,A,D) to move (Q to Quit): ");
            choice = scanner.nextLine();
            moves ++;

            int [] player_data = update_player(game_map, choice, player_y_pos, player_x_pos, game_level, victory);
            player_y_pos = player_data[0];
            player_x_pos = player_data[1];
            game_level = player_data[2];
            victory = player_data[3];

            ClearScreen();
            if (victory == 1 || choice.equalsIgnoreCase("Q") || choice.equalsIgnoreCase("QUIT")){
                if (victory == 1){
                    System.out.print("Well done! You have won!\n");
                }
                else{
                    System.out.print("Thanks for playing!");
                }

                break;
            }
            display_data(game_level,moves);
            game_map = pick_map(game_level);
            draw_map(game_map,player_y_pos,player_x_pos);
        }

    }


    public static void display_data(int game_level, int moves){
        System.out.println("Maze Runner!\nMorbanaa Studios\n-----------------");
        System.out.print("Level: " + game_level + "\n");
        System.out.print("Moves: " + moves + "\n");
    }


    public static int [] update_player(char[][] game_map, String choice, int player_y_pos, int player_x_pos, int game_level,int victory){

        if(choice.equalsIgnoreCase("W")){
            if (game_map[player_y_pos - 1][player_x_pos] == ' ' || game_map[player_y_pos - 1][player_x_pos] == 'E'){
                player_y_pos --;
            }
        }
        else if(choice.equalsIgnoreCase("S")){
            if (game_map[player_y_pos + 1][player_x_pos] == ' ' || game_map[player_y_pos + 1][player_x_pos] == 'E' ){
                player_y_pos ++;
            }
        }
        else if(choice.equalsIgnoreCase("A")){
            if (game_map[player_y_pos][player_x_pos - 1] == ' ' || game_map[player_y_pos][player_x_pos - 1] == 'E' ) {
                player_x_pos --;
            }
        }
        else if(choice.equalsIgnoreCase("D")){
            if (game_map[player_y_pos][player_x_pos + 1] == ' ' || game_map[player_y_pos][player_x_pos + 1] == 'E' ) {
                player_x_pos ++;
            }
        }

        int map_height = game_map.length;
        int map_length = game_map[0].length;
        for (int y = 0; y < map_height; y ++){
            for (int x = 0; x < map_length; x ++){
                if (game_map[player_y_pos][player_x_pos] == 'E'){
                    game_level ++;
                    if (game_level > 6){
                        game_level = 6;
                        victory = 1;
                    }
                    player_y_pos = 1;
                    player_x_pos = 1;
                    break;
                }
            }
        }

        return new int[]{player_y_pos,player_x_pos,game_level, victory};
    }


    public static void draw_map(char[][] game_map, int player_y_pos, int player_x_pos){
        int map_height = game_map.length;
        int map_length = game_map[0].length;

        for (int y = 0; y < map_height; y ++){
            for (int x = 0; x < map_length; x ++){
                if (y == player_y_pos && x == player_x_pos){
                    System.out.print(BRIGHT_CYAN + "P" + RESET);
                }
                else if (game_map[y][x] == 'E'){
                    System.out.print(BRIGHT_RED + game_map[y][x] + RESET);
                }
                else if (game_map[y][x] == '@') {
                    System.out.print(BRIGHT_YELLOW + game_map[y][x] + RESET);
                }
                else {
                    System.out.print(game_map[y][x]);
                }
            }
            System.out.println();
        }

    }


    public static char [][] pick_map(int game_level){
        char [][] map_level_one = {
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','E','@'},
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'}
        };
        char [][] map_level_two = {
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ','@',' ',' ',' ','@',' ',' ',' ','@',' ',' ',' ','@',' ','E','@'},
                {'@',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@','@'},
                {'@',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@','@'},
                {'@',' ',' ',' ','@',' ',' ',' ','@',' ',' ',' ','@',' ',' ',' ','@','@'},
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'}
        };
        char [][] map_level_three = {
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ','@',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','E','@'},
                {'@',' ','@','@','@','@',' ','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ','@','@','@','@',' ','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ',' ',' ',' ',' ','@'},
                {'@','@',' ','@','@','@',' ','@','@','@',' ','@','@','@','@','@',' ','@'},
                {'@','@',' ','@','@','@',' ','@','@','@',' ','@','@','@','@','@',' ','@'},
                {'@',' ',' ',' ',' ','@',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ','@'},
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'}
        };
        char [][] map_level_four = {
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ',' ',' ','@',' ',' ',' ','@',' ',' ',' ',' ','@',' ',' ',' ','@'},
                {'@','@','@',' ','@',' ','@',' ','@',' ','@','@',' ','@','@','@',' ','@'},
                {'@',' ','@',' ','@',' ','@',' ','@',' ','@','@','@','@','@','@',' ','@'},
                {'@',' ',' ',' ',' ',' ','@',' ',' ',' ',' ','@',' ',' ',' ',' ',' ','@'},
                {'@','@','@','@',' ','@','@',' ','@','@','@','@',' ','@','@','@','@','@'},
                {'@',' ',' ',' ',' ',' ','@',' ','@','@','@','@',' ','@','@','@','@','@'},
                {'@','@','@','@','@',' ',' ',' ',' ',' ',' ',' ',' ','@','E',' ',' ','@'},
                {'@',' ',' ',' ',' ',' ','@','@',' ','@','@',' ','@','@','@','@',' ','@'},
                {'@','@','@','@','@',' ','@','@',' ','@','@',' ','@','@','@','@',' ','@'},
                {'@',' ',' ',' ',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ',' ',' ','@'},
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'}
        };
        char [][] map_level_five = {
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ','@',' ',' ',' ','@','@','@',' ',' ',' ',' ',' ',' ','@','E','@'},
                {'@',' ','@',' ','@',' ',' ',' ','@',' ','@','@','@','@',' ','@',' ','@'},
                {'@',' ',' ',' ','@','@','@',' ','@',' ',' ',' ',' ','@',' ','@',' ','@'},
                {'@','@','@',' ','@',' ','@',' ','@',' ','@','@',' ','@','@','@',' ','@'},
                {'@',' ',' ',' ','@',' ','@',' ',' ',' ',' ','@',' ','@',' ',' ',' ','@'},
                {'@',' ','@','@','@',' ','@','@','@','@',' ','@',' ',' ',' ','@','@','@'},
                {'@',' ','@',' ',' ',' ','@',' ',' ',' ',' ','@','@','@','@','@',' ','@'},
                {'@',' ','@',' ','@','@','@',' ','@','@','@','@',' ',' ','@',' ',' ','@'},
                {'@',' ',' ',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ','@',' ',' ','@'},
                {'@','@','@',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@','@',' ','@'},
                {'@',' ',' ',' ','@',' ','@',' ','@',' ','@',' ','@',' ','@',' ',' ','@'},
                {'@',' ','@','@','@',' ','@',' ',' ',' ','@',' ','@',' ','@',' ','@','@'},
                {'@',' ','@',' ',' ',' ','@','@','@',' ',' ',' ','@',' ',' ',' ',' ','@'},
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'}
        };
        char [][] map_level_six = {
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'},
                {'@',' ','@',' ',' ',' ',' ',' ',' ','@',' ',' ',' ',' ','@',' ',' ',' ','@',' ',' ',' ',' ',' ','@','@','@','@',' ',' ',' ','@','@',' ',' ','E','@'},
                {'@',' ','@',' ','@',' ','@','@',' ','@',' ','@','@','@','@',' ','@',' ','@',' ','@',' ','@',' ',' ',' ',' ','@',' ','@',' ',' ',' ',' ','@',' ','@'},
                {'@',' ','@',' ','@',' ','@',' ',' ','@',' ','@',' ',' ',' ',' ','@',' ','@',' ','@','@','@','@','@',' ',' ','@',' ','@','@','@','@',' ','@','@','@'},
                {'@',' ',' ',' ','@',' ','@',' ',' ','@',' ',' ',' ','@','@',' ','@',' ','@',' ',' ',' ',' ',' ',' ','@',' ','@',' ','@',' ',' ','@',' ','@',' ','@'},
                {'@','@','@',' ','@',' ','@',' ','@','@',' ','@',' ',' ','@',' ','@','@','@','@','@',' ','@','@',' ','@',' ',' ',' ',' ',' ',' ',' ',' ','@',' ','@'},
                {'@',' ',' ',' ','@',' ','@',' ',' ',' ',' ','@',' ',' ',' ',' ','@',' ',' ',' ','@',' ','@',' ',' ','@',' ','@','@','@','@',' ','@',' ','@',' ','@'},
                {'@',' ','@','@','@',' ','@','@','@',' ','@','@','@','@','@',' ','@',' ',' ',' ',' ',' ','@',' ',' ','@',' ','@',' ',' ','@',' ','@',' ','@',' ','@'},
                {'@',' ','@',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ','@',' ',' ',' ',' ','@','@','@',' ','@','@',' ','@',' ','@',' ','@'},
                {'@','@','@',' ','@','@','@',' ','@',' ','@','@',' ','@','@','@','@',' ',' ',' ','@','@',' ','@','@','@',' ','@',' ',' ',' ',' ',' ',' ','@',' ','@'},
                {'@',' ',' ',' ',' ',' ','@',' ',' ','@',' ','@',' ','@',' ',' ','@','@','@','@','@','@',' ',' ',' ',' ',' ',' ','@',' ','@',' ',' ',' ','@',' ','@'},
                {'@',' ','@',' ',' ',' ','@',' ',' ','@',' ','@',' ',' ',' ',' ',' ',' ','@',' ',' ','@','@','@','@',' ','@',' ',' ','@','@','@',' ','@','@',' ','@'},
                {'@',' ','@','@','@',' ','@','@',' ','@',' ',' ',' ',' ','@','@','@',' ','@',' ',' ','@',' ',' ',' ',' ','@','@',' ',' ','@',' ',' ',' ',' ',' ','@'},
                {'@',' ',' ','@',' ',' ',' ',' ',' ','@',' ','@','@','@','@',' ','@',' ','@',' ','@','@',' ',' ',' ',' ','@','@','@',' ','@',' ','@','@','@','2','@'},
                {'@',' ',' ','@',' ','@','@','@',' ','@',' ','@',' ',' ',' ',' ','@',' ','@',' ','@','@',' ','@','@','@','@',' ','@',' ','@',' ','@','@',' ',' ','@'},
                {'@',' ',' ','@',' ','@',' ',' ',' ','@',' ','@',' ',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','@',' ',' ',' ',' ',' ','@'},
                {'@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@','@'}
        };

        switch(game_level){
            case 1: return map_level_one;
            case 2: return map_level_two;
            case 3: return map_level_three;
            case 4: return map_level_four;
            case 5: return map_level_five;
            case 6: return map_level_six;
            default: throw new IllegalArgumentException("Invalid game level: ");
        }
    }


    // Moves the screen down
    public static void ClearScreen(){
        for (int i = 0; i <= 50; i++){
            System.out.println();
        }
        // If this is not here it can cause issues with scanner.Nextline()
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
    }


    // Changes String Color
    public static final String RESET  = "\u001B[0m";
    public static final String BRIGHT_RED    = "\u001B[91m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_CYAN   = "\u001B[96m";

}