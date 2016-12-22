package Segon.cazaSoldats;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import acm.graphics.GImage;

public class Soldat implements Serializable{

	boolean amic;
	int x;
	int y;
	transient GImage imatge;
	int movilitat = 10;
	
	

	public Soldat(boolean amic2, double aparicioX, int i, GImage img){
		amic = amic2;
		x = (int) aparicioX;
		y = i;
		imatge = img;
		imatge.addMouseListener(new MouseAdapter(){
			public void mousePresed(MouseEvent e) {
				// Crear boolean "mort" y luego en run mirar si está en true y hacer pantalla.remove(imagen)
		    }
		});
		//Añadir mouse listener a la imagen, tener en cuenta que al guardar
		// y abrir el programa hay que volver a poner el mouse listener.
	}
	
	

	public boolean isAmic() {
		return amic;
	}

	public void setAmic(boolean amic) {
		this.amic = amic;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public GImage getImatge() {
		return imatge;
	}

	public void setImatge(GImage imatge) {
		this.imatge = imatge;
	}
	public int getMovilitat() {
		return movilitat;
	}

	public void setMovilitat(int movilitat) {
		this.movilitat = movilitat;
	}

}
