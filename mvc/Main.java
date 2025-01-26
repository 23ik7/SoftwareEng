package mvc;

import javafx.application.Application;
import mvc.presentation.MainFX;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread() {
			public void run() {
				MainFX.launch(MainFX.class, args);
			}
		};
		t.start();

	}
}
