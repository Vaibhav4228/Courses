package com.gameApp.Game;

public class gameBasic {
    public static void main(String[] args) {

//        MarioGame marioGame = new MarioGame();
        var superContraGame = new SuperContraGame();
        GameRunner gameRunner = new GameRunner(marioGame);
        gameRunner.run();
    }
}
