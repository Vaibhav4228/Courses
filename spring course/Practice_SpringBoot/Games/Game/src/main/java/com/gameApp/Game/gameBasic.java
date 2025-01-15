package com.gameApp.Game;

public class gameBasic {
    public static void main(String[] args) {

//        MarioGame marioGame = new MarioGame();
        var game = new SuperContraGame();
        GameRunner gameRunner = new GameRunner(game);
        gameRunner.run();
    }
}
