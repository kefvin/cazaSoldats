package Segon.cazaSoldats;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Hello world!
 *
 */
public class App extends GraphicsProgram
{
	private static final int AMPLADAPANTALLA = 1024;
	private static final int ALTURAPANTALLA = 644;
	private static final int AMPLADASOLDAT = 170;
	private static final int ALTURASOLDAT = 200;
	private int morts = 0;
	private int escapats = 0;
	GLabel marcador = new GLabel("Morts: "+morts+" Escapats: "+escapats);
	public int gameOver = 5;
	List<Soldat> soldats = new ArrayList();
	Run runner = null;
	Marcador resultat = new Marcador();
	
	
	/*
	 * Marcador a 0
	 * 
	 */

	@Override
	public final void run() {
		setSize(AMPLADAPANTALLA, ALTURAPANTALLA);
		addMouseListeners();
		addKeyListeners();
		
		buscaPartidaGuardada();
		runner = new Run(this, gameOver, soldats, AMPLADAPANTALLA, AMPLADASOLDAT);
		clicaPerComencar();
		runner.juga();
		
		GLabel label = new GLabel("Fi del joc!");
		double x = (AMPLADAPANTALLA - label.getWidth()) / 2;
		double y = (ALTURAPANTALLA + label.getAscent()) / 2;
		add(label, x, y);
	}

	/**
	 * Clica per començar.
	 */
	private void clicaPerComencar() {
		GLabel label = new GLabel("Clica per començar");
		double x = (AMPLADAPANTALLA - label.getWidth()) / 2;
		double y = (ALTURAPANTALLA + label.getAscent()) / 2;
		add(marcador, x,10); // Actualiza al hacer marcador.setLabel() 
		add(label, x, y);
		waitForClick();
		remove(label);
		marcador.setLabel("Morts: "+morts+" Escapats: "+escapats);
	}

	public int actualitzarMarcadorEscapat(){
		escapats++;
		marcador.setLabel("Morts: "+morts+" Escapats: "+escapats);
		return escapats;
	}

	public int actualitzarMarcadorMorts(){
		morts++;
		marcador.setLabel("Morts: "+morts+" Escapats: "+escapats);
		return morts;
	}

	public Soldat generaSoldat(){
		Random rand = new Random();
		int crear = rand.nextInt(2);
		boolean[] amic = {true, false};
		boolean amicSegur = amic[crear];
		GImage imatge = null;
		int direccio;
		double[] aparicioX = { (0 - AMPLADASOLDAT), (AMPLADAPANTALLA)};
		int aparicio = (int) aparicioX[rand.nextInt(2)];

		if(amicSegur){
			imatge = new GImage("soldadobueno.png");
		}else{
			imatge = new GImage("soldadomalo.png");
		}

		int y = rand.nextInt((int) (ALTURAPANTALLA - imatge.getHeight()));
		imatge.setLocation(aparicio, y);
		
		if(aparicio < 0){
			direccio = 1;
		}else{
			direccio = -1;
		}

		Soldat soldat = new Soldat(amicSegur, imatge, direccio, AMPLADASOLDAT, ALTURASOLDAT);

		imatge.setSize(AMPLADASOLDAT, ALTURASOLDAT);
		add(soldat.getImatge(), soldat.getX(), soldat.getY());

		return soldat;
	}
	
//	@SuppressWarnings("unchecked")
	private void buscaPartidaGuardada() {
		try {
//			ObjectInputStream input = recuperaArxius("guardaSoldats");
//			soldats = (List<Soldat>) input.readObject();
//			input = recuperaArxius("guardaMarcador");
//			resultat = (Marcador) input.readObject();
			
			File guardaSoldats = new File("guardaSoldats");
			File guardaMarcador = new File("guardaMarcador");
			
			if(guardaSoldats.exists() && guardaMarcador.exists()){
				
			FileInputStream arxiuSoldats = new FileInputStream("guardaSoldats");
			ObjectInputStream inSoldats = new ObjectInputStream(arxiuSoldats);
			soldats = (List<Soldat>) inSoldats.readObject();
			arxiuSoldats.close();
			guardaSoldats.delete();
			
			FileInputStream arxiuMarcador = new FileInputStream("guardaMarcador");
			ObjectInputStream inMarcador = new ObjectInputStream(arxiuMarcador);
			resultat = (Marcador) inMarcador.readObject();
			arxiuMarcador.close();
			guardaMarcador.delete();
			
			reescriuMarcador();
			
			GImage imatge;
			for(Soldat soldat : soldats){
				if(soldat.getAmic()){
					imatge = new GImage("soldadobueno.png");
				}else{
					imatge = new GImage("soldadomalo.png");
				}
				soldat.setImatge(imatge);
				add(soldat.getImatge());
			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


//	private ObjectInputStream recuperaArxius(String nomArxiu){
//		try {
//			File guardaSoldats = new File(nomArxiu);
//			System.out.println(guardaSoldats.getAbsolutePath());
//			ObjectInputStream inSoldats = null;
//			if(guardaSoldats.exists()){
//			FileInputStream arxiuSoldats = new FileInputStream(nomArxiu);
//			inSoldats = new ObjectInputStream(arxiuSoldats);
//			arxiuSoldats.close();
//			guardaSoldats.delete();
//		}
//		return inSoldats;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null
//	}

	private void reescriuMarcador() {

		morts = resultat.getMorts();
		escapats = resultat.getEscapats();
	}

	public void save(){
		try {
			FileOutputStream arciuSoldats = new FileOutputStream("guardaSoldats");
			ObjectOutputStream guardaSoldats  = new ObjectOutputStream(arciuSoldats);
			guardaSoldats.writeObject(soldats);
			FileOutputStream arciuMarcador = new FileOutputStream("guardaMarcador");
			ObjectOutputStream guardaMarcador  = new ObjectOutputStream(arciuMarcador);
			guardaMarcador.writeObject(resultat);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		
		switch( keyCode ) { 
		case KeyEvent.VK_S:
			runner.actualiza();
			actualitzaMarcador();
			save();
			System.exit(0); // Cierra el programa
			break;		
		case KeyEvent.VK_X:
			System.exit(0); // Cierra el programa
			break;
			
		}
	}

	private void actualitzaMarcador() {

		resultat.setMorts(morts);
		resultat.setEscapats(escapats);		
	}

}
