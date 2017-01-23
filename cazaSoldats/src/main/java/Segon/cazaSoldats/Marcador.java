package Segon.cazaSoldats;

import java.io.Serializable;

public class Marcador implements Serializable{
	
	int morts = 0;
	int escapats = 0;
	
	public int getMorts() {
		return morts;
	}

	public void setMorts(int morts) {
		this.morts = morts;
	}

	public int getEscapats() {
		return escapats;
	}

	public void setEscapats(int escapats) {
		this.escapats = escapats;
	}

	public Marcador(){
		
	}

}
