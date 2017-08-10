package net.termer.flpluginsupport;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import net.termer.flps.FLPS;
import flands.FLApp;

public class Main {
	
	public static void main(String[] args) {
		try {
			Plugins.init();
			Plugins.load();
			Plugins.preInit();
			FLApp.main(args);
			FLPS.getGame().addWindowListener(new WindowListener() {
				public void windowOpened(WindowEvent e) {}
				public void windowClosing(WindowEvent e) {
					Plugins.disable();
				}
				public void windowClosed(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowActivated(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
			});
			Plugins.enable();
		} catch(Exception e) {
			System.err.println("Error while starting game");
			e.printStackTrace();
		} finally {
			System.out.println("Goodbye");
		}
	}
}