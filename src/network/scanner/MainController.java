package network.scanner;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observer;

import network.scanner.Calculate;
import network.scanner.Scan;
import network.scanner.MainWindow;

public class MainController {
	private Observer observerObject;
	public MainController(){
		observerObject = new MainWindow(this);
	}
	public void startScan(String from,String to) throws UnknownHostException, IOException{
		new Calculate(from, to, this);
		Scan s = new Scan();
		s.scan();
		
	}
	public Observer getObserver(){
		return this.observerObject;
	}
	

}
