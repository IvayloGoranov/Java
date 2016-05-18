package com.caveofprogramming.designpatterns.demo1.controller;

import java.sql.SQLException;
import java.util.List;

import com.caveofprogramming.designpatterns.demo1.model.DAOFactory;
import com.caveofprogramming.designpatterns.demo1.model.Database;
import com.caveofprogramming.designpatterns.demo1.model.Model;
import com.caveofprogramming.designpatterns.demo1.model.Person;
import com.caveofprogramming.designpatterns.demo1.model.PersonDAO;
import com.caveofprogramming.designpatterns.demo1.view.AppListener;
import com.caveofprogramming.designpatterns.demo1.view.CreateUserEvent;
import com.caveofprogramming.designpatterns.demo1.view.CreateUserListener;
import com.caveofprogramming.designpatterns.demo1.view.SaveListener;
import com.caveofprogramming.designpatterns.demo1.view.View;

public class Controller implements CreateUserListener, SaveListener,
		AppListener {
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void onUserCreated(CreateUserEvent event) {
		model.addPerson(new Person(event.getName(), event.getPassword()));
	}

	@Override
	public void onSave() {
		try {
			model.save();
		} catch (Exception e) {
			view.showError("Error saving to database.");
		}
	}

	@Override
	public void onOpen() {
		try {
			Database.getInstance().connect();
		} catch (Exception e) {
			view.showError("Cannot connect to database.");
		}
		
		try {
			model.load();
		} catch (Exception e) {
			view.showError("Error loading data from database.");
		}
	}

	@Override
	public void onClose() {
		Database.getInstance().disconnect();
	}

}
