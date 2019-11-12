package com.banshee.listeners.action;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActionListener implements ActionListener {

	private TrayIcon trayIcon;
	
	
	public ExitActionListener(TrayIcon trayIcon) {
		super();
		this.trayIcon = trayIcon;
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		SystemTray.getSystemTray().remove(trayIcon);
		System.exit(0);
	}

}
