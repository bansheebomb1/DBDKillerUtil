package com.banshee.DBDKillerUtil.listeners.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.Timer;


public class SurvivalLabelTimerActionListener implements ActionListener {


	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(SurvivalLabelTimerActionListener.class.getName());
	private int currentTicker;
	private JLabel survivorLabel;
	private Timer countdown;
	private String survNum;
	private Color inprog = new Color(255,0,0,55);
	private Color done = new Color(0,255,0,55);
	
	public SurvivalLabelTimerActionListener(String survNum, JLabel survivorLabel) {
		this.survNum = survNum;
		this.currentTicker = 60;
		this.survivorLabel = survivorLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		currentTicker = currentTicker - 1;
		survivorLabel.setBackground(inprog);
		survivorLabel.setText(currentTicker + " sec");

		
		if(currentTicker <= 0)
		{
			logger.log(Level.INFO, "Stopping Timer for: "+survNum);
			if(countdown !=null)
			{
				
				countdown.stop();
			}
			else
			{
				((Timer) e.getSource()).stop();
			}
			survivorLabel.setBackground(done);
		}
		survivorLabel.getParent().repaint();
	}
	
	public void resetTicker()
	{
		this.currentTicker = 60;
	}

	public void setTimer(Timer countdown) {
		this.countdown = countdown;
		
	}

}
