package com.banshee.DBDKillerUtil.listeners.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.banshee.DBDKillerUtil.form.KillerInfoForm;

public class ShowHideUtilActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {
		KillerInfoForm.getInstance()
				.setKillerInfoFormVisibility(!KillerInfoForm.getInstance().getKillerInfoFormVisibility());
	}

}
