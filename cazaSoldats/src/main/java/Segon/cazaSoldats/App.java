package Segon.cazaSoldats;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private int morts = 0;
	private int escapats = 0;
	GLabel marcador = new GLabel("Morts: "+morts+" Escapats: "+escapats);
	public int gameOver = 5;
	List<Soldat> soldats = new ArrayList();

    	/*MouseListener mouse = new MouseListener() {
    		public void mousePressed(MouseEvent e) {
    		}
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
    	};*/
	
	@Override
	public final void run() {
		setSize(AMPLADAPANTALLA, ALTURAPANTALLA);
		addMouseListeners();
		clicaPerComencar();
		Run runner = new Run(this, gameOver, soldats, AMPLADAPANTALLA);
		runner.juga();
	}
    
	/**
	 * Clica per començar.
	 */
	private void clicaPerComencar() {
		GLabel label = new GLabel("Clica per començar");
		double x = (AMPLADAPANTALLA - label.getWidth()) / 2;
		double y = (ALTURAPANTALLA + label.getAscent()) / 2;
		add(marcador, x, 0); // Actualiza al hacer marcador.setLabel() 
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
		int crear = rand.nextInt(1);
		boolean[] amic = {true, false};
		GImage[] imatges = {new GImage("soldadobueno.png"), new GImage("soldadomalo.png")};
		double[] aparicioX = { 0 - imatges[crear].getWidth(), AMPLADAPANTALLA + imatges[crear].getWidth()};
		
		Soldat soldat = new Soldat(amic[crear], aparicioX[rand.nextInt(1)], 
				rand.nextInt((int) (ALTURAPANTALLA - imatges[crear].getHeight())), imatges[crear]);
		
		add(soldat.getImatge(), soldat.getX(), soldat.getY());
		
		return soldat;
	}
	
    public void mousePressed(MouseEvent e) {
		
	}
    
}
