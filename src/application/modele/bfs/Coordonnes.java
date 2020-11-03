package application.modele.bfs;

public class Coordonnes {
	private int x,y;
	
	public Coordonnes (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordonnes other = (Coordonnes) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public boolean estAdjacent(Coordonnes coord) {
		if(this.x - 32 == coord.x && this.y == coord.y) {
			return true;
		}
		if(this.x + 32 == coord.x && this.y == coord.y) {
			return true;
		}
		if(this.x  == coord.x && this.y - 32 == coord.y) {
			return true;
		}
		if(this.x  == coord.x && this.y + 32 == coord.y) {
			return true;
		}
		return false;
	}
}
