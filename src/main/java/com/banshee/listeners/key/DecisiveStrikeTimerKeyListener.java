package com.banshee.listeners.key;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.Timer;

import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.banshee.listeners.action.SurvivalLabelTimerActionListener;


public class DecisiveStrikeTimerKeyListener implements NativeKeyListener {

	/** Logging */
	private static final Logger logger = Logger.getLogger(DecisiveStrikeTimerKeyListener.class.getName());
	private String survNumber;
	private Timer countdown;
	private SurvivalLabelTimerActionListener timerListener;
	private JLabel survLabel;
	
	
	public DecisiveStrikeTimerKeyListener(String survNum, JLabel survLabel)
	{
		this.survNumber = survNum;
		this.survLabel = survLabel;
		timerListener = new SurvivalLabelTimerActionListener(survNum,survLabel);
		countdown = new Timer(1000, timerListener);
		timerListener.setTimer(countdown);
	}
	

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeEvent) {		
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
		if(survNumber.equals(NativeKeyEvent.getKeyText(nativeEvent.getKeyCode())))
		{
			displayEventInfo(nativeEvent);
			if(countdown != null)
			{
				logger.log(Level.INFO, "Kicking off timer for label" + (survLabel !=  null ? survLabel.getName() : "null"));
				timerListener.resetTicker();
				countdown.restart();
			}

		}

		
	}
	
	private void displayEventInfo(final NativeInputEvent e) {
		logger.log(Level.INFO, e.paramString());
	}
}
