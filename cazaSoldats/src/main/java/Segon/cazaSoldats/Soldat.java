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
	boolean mort = false;

	public Soldat(boolean amic2, GImage img, int d){
		amic = amic2;
		x = (int) img.getX();
		y = (int) img.getY();
		imatge = img;
		imatge.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// Crear boolean "mort" y luego en run mirar si está en true y hacer pantalla.remove(imagen)
				
				mort = true;
		    }
		});
		movilitat *= d;
		//Añadir mouse listener a la imagen, tener en cuenta que al guardar
		// y abrir el programa hay que volver a poner el mouse listener.
		// Añadir GImage al Cargar el programa si hay datos guardados
	}
	
	public void moure(){
		imatge.move(movilitat, 0);
	}
	
	

	public boolean getAmic() {
		return amic;
	}

	public void setAmic(boolean amic) {
		this.amic = amic;
	}

	public int getX() {
		return (int) imatge.getX();
	}

	public void setX() {
		this.x = (int) imatge.getX();
	}

	public int getY() {
		return (int) imatge.getY();
	}

	public void setY() {
		this.y = (int) imatge.getY();
	}

	public GImage getImatge() {
		return imatge;
	}

	public void setImatge(GImage imatge) {
		this.imatge = imatge;
		this.imatge.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// Crear boolean "mort" y luego en run mirar si está en true y hacer pantalla.remove(imagen)
				
				mort = true;
		    }
		});
	}
	public int getMovilitat() {
		return movilitat;
	}

	public void setMovilitat(int movilitat) {
		this.movilitat = movilitat;
	}
	
	public boolean getMort(){
		return mort;
	}


}
