package Controller;

import java.util.Scanner;

public class MainClass {
	
	public static void main(String[] args) {
		
		I_Controller ctrl = Controller.getInstance();
	
		ctrl.startGui();  // GUI is only for show it does not work
		//boolean run = true;
	//	ctrl.launchManageQuestionsScreen(); 
		
/*
		try {
			do {
				ctrl.launchIntro();
				switch (input.nextInt()) {
				case 1:
					ctrl.startGame(); // start game
					break;
				case 2:
					ctrl.launchHistoryScreen();// view history
					break;
				case 3:
					ctrl.launchManageQuestionsScreen(); // handle edit questions
					break;
				case 4:
					run = false;
					break;
				default:
					System.out.println("This option is not available");
					break;
				}
			} while (run);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			ctrl.CloseInput();
		}*/
	}

}
