package Segon.cazaSoldats;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acm.graphics.GImage;

public class Run {

	App pantalla;
	int gameOver;
	List<Soldat> soldats;
	int AMPLADAPANTALLA;
	
	public Run(App p, int gO, List<Soldat> s, int am) {
		pantalla = p;
		gameOver = gO;
		soldats = s;
		AMPLADAPANTALLA = am;
	}
	
	public void juga(){
		int escapats = 0;
		while(escapats<gameOver){
			// Hacer un random para simular que salen cada x periodo de tiempo
			// si el rand es más grande que tal generar soldado, si no, no generar
			
			Random rand = new Random();
			if(rand.nextInt(100) > 80 ){
				soldats.add(pantalla.generaSoldat());
				for(Soldat soldat: soldats){
					if(soldat.getX()<0){
						mouSoldat(1, soldat);
						soldatEscapat(soldat); // "Mata" els soldats si s'escapen
					}else{
						mouSoldat(-1, soldat);
						soldatEscapat(soldat);
					}
				}
			}
		}
	}
	
	public void mouSoldat(int m, Soldat soldat){
		soldat.getImatge().move(soldat.getMovilitat()*m,0);
		soldat.setX(soldat.getX()+soldat.getMovilitat()*m);
	}
	
	public void soldatEscapat(Soldat soldat){
		if(soldat.getX() < 0 - soldat.getImatge().getWidth() || soldat.getX() > AMPLADAPANTALLA + soldat.getImatge().getWidth()){
			pantalla.remove(soldat.getImatge());
			soldats.remove(soldat);
		}
	}
	
}
