/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.business;

import java.util.ArrayList;

public class Player {
    private char color;
    private String name;
    private ArrayList pieces;
    private King king;

    public Player(String name, char color){

    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setColor(char color){
        this.color=color;
    }
    public char getColor(){
        return this.color;
    }
    public King getKing(){
        return this.king;
    }
    public void addPiece(Piece piece){

    }
    public ArrayList getPiece(){

    }
}
}
