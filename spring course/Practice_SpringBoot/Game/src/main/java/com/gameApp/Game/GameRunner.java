package com.gameApp.Game;

public class GameRunner {
    SuperContraGame game;

    public GameRunner (SuperContraGame game){
        this.game = game;
    }
    public void run(){
        System.out.println("running  game:  " + game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}