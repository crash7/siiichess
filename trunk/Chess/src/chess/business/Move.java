package chess.business;

public class Move {
	private Position source;
	private Position destination;

	public Move() {

	}

	public Move(Position source, Position destination) {
		this.source = source;
		this.destination = destination;
		
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
