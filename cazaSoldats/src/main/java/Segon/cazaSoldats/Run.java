package Segon.cazaSoldats;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import acm.graphics.GImage;

public class Run {

	App pantalla;
	int gameOver;
	List<Soldat> soldats;
	int AMPLADAPANTALLA;
	int AMPLADASOLDAT;
	
	private int escapats = 0;
	
	public Run(App p, int gO, List<Soldat> s, int am, int amplSoldat) {
		pantalla = p;
		gameOver = gO;
		soldats = s;
		AMPLADAPANTALLA = am;
		AMPLADASOLDAT = amplSoldat;
	}
	
	public void juga(){
		while(escapats<gameOver){
			// Hacer un random para simular que salen cada x periodo de tiempo
			// si el rand es más grande que tal generar soldado, si no, no generar
			
			Random rand = new Random();
			if(rand.nextInt(100) > 90 ){
				soldats.add(pantalla.generaSoldat());
			}
			for(Iterator<Soldat> soldatt = soldats.iterator(); soldatt.hasNext(); ) {
				Soldat soldat = soldatt.next();
				soldat.moure();
				if (soldatEscapat(soldat) || comprovaSoldatClicat(soldat)) {
					soldatt.remove();
				}; // "Mata" els soldats si s'escapen
				
				pantalla.pause(30);
				// Crea la pausa
			}
		}
		// Poner mensage par cuando pierdes
	}
	
	public boolean soldatEscapat(Soldat soldat){
		if(soldat.getX() < 0 - AMPLADASOLDAT || soldat.getX() > AMPLADAPANTALLA){
			pantalla.remove(soldat.getImatge());
			// soldats.remove(soldat);
			if(!soldat.getAmic()){
				pantalla.actualitzarMarcadorEscapat();
			}
			return true;
		}
		return false;
	}
	
	public boolean comprovaSoldatClicat(Soldat soldat){
		if(soldat.getMort()){
			pantalla.remove(soldat.getImatge());
			//soldats.remove(soldat);
			if(soldat.getAmic()){
				escapats = 5;
			}else{
				pantalla.actualitzarMarcadorMorts();
			}
			return true;
		}
		return false;
	}

	public void actualiza() {
		
		for(Soldat soldat : soldats){
			soldat.setY();
			soldat.setX();
		}
		
	}
	
}
