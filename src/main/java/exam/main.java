package exam;


import java.awt.desktop.SystemEventListener;
import java.util.Locale;
import java.util.Scanner;
import engine.GameProgress;
import engine.Parser;
import engine.ResourceHandler;
import gui.UIFrame;
import gui.UIHandler;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class main {

	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void main(String[] args) {
		//Locale.setDefault(Locale.ITALY);
		/*
		JFrame frame =  new UIFrame();
		((UIFrame) frame).printString("omfg");
		((UIFrame) frame).printString("HEAVEN KNOWS THAT I'M BORN TO LAY FOR THIS GIRLS");*/
		//ResourceHandler.loadResources();
		UIHandler.printInFrame("");
		GameProgress.newGame();
	}
}
