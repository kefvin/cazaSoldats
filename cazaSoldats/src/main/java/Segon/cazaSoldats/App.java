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
	
	
	/*
	 * No se crean correctamente en la X y la Y, no se guarda el arcador y no se borra el archivo
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
		imatge.setSize(AMPLADASOLDAT, ALTURASOLDAT);
		
		if(aparicio < 0){
			direccio = 1;
		}else{
			direccio = -1;
		}

		Soldat soldat = new Soldat(amicSegur, imatge, direccio);

		add(soldat.getImatge(), soldat.getX(), soldat.getY());

		return soldat;
	}
	
	private void buscaPartidaGuardada() {
		try {
			File esta = new File("guardat");
			System.out.println(esta.getAbsolutePath());
			if(esta.exists()){
			FileInputStream archivo = new FileInputStream("guardat");
			ObjectInputStream in = new ObjectInputStream(archivo);
			soldats = (List<Soldat>) in.readObject();
			
			GImage imatge;
			for(Soldat soldat : soldats){
				if(soldat.getAmic()){
					imatge = new GImage("soldadobueno.png");
				}else{
					imatge = new GImage("soldadomalo.png");
				}
				imatge.setSize(AMPLADASOLDAT, ALTURASOLDAT);
				soldat.setImatge(imatge);
				add(soldat.getImatge(), soldat.getX(), soldat.getY());
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
	
	public void save(){
		try {
			FileOutputStream archivo = new FileOutputStream("guardat");
			ObjectOutputStream guarda  = new ObjectOutputStream(archivo);
			guarda.writeObject(soldats);
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
			save();
			System.exit(0); // Cierra el programa
			break;		
		case KeyEvent.VK_X:
			//fitxerCavallers.delete();
			//fitxerContador.delete();
			System.exit(0); // Cierra el programa
			break;
		}
	}

}
