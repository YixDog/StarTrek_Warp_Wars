import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;
import java.awt.Color;

public class MenuMechanics {
	
	GameMechanics playerMovement = new GameMechanics();
	public enigma.console.Console cn = Enigma.getConsole("Game",120,35,13);
	public KeyListener klis; // listener of keyboard
    public int keypr;   // key pressed?
	public int rkey;    // key   (for press/release)
	
	public boolean playMode = false;
	
	public void MenuChoser() throws InterruptedException {
		Menu();
		keypr = 0;
		int cursory = 10;
		int keyCtrl = 0;
		
		while(true) {
		klis = new KeyListener() {
	        public void keyTyped(KeyEvent e) {}
	        public void keyPressed(KeyEvent e) {
	           if(keypr == 0) {
	              keypr = 1;
	              rkey = e.getKeyCode();
	           }
	        }
	        public void keyReleased(KeyEvent e) {}
	     };
	     cn.getTextWindow().addKeyListener(klis);
		
	     
			while(keyCtrl == 0) {
				 if(keypr == 1) {// selecting operation 
		             if(rkey == KeyEvent.VK_UP && cursory >= 14) {
		            	 keyCtrl = 1;
		            	 cursory -= 2;
		            	 if (cursory == 12) {
		            		 Menu();
		            		 cn.getTextWindow().setCursorPosition(48, 12);
		            		 cn.getTextWindow().output("1.PLAY", new TextAttributes(Color.green,Color.black)); 
		        	     }
		            	 else if (cursory == 14) {
		            		 Menu();
		            		 cn.getTextWindow().setCursorPosition(48, 14);
		            		 cn.getTextWindow().output("2.HARD MODE", new TextAttributes(Color.green,Color.black)); 
		                 }
		            	 else if (cursory == 16) {
		            		 Menu();
		            		 cn.getTextWindow().setCursorPosition(48, 16);
		            		 cn.getTextWindow().output("3.TUTORIALS", new TextAttributes(Color.green,Color.black)); 
		            	 }
		             }
		             
		             if(rkey == KeyEvent.VK_DOWN && cursory <= 16) { 
		            	 keyCtrl = 1;
		            	 cursory += 2;
		            	 if (cursory == 14) {
		            		 Menu();
		            		 cn.getTextWindow().setCursorPosition(48, 14);
		            		 cn.getTextWindow().output("2.HARD MODE", new TextAttributes(Color.green,Color.black)); 
		                 }
		            	 else if (cursory == 16) {
		            		 Menu();
		            		 cn.getTextWindow().setCursorPosition(48, 16);
		            		 cn.getTextWindow().output("3.TUTORIALS", new TextAttributes(Color.green,Color.black)); 
		            	 }
		            	 else if (cursory == 18) {
		            		 Menu();
		            		 cn.getTextWindow().setCursorPosition(48, 18);
		            		 cn.getTextWindow().output("4.CREDITS", new TextAttributes(Color.green,Color.black)); 
		            	 }	 
		             }
		          
		             if(rkey == KeyEvent.VK_ENTER) {
		            	keyCtrl = 1;
		                if (cursory == 12) {
		                	playerMovement.Play(12);
		                break;
		                }
		                		        	    
		                else if (cursory == 14) {
		                	playerMovement.Play(14);
		                }	
		                
		                else if (cursory == 16) {
		                	Tutorial();
		                }
		                
		                else if (cursory == 18) {
		                	Credits();
		                } 
				    }
		             
		             if(rkey == KeyEvent.VK_BACK_SPACE) {
		            	 playerMovement.consoleClear();
		            	 Menu();
		             }
		             
		             keypr=0;  
		             keyCtrl = 0;
				 }
			 }
    	 }
	}

	public void Menu() {
		cn.getTextWindow().setCursorPosition(47, 10);
		cn.getTextWindow().output(" ___________________");
		cn.getTextWindow().setCursorPosition(47, 11);
		cn.getTextWindow().output("|______WELCOME______|");
		cn.getTextWindow().setCursorPosition(47, 12);
		cn.getTextWindow().output("|1.PLAY             |");
		cn.getTextWindow().setCursorPosition(47, 13);
		cn.getTextWindow().output("|___________________|");
		cn.getTextWindow().setCursorPosition(47, 14);
		cn.getTextWindow().output("|2.HARD MODE        |");
		cn.getTextWindow().setCursorPosition(47, 15);
		cn.getTextWindow().output("|___________________|");
		cn.getTextWindow().setCursorPosition(47, 16);
		cn.getTextWindow().output("|3.TUTORIALS        |");
		cn.getTextWindow().setCursorPosition(47, 17);
		cn.getTextWindow().output("|___________________|");
		cn.getTextWindow().setCursorPosition(47, 18);
		cn.getTextWindow().output("|4.CREDITS          |");
		cn.getTextWindow().setCursorPosition(47, 19);
		cn.getTextWindow().output("|___________________|");
			 
	  }
	
