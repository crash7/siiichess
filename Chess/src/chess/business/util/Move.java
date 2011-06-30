/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.business.util;


public class Move {
    private Position source;
    private Position destination;
    public Move(){

    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public Position getSource() {
        return source;
    }

    public void setSource(Position source) {
        this.source = source;
    }
    

}
