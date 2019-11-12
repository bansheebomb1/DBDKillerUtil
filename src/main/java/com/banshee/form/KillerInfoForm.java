package com.banshee.form;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.net.URL;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.SwingDispatchService;

import com.banshee.listeners.action.ExitActionListener;
import com.banshee.listeners.action.ShowHideUtilActionListener;
import com.banshee.listeners.key.DecisiveStrikeTimerKeyListener;



public class KillerInfoForm extends JFrame implements WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8149278139449236886L;
	/** Logging */
	private static final Logger logger = Logger.getLogger(KillerInfoForm.class.getName());
	private static KillerInfoForm k;
	private JLabel survLabel1;
	private JLabel survLabel2;
	private JLabel survLabel3;
	private JLabel survLabel4;
	private DecisiveStrikeTimerKeyListener list1;
	private DecisiveStrikeTimerKeyListener list2;
	private DecisiveStrikeTimerKeyListener list3;
	private DecisiveStrikeTimerKeyListener list4;
	private TrayIcon trayIcon;
	private KillerInfoForm()
	{

		setOptions();
		prepareFormBody();
		prepareTray();
		prepareListeners();
		setVisible(true);
		
	}
	public static KillerInfoForm getInstance() {
		if (k == null) {
			k = new KillerInfoForm();
		}
		return k;
	}

	public boolean getKillerInfoFormVisibility() {
		return this.isVisible();
	}

	public void setKillerInfoFormVisibility(boolean isVisible) {
		this.setVisible(isVisible);
	}
	
	private void prepareListeners() {
		GlobalScreen.setEventDispatcher(new SwingDispatchService());
		addWindowListener(this);
		list1 = new DecisiveStrikeTimerKeyListener("1", survLabel1);
		list2 = new DecisiveStrikeTimerKeyListener("2", survLabel2);
		list3 = new DecisiveStrikeTimerKeyListener("3", survLabel3);
		list4 = new DecisiveStrikeTimerKeyListener("4", survLabel4);
		GlobalScreen.addNativeKeyListener(list1);
		GlobalScreen.addNativeKeyListener(list2);
		GlobalScreen.addNativeKeyListener(list3);
		GlobalScreen.addNativeKeyListener(list4);
	}


	private void prepareFormBody() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sizex = (int)(screenSize.getWidth() * 0.0435);

		//form body
		survLabel1 = prepareTimeLabel(1);
		survLabel2 = prepareTimeLabel(2);
		survLabel3 = prepareTimeLabel(3);
		survLabel4 = prepareTimeLabel(4); 

		setBackground(new Color(0, 0, 0, 0));
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addComponent(survLabel1, GroupLayout.DEFAULT_SIZE, sizex, Short.MAX_VALUE)
						.addComponent(survLabel2, GroupLayout.DEFAULT_SIZE, sizex, Short.MAX_VALUE)
						.addComponent(survLabel3, GroupLayout.DEFAULT_SIZE, sizex, Short.MAX_VALUE)
						.addComponent(survLabel4, GroupLayout.DEFAULT_SIZE, sizex, Short.MAX_VALUE)

		);
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(survLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
				.addComponent(survLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
				.addComponent(survLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
				.addComponent(survLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE));

		pack();
		
	}

	public void windowClosed(WindowEvent e) {
		// Clean up the native hook.
		try {
			GlobalScreen.unregisterNativeHook();
			this.dispose();
		}
		catch (NativeHookException ex) {
			ex.printStackTrace();
		}
		System.runFinalization();
		System.exit(0);
	}
	
	private void setOptions() {
		
		//options
		this.setType(Type.UTILITY);
		this.setUndecorated(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) (screenSize.getWidth() * 0.03), (int)(screenSize.getHeight() - 33));
		
	}
	
	
	private void prepareTray() {
		if (!SystemTray.isSupported()) {
			System.out.println("Your pc does not support system tray");
			System.exit(1);
			return;
		}
		// FRAME FOR RIGHT CLICK POP UP
		final Frame frame = new Frame("");
		frame.setUndecorated(true);
		frame.setType(Type.UTILITY);

		final SystemTray tray = SystemTray.getSystemTray();
		final PopupMenu popup = new PopupMenu();
		trayIcon = new TrayIcon(createIcon("/images/tray.GIF", "Tray Icon"));

		// ADD components
		// CREATE Show Hide Clock Action Listener
		MenuItem showHideClock = new MenuItem("Show/Hide Util");
		showHideClock.addActionListener(new ShowHideUtilActionListener() );

		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ExitActionListener(trayIcon));

		popup.add(showHideClock);
		popup.addSeparator();
		popup.add(exitItem);

		// ADD POPUP MENU TO TRAYICON
		trayIcon.setPopupMenu(popup);

		// SET TOOLTIP
		trayIcon.setToolTip("KillerInfoUtil v1.0");

		try {
			tray.add(trayIcon);
		} catch (AWTException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	private JLabel prepareTimeLabel(int survNum) {
		JLabel survLabel = new JLabel();
		survLabel.setName("SurvLabel" + survNum);
		survLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
		survLabel.setForeground(Color.WHITE);
		survLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		survLabel.setText("60 sec");
		survLabel.setOpaque(true);
		survLabel.setBackground(new Color(0, 0, 0, 65));
		//survLabel.setSize(60, 33);

		return survLabel;
	}
	
	private Image createIcon(String path, String desc) {
		URL imageURL = KillerInfoForm.class.getResource(path);
		return (new ImageIcon(imageURL, desc)).getImage();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