	public void Tutorial() {
	     playerMovement.consoleClear();
		 cn.getTextWindow().setCursorPosition(0, 1);
   	     cn.getTextWindow().output("        _______ _    _ _______ ____  _____  _____          _       _____ \r\n"
   	 		+ "       |__   __| |  | |__   __/ __ \\|  __ \\|_   _|   /\\   | |     / ____|\r\n"
   	 		+ "          | |  | |  | |  | | | |  | | |__) | | |    /  \\  | |    | (___  \r\n"
   	 		+ "          | |  | |  | |  | | | |  | |  _  /  | |   / /\\ \\ | |     \\___ \\ \r\n"
   	 		+ "          | |  | |__| |  | | | |__| | | \\ \\ _| |_ / ____ \\| |____ ____) |\r\n"
   	 		+ "          |_|   \\____/   |_|  \\____/|_|  \\_\\_____/_/    \\_\\______|_____/ \r\n"
   	 		+ "                                                                         \r\n", new TextAttributes(Color.blue,Color.black));
   	     cn.getTextWindow().setCursorPosition(6, 9);
  	     cn.getTextWindow().output("> You need to use the arrow keys to move the player.", new TextAttributes(Color.green,Color.black));
  	     cn.getTextWindow().setCursorPosition(6, 11);
	     cn.getTextWindow().output("> The player has a backpack and the numbers and devices he/she collects on the playground are thrown ", new TextAttributes(Color.green,Color.black));
	     cn.getTextWindow().setCursorPosition(6, 12);
	     cn.getTextWindow().output("  into this backpack. If you want to leave them back you should use the WASD keys.", new TextAttributes(Color.green,Color.black));
	     cn.getTextWindow().setCursorPosition(6, 14);
	     cn.getTextWindow().output("> Depending on the numbers you collect, you earn different amounts of points.", new TextAttributes(Color.green,Color.black));
	     cn.getTextWindow().setCursorPosition(6, 16);
	     cn.getTextWindow().output("> Your goal is to get the highest score without getting caught by the chasing bots.", new TextAttributes(Color.green,Color.black));
	     cn.getTextWindow().setCursorPosition(6, 18);
	     cn.getTextWindow().output("> You can use the trap and warp devices you have to get rid of the bots.", new TextAttributes(Color.green,Color.black));
	     cn.getTextWindow().setCursorPosition(6, 20);
	     cn.getTextWindow().output("> You have a total of 5 lives. If you get caught in a bot, you will lose one of your lives. ", new TextAttributes(Color.green,Color.black));
	     cn.getTextWindow().setCursorPosition(6, 21);
	     cn.getTextWindow().output("  It's game over when you lose all your lives.", new TextAttributes(Color.green,Color.black));
	     cn.getTextWindow().setCursorPosition(6, 24);
	     cn.getTextWindow().output("  Have a good time!", new TextAttributes(Color.cyan,Color.black));
	}
	
	public void Credits() {
		playerMovement.consoleClear();
		cn.getTextWindow().setCursorPosition(0, 3);
  	    cn.getTextWindow().output("    ____       ___          ___          __     __ __                        __\r\n"
  	     		+ "   /_  / ___ _/ _/__ ____  / _ )___ ____/ /__  / //_/__ ________ ___ _____  / /\r\n"
  	     		+ "    / /_/ _ `/ _/ -_) __/ / _  / -_) __/  '_/ / ,< / _ `/ __/ _ `/ // / _ \\/ / \r\n"
  	     		+ "   /___/\\_,_/_/ \\__/_/   /____/\\__/_/ /_/\\_\\ /_/|_|\\_,_/_/  \\_,_/\\_, /\\___/_/  \r\n"
  	     		+ "                                                                /___/          ", new TextAttributes(Color.blue,Color.black));
  	    cn.getTextWindow().setCursorPosition(0, 10);
	    cn.getTextWindow().output("   __  ___      _ __    _____        \r\n"
	    		+ "   \\ \\/ (_)__ _(_) /_  / ___/__ ____ \r\n"
	    		+ "    \\  / / _ `/ / __/ / /__/ _ `/ _ \\\r\n"
	    		+ "    /_/_/\\_, /_/\\__/  \\___/\\_,_/_//_/\r\n"
	    		+ "        /___/                        ", new TextAttributes(Color.green,Color.black)); 
	    cn.getTextWindow().setCursorPosition(0, 17);
	    cn.getTextWindow().output("     ____               __ ___  _ __  _  _     \r\n"
	    		+ "    / __/______ ___    / //_(_)(_) /_(_)(_)__ _\r\n"
	    		+ "   / _// __/ -_) _ \\  / ,< / _ \\/ __/ // / _ `/\r\n"
	    		+ "  /___/_/  \\__/_//_/ /_/|_|\\___/\\__/\\_,_/\\_, / \r\n"
	    		+ "                                        /___/  ", new TextAttributes(Color.yellow,Color.black));
	    cn.getTextWindow().setCursorPosition(0, 24);
	    cn.getTextWindow().output("     ____         __               ___       __             \r\n"
	    		+ "    / __/_ ______/ /_____ ____    / _ )__ __/ /_ _  __ _____\r\n"
	    		+ "   / _// // / __/  '_/ _ `/ _ \\  / _  / // / /  ' \\/ // (_-<\r\n"
	    		+ "  /_/  \\_,_/_/ /_/\\_\\\\_,_/_//_/ /____/\\_,_/_/_/_/_/\\_,_/___/\r\n"
	    		+ "                                                            ", new TextAttributes(Color.red,Color.black));
		
	}
}
