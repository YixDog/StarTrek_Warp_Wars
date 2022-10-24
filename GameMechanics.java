import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;
import java.awt.Color;

public class GameMechanics {
	CircularQueue inputqueue = new CircularQueue(15);
	CircularQueue inputqueue2 = new CircularQueue(15);
	public enigma.console.Console cn = Enigma.getConsole("Game",120,35,13);
	public KeyListener klis; // listener of keyboard
    public int keypr;   // key pressed?
	public int rkey;    // key   (for press/release)
	public int second = 0;
	public int a = 0;
	
	public int time = 0;
	
	public char [][] gameElements = new char [23][55];
	public int player_x = 0;
	public int player_y = 0;
	public static Player Player = new Player();
	static boolean trap =false;
	static boolean warp =false;
	public int key = 0;
	static int trapposition_x = 3;
    static int trapposition_y = 3;
	public void Play(int mode) throws InterruptedException {
		int WarpInitialTime=0;
		int TrapInitialTime=0;	
		int warpposition_x = 0;
        int warpposition_y = 0;       
		
		boolean flag = true;
		boolean endgame=false;
		Random rnd = new Random();
		
		gameElements = Map();
	    consoleClear();
	    GameArray(gameElements);
	    Screen();
	    if (mode == 12) {
	    	FirstQueue(inputqueue,inputqueue2);
		    FirstElements();
	    }
	    else if (mode == 14) {
	    	FirstQueueHard(inputqueue,inputqueue2);
		    FirstElementsHard();
	    }
		keypr = 0;
		int keyCtrl = 0;
		
        while(flag){
			player_x = rnd.nextInt(55);
			player_y = rnd.nextInt(23);
			
			for (int i = 0; i < gameElements.length; i++) {
				for (int j = 0; j < gameElements[0].length; j++) {
					if (gameElements[player_y][player_x] == ' ') {
						gameElements[player_y][player_x] = 'P';
						flag = false;
					}
				}	
			}	
		}
		
		while(true) {		
			int second = 0, time = 0;
			char element = ' ';
			
			if(endgame) {
				break;
				
				
			}
				

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
	     while (keyCtrl == 0) {
	    	 /*
	    	 long start=System.currentTimeMillis();
	    	 if(Player.isEnergy240Seconds()==true&& second - 240>InitialTime)
	    	 {
	    		 Player.setEnergy240Seconds(false);
	    		 Player.setMovementSpeed(2);
	    	 }
	    	 if(Player.isEnergy30Seconds()==true&& second - 30>InitialTime)
	    	 {
	    		 Player.setEnergy30Seconds(false);
	    		 Player.setMovementSpeed(2);
	    	 } */
	    	 
        	 GameArray(gameElements);
        	 cn.getTextWindow().setCursorPosition(79, 25);
    	     String timePrint = String.valueOf(second);
    	     cn.getTextWindow().output(timePrint, new TextAttributes(Color.cyan,Color.black));
    	     cn.getTextWindow().setCursorPosition(80, 20);
    		 cn.getTextWindow().output(String.valueOf(Player.getScore()));
    		 cn.getTextWindow().setCursorPosition(80, 21);
    		 cn.getTextWindow().output(String.valueOf(Player.getLife()));
    	     time+=200;
    	     findingPlayer(gameElements);
    	     if(Player.getLife()<=0)
    		    {
    		    	endgame =true;
    		    	break;
    		    }
    	    
    		if(time == 1000) {
    				second++;
    				time = 0;
    				key = 0;
    		}
    		if(second%3==0 && key == 0) {
    			if (mode == 12) {
    				InputQueue(inputqueue,inputqueue2);
    				key = 1;
    			}
    			else if (mode == 14) {
    				InputQueueHard(inputqueue,inputqueue2);
    				key = 1;
    		    }
    		}
    		if(second%0.5==0) {
    			RandomNumbers();
    			key = 1;
    		}
   		 cn.getTextWindow().setCursorPosition(79, 25);
	     String timePrint1=String.valueOf(second);
	     cn.getTextWindow().output(timePrint1, new TextAttributes(Color.cyan,Color.black)); 
	     if (gameElements[player_y][player_x] != '#' && gameElements[player_y][player_x] != ' ') {
      		element = gameElements[player_y ][player_x];
      		CollectNumbers(element);
      		
      	}
        	 if (keypr == 1) {
        		 if(rkey == KeyEvent.VK_UP) 
        		 {
        			 cn.getTextWindow().setCursorPosition(79, 25);
            	     timePrint = String.valueOf(second);
            	     cn.getTextWindow().output(timePrint, new TextAttributes(Color.cyan,Color.black)); 
            	     
        			 if(gameElements[player_y - 1][player_x] != '#' && gameElements[player_y - 2][player_x] != '#') {
        				keyCtrl = 1;
                     	gameElements[player_y][player_x] = ' ';
                     	//player_y=player_y-Player.getMovementSpeed();
                     	
                     	if (gameElements[player_y - 1][player_x] != '#' && gameElements[player_y - 1][player_x] != ' ') {
                     		element = gameElements[player_y - 1][player_x];
                     		CollectNumbers(element);
                     		gameElements[player_y - 1][player_x] = ' ';
                     	}
                     	
                     	if (gameElements[player_y - 2][player_x] != '#' && gameElements[player_y - 2][player_x] != ' ') {
                     		element = gameElements[player_y - 2][player_x];
                     		CollectNumbers(element);
                     		gameElements[player_y - 2][player_x] = ' ';
                     	}
                     	
                     	player_y = player_y-2;
                     	gameElements[player_y][player_x] = 'P';
                     	GameArray(gameElements);
                     	
        			 }
        			 else if(gameElements[player_y - 1][player_x] != '#' && gameElements[player_y - 2][player_x] == '#') {
        				keyCtrl = 1;
                      	gameElements[player_y][player_x] = ' ';
                      	
                      	if (gameElements[player_y - 1][player_x] != '#' && gameElements[player_y - 1][player_x] != ' ') {
                     		element = gameElements[player_y - 1][player_x];
                     		CollectNumbers(element);
                     		gameElements[player_y - 1][player_x] = ' ';
                     	}
                      	
                      	player_y=player_y-1;
                      	gameElements[player_y][player_x] = 'P';
                      	GameArray(gameElements);
        			 }
        			
        				 
                 }
                 if(rkey == KeyEvent.VK_DOWN) {
        			 cn.getTextWindow().setCursorPosition(79, 25);
            	     timePrint = String.valueOf(second);
            	     cn.getTextWindow().output(timePrint, new TextAttributes(Color.cyan,Color.black));   
                	 if(gameElements[player_y + 1][player_x] != '#' && gameElements[player_y + 2][player_x] != '#') {
         				keyCtrl = 1;
                      	gameElements[player_y][player_x] = ' ';
                      	//player_y=player_y+Player.getMovementSpeed();
                      	
                      	if (gameElements[player_y + 1][player_x] != '#' && gameElements[player_y + 1][player_x] != ' ') {
                     		element = gameElements[player_y + 1][player_x];
                     		CollectNumbers(element);
                     		gameElements[player_y + 1][player_x] = ' ';
                     	}
                     	
                     	if (gameElements[player_y + 2][player_x] != '#' && gameElements[player_y + 2][player_x] != ' ') {
                     		element = gameElements[player_y + 2][player_x];
                     		CollectNumbers(element);
                     		gameElements[player_y + 2][player_x] = ' ';
                     	}
                     	
                      	player_y = player_y+2;
                      	gameElements[player_y][player_x] = 'P';
                      	GameArray(gameElements);
         			 }
         			 else if(gameElements[player_y + 1][player_x] != '#'&& gameElements[player_y + 2][player_x] == '#') {
         				keyCtrl = 1;
                       	gameElements[player_y][player_x] = ' ';
                       	
                       	if (gameElements[player_y + 1][player_x] != '#' && gameElements[player_y + 1][player_x] != ' ') {
                     		element = gameElements[player_y + 1][player_x];
                     		CollectNumbers(element);
                     		gameElements[player_y + 1][player_x] = ' ';
                     	}
                     	
                       	player_y=player_y+1;
                       	gameElements[player_y][player_x] = 'P';
                       	GameArray(gameElements);
         			 }
                 }
                 if(rkey == KeyEvent.VK_RIGHT) {
        			 cn.getTextWindow().setCursorPosition(79, 25);
            	     timePrint = String.valueOf(second);
            	     cn.getTextWindow().output(timePrint, new TextAttributes(Color.cyan,Color.black));   
                	 if(gameElements[player_y][player_x+1] != '#' && gameElements[player_y][player_x+2] != '#') {
           				keyCtrl = 1;
                        	gameElements[player_y][player_x] = ' ';
                        	//player_y=player_y+Player.getMovementSpeed();
                        	
                        	if (gameElements[player_y][player_x + 1] != '#' && gameElements[player_y][player_x + 1] != ' ') {
                         		element = gameElements[player_y][player_x + 1];
                         		CollectNumbers(element);
                         		gameElements[player_y][player_x + 1] = ' ';
                         	}
                         	
                         	if (gameElements[player_y][player_x + 2] != '#' && gameElements[player_y][player_x + 2] != ' ') {
                         		element = gameElements[player_y][player_x + 2];
                         		CollectNumbers(element);
                         		gameElements[player_y][player_x + 2] = ' ';
                         	}
                         	
                        	player_x=player_x+2;
                        	gameElements[player_y][player_x] = 'P';
                        	GameArray(gameElements);
           			 }
           			 else if(gameElements[player_y][player_x+1] != '#'&& gameElements[player_y][player_x + 2] == '#') {
           				keyCtrl = 1;
                         	gameElements[player_y][player_x] = ' ';
                         	
                         	if (gameElements[player_y][player_x + 1] != '#' && gameElements[player_y][player_x + 1] != ' ') {
                         		element = gameElements[player_y][player_x + 1];
                         		CollectNumbers(element);
                         		gameElements[player_y][player_x + 1] = ' ';
                         	}
                         	
                         	player_x=player_x+1;
                         	gameElements[player_y][player_x] = 'P';
                         	GameArray(gameElements);
           			 }
                 }
                 if(rkey == KeyEvent.VK_LEFT) {
        			 cn.getTextWindow().setCursorPosition(79, 25);
            	     timePrint = String.valueOf(second);
            	     cn.getTextWindow().output(timePrint, new TextAttributes(Color.cyan,Color.black));   
                	 if(gameElements[player_y][player_x-1] != '#' && gameElements[player_y][player_x-2] != '#') {
          				keyCtrl = 1;
                       	gameElements[player_y][player_x] = ' ';
                       	//player_y=player_y+Player.getMovementSpeed();
                       	
                       	if (gameElements[player_y][player_x - 1] != '#' && gameElements[player_y][player_x - 1] != ' ') {
                     		element = gameElements[player_y][player_x - 1];
                     		CollectNumbers(element);
                     		gameElements[player_y][player_x - 1] = ' ';
                     	}
                     	
                     	if (gameElements[player_y][player_x - 2] != '#' && gameElements[player_y][player_x - 2] != ' ') {
                     		element = gameElements[player_y][player_x - 2];
                     		CollectNumbers(element);
                     		gameElements[player_y][player_x - 2] = ' ';
                     	}
                     	
                       	player_x=player_x-2;
                       	gameElements[player_y][player_x] = 'P';
                       	GameArray(gameElements);
          			 }
          			 else if(gameElements[player_y][player_x-1] != '#' && gameElements[player_y][player_x - 2] == '#') {
          				keyCtrl = 1;
                        	gameElements[player_y][player_x] = ' ';
                        	
                        	if (gameElements[player_y][player_x - 1] != '#' && gameElements[player_y][player_x - 1] != ' ') {
                         		element = gameElements[player_y][player_x - 1];
                         		CollectNumbers(element);
                         		gameElements[player_y][player_x - 1] = ' ';
                         	}
                        	
                        	player_x=player_x-1;
                        	gameElements[player_y][player_x] = 'P';
                        	GameArray(gameElements);
          			 }
                 }
                 char backpacktop=' ';
                
                 
                 if(!Player.Backpack.isEmpty())
                 backpacktop = Player.Backpack.peek().toString().charAt(0);
                 if(rkey == KeyEvent.VK_W && gameElements[player_y-1][player_x] != '#'&&!Player.Backpack.isEmpty()){
                	 gameElements[player_y-1][player_x] = Player.Backpack.pop().toString().charAt(0);
                	 if(backpacktop =='*') { 
                	 warp=true;
                	 WarpInitialTime =second;
                	 warpposition_x = player_x;
                     warpposition_y = player_y-1;
                	 }
                	 
                	 else if(backpacktop =='=') { 
                		 trap=true;
                    	 TrapInitialTime =second;
                    	 trapposition_x = player_x;
                    	 trapposition_y = player_y-1;
                    	 }
                    	 else
                    	 gameElements[player_y-1][player_x]= ' ';
                		 
                	 GameArray(gameElements);
                	 StackPrint(Player.Backpack);
                 }
                 if(rkey == KeyEvent.VK_S && gameElements[player_y+1][player_x] != '#'&&!Player.Backpack.isEmpty()){
                	 gameElements[player_y+1][player_x] = Player.Backpack.pop().toString().charAt(0);
                	 if(backpacktop =='*') { 
                		 warp=true;
                		 WarpInitialTime =second;
                    	 warpposition_x = player_x;
                         warpposition_y = player_y+1;
                         
                    	 }
                	 
                	 else if(backpacktop =='=') { 
                		 trap=true;
                		 TrapInitialTime =second;
                		 trapposition_x = player_x;
                    	 trapposition_y = player_y+1;
                    	 
                         
                    	 }
                	 else
                    	 gameElements[player_y+1][player_x]= ' ';
                	 GameArray(gameElements);
                	 StackPrint(Player.Backpack);
                	 
                 }
                 
                 if(rkey == KeyEvent.VK_A && gameElements[player_y][player_x-1] != '#'&&!Player.Backpack.isEmpty()){
                	 gameElements[player_y][player_x-1] = Player.Backpack.pop().toString().charAt(0);
                	 if(backpacktop =='*') { 
                		 warp=true;
                		 WarpInitialTime =second;
                    	 warpposition_x = player_x-1;
                         warpposition_y = player_y;
                    	 }
                	 
                	 else if(backpacktop =='=') { 
                		 trap=true;
                		 TrapInitialTime =second;
                		 trapposition_x = player_x-1;
                    	 trapposition_y = player_y;
                    	 }
                	 else
                    	 gameElements[player_y][player_x-1]= ' ';
                	 GameArray(gameElements);
                	 StackPrint(Player.Backpack);
                 }
                 if(rkey == KeyEvent.VK_D && gameElements[player_y-1][player_x+1] != '#'&&!Player.Backpack.isEmpty()){
                	 gameElements[player_y][player_x+1] = Player.Backpack.pop().toString().charAt(0);
                	 if(backpacktop =='*') {
                		 warp=true;
                		 WarpInitialTime =second;
                    	 warpposition_x = player_x+1;
                         warpposition_y = player_y;
                    	 }
                	 
                	 else if(backpacktop =='=') {
                		 trap=true;
                		 TrapInitialTime =second;
                    	 trapposition_x = player_x+1;
                    	 trapposition_y = player_y;
                    	
                    	 }
                	 else
                    	 gameElements[player_y][player_x+1]= ' ';
                	 GameArray(gameElements);
                	 StackPrint(Player.Backpack);
                 }
                 
                
             }
        	 if(warp&&WarpInitialTime+25>second)
             {
            	 Warp(gameElements,warpposition_y,warpposition_x);
            	 GameArray(gameElements);
            	 StackPrint(Player.Backpack);
             }
             else {
            	 warp=false;
            	 gameElements[warpposition_y][warpposition_x]=' ';
             }
        	 if(trap&&TrapInitialTime+25>second)
             {
            	 
            	 GameArray(gameElements);
            	 StackPrint(Player.Backpack);
             }
             else {
            	 trap=false;
            	 gameElements[trapposition_y][trapposition_x]=' ';
             }
 	      keypr=0;  
          keyCtrl = 0;
          Thread.sleep(200);
          }
     }
		consoleClear();
		cn.getTextWindow().setCursorPosition(18,12);
		cn.getTextWindow().output("\r\n"
				+ " _____                        _____                _ \r\n"
				+ "|  __ \\                      |  _  |              | |\r\n"
				+ "| |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __| |\r\n"
				+ "| | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__| |\r\n"
				+ "| |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |  |_|\r\n"
				+ " \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|  (_)\r\n"
				+ "                                                     \r\n"
				+ "                                                     \r\n",new TextAttributes(Color.blue,Color.black));
		cn.getTextWindow().setCursorPosition(17,22);
		cn.getTextWindow().output("Your score is: "+Player.getScore());
	}
			
	public char[][] Map() {
		int k = 0;
        try {
			File map = new File("map.txt");
			Scanner in = new Scanner(map);
			while(in.hasNextLine()) {
				String line = in.nextLine();
				if (line != null) {
					for (int j = 0; j < line.length(); j++) {
						gameElements [k][j] = (char) line.charAt(j);
					}
					k++;
				}
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return gameElements;
	}

	public void GameArray(char [][] gameElements) {
		
		for (int i = 0; i < gameElements.length; i++) {
			for (int j = 0; j < gameElements[0].length; j++) {
				if (gameElements[i][j] == '#') {
					cn.getTextWindow().setCursorPosition(j + 5, i + 3);
					cn.getTextWindow().output(gameElements[i][j]);
				}
				else if (gameElements[i][j] == 'P') {
					cn.getTextWindow().setCursorPosition(j + 5, i + 3);
					cn.getTextWindow().output(gameElements[i][j], new TextAttributes(Color.green,Color.black));
				}
				else if (gameElements[i][j] == '1' || gameElements[i][j] == '2' || gameElements[i][j] == '3') {
					cn.getTextWindow().setCursorPosition(j + 5, i + 3);
					cn.getTextWindow().output(gameElements[i][j], new TextAttributes(Color.yellow,Color.black));
				}
				else if (gameElements[i][j] == '4' || gameElements[i][j] == '5') {
					cn.getTextWindow().setCursorPosition(j + 5, i + 3);
					cn.getTextWindow().output(gameElements[i][j], new TextAttributes(Color.blue,Color.black));
				}
				else if (gameElements[i][j] == '*' || gameElements[i][j] == '=') {
					cn.getTextWindow().setCursorPosition(j + 5, i + 3);
					cn.getTextWindow().output(gameElements[i][j], new TextAttributes(Color.red,Color.black));
				}
				else {
					cn.getTextWindow().setCursorPosition(j + 5, i + 3);
					cn.getTextWindow().output(gameElements[i][j], new TextAttributes(Color.red,Color.black));
				}
			}	
		}	
	}
	
	public void Screen() {
		cn.getTextWindow().setCursorPosition(69, 5);
		cn.getTextWindow().output("|               |", new TextAttributes(Color.green,Color.black));
		cn.getTextWindow().setCursorPosition(69, 4);
		cn.getTextWindow().output("|               |", new TextAttributes(Color.green,Color.black));
		cn.getTextWindow().setCursorPosition(69, 6);
		cn.getTextWindow().output("|               |", new TextAttributes(Color.green,Color.black));
		cn.getTextWindow().setCursorPosition(70, 4);
		cn.getTextWindow().output("<<<<<<<<<<<<<<<", new TextAttributes(Color.green,Color.black));
		cn.getTextWindow().setCursorPosition(70, 6);
		cn.getTextWindow().output("<<<<<<<<<<<<<<<", new TextAttributes(Color.green,Color.black));
		cn.getTextWindow().setCursorPosition(70, 3);
		cn.getTextWindow().output("Input", new TextAttributes(Color.green,Color.black));
		
		for (int i = 8; i < 16; i++) {
			cn.getTextWindow().setCursorPosition(75, i);
			cn.getTextWindow().output("|", new TextAttributes(Color.red,Color.black));
			cn.getTextWindow().setCursorPosition(79, i);
			cn.getTextWindow().output("|", new TextAttributes(Color.red,Color.black));
		}
		
		cn.getTextWindow().setCursorPosition(75, 16);
		cn.getTextWindow().output("+---+", new TextAttributes(Color.red,Color.black));
		cn.getTextWindow().setCursorPosition(75, 17);
		cn.getTextWindow().output("P.Backpack", new TextAttributes(Color.red,Color.black));
		cn.getTextWindow().setCursorPosition(70, 19);
		cn.getTextWindow().output("P.Energy:", new TextAttributes(Color.cyan,Color.black));
		cn.getTextWindow().setCursorPosition(70, 20);
		cn.getTextWindow().output("P.Score :", new TextAttributes(Color.cyan,Color.black));;
		cn.getTextWindow().setCursorPosition(70, 21);
		cn.getTextWindow().output("P.Life  :", new TextAttributes(Color.cyan,Color.black));
		cn.getTextWindow().setCursorPosition(70, 23);
		cn.getTextWindow().output("C.Score :", new TextAttributes(Color.cyan,Color.black));
		cn.getTextWindow().setCursorPosition(70, 25);
		cn.getTextWindow().output("Time    :", new TextAttributes(Color.cyan,Color.black));
	}
	
	
	static void BackpackPowerControl()
	  {
	  Stack TempStack = new Stack(8);
	  String data = String.valueOf(Player.Backpack.pop());
	  TempStack.push(data);
	  if(!Player.Backpack.isEmpty())
	  {
	  if(String.valueOf(Player.Backpack.peek()).equals(TempStack.peek()))
	  {
	   
	   if(data.equals("1"))
	   {
	    Player.Backpack.push(TempStack.pop());	   
	   }
	   else if(data.equals("2"))
	   {
	   Player.Backpack.pop();
	   TempStack.pop();
	   Player.setEnergy30Seconds(true);
	   
	   }
	   else if(data.equals("3"))
	   {
	   Player.Backpack.pop();
	   TempStack.pop();
	   Player.Backpack.push("=");	   
	   
	   }
	   else if(data.equals("4"))
	   {
	   Player.Backpack.pop();
	   TempStack.pop();
	   Player.setEnergy240Seconds(true);
	   }
	   else if(data.equals("5"))
	   {
	   Player.Backpack.pop();
	   TempStack.pop();
	   Player.Backpack.push("*");
	   }
	  }
	  else
	   {
	    Player.Backpack.push(TempStack.pop());
	   }
	  }
	  else
	   {
	    Player.Backpack.push(TempStack.pop());
	   }
	  }
	
	public void consoleClear() {
		for (int i = 0; i < 120; i++) {
			for (int j = 0; j < 35; j++) {
				cn.getTextWindow().output(i,j, ' ');
			}
		}
		cn.getTextWindow().setCursorPosition(6, 0);
	}
	
    public void FirstQueue(CircularQueue inputqueue,CircularQueue inputqueue2){
		
		int lineup = 0;
		
		Random rnd = new Random();
		for(int i=0;i<15;i++) {
			
			int random = rnd.nextInt(40);
			
			if(random < 13) {
				inputqueue.enqueue('1');
			}				
			else if(random < 21) {
				inputqueue.enqueue('2');
			}					
			else if(random < 27) {
				inputqueue.enqueue('3');
			}					
			else if(random < 32) {
				inputqueue.enqueue('4');
			}						
			else if(random < 36) {
				inputqueue.enqueue('5');
			}					
			else if(random < 38) {
				inputqueue.enqueue('=');
			}					
			else if(random < 39) {
				inputqueue.enqueue('*');
			}					
			else if(random < 41) {
				inputqueue.enqueue('C');
			}
	    }
		
					
		int queuelenght = inputqueue.size();
		
		for (int i = 0; i < queuelenght; i++) {
			char input = (Character) inputqueue.peek();
			inputqueue2.enqueue(inputqueue.dequeue());
			cn.getTextWindow().setCursorPosition(70 + lineup, 5);
			cn.getTextWindow().output(input);
			lineup++;
		}
	}
		
	public void InputQueue (CircularQueue inputqueue,CircularQueue inputqueue2) {
			
			int queuelenght = inputqueue.size();
			int lineup = 0;
			Random rnd = new Random();
			for (int i = 0; i < queuelenght; i++) {
				inputqueue2.peek();
				inputqueue.enqueue(inputqueue2.dequeue());								
			}
			
			inputqueue.peek();
			char input = (Character) inputqueue.peek();			
			
			boolean flag = true;
			while(flag){
				
				int randomx = rnd.nextInt(55);
				int randomy = rnd.nextInt(23);
				
				for (int i = 0; i < gameElements.length; i++) {
					for (int j = 0; j < gameElements[0].length; j++) {
						if (gameElements[randomy][randomx] == ' ') {
							gameElements[randomy][randomx] = input;
							GameArray(gameElements);
							flag = false;
						}
						
					}	
				}
				
			}
			inputqueue.dequeue();
			
			for (int i = 0; i < queuelenght - 1; i++) {
				inputqueue.peek();
				inputqueue2.enqueue(inputqueue.dequeue());								
			}
			
			for (int i = 0; i < queuelenght - 1; i++) {
				inputqueue2.peek();
				inputqueue.enqueue(inputqueue2.dequeue());								
			}
			
				int random = rnd.nextInt(40);
				
				if(random < 13) {
					inputqueue.enqueue('1');
				}				
				else if(random < 21) {
					inputqueue.enqueue('2');
				}					
				else if(random < 27) {
					inputqueue.enqueue('3');
				}					
				else if(random < 32) {
					inputqueue.enqueue('4');
				}						
				else if(random < 36) {
					inputqueue.enqueue('5');
				}					
				else if(random < 38) {
					inputqueue.enqueue('=');
				}					
				else if(random < 39) {
					inputqueue.enqueue('*');
				}					
				else if(random < 41) {
					inputqueue.enqueue('C');
				}
									
				lineup = 0;
				
				for (int i = 0; i < queuelenght; i++) {
					inputqueue.peek();
					input = (Character) inputqueue.peek();
					inputqueue2.enqueue(inputqueue.dequeue());
					cn.getTextWindow().setCursorPosition(70 + lineup, 5);
					cn.getTextWindow().output(input);
					lineup++;
				}	
		    }
	
	public void FirstElements() {
		
		Random rnd = new Random();
		Queue elements = new Queue(19);
		
		boolean flag = true;
		
											
		elements.enqueue('C');
		
		for(int i=0;i<18;i++) {
			
			int random = rnd.nextInt(40);
			
			if(random < 13) {
				elements.enqueue('1');
			}				
			else if(random < 21) {
				elements.enqueue('2');
			}					
			else if(random < 27) {
				elements.enqueue('3');
			}					
			else if(random < 32) {
				elements.enqueue('4');
			}						
			else if(random < 36) {
				elements.enqueue('5');
			}					
			else if(random < 38) {
				elements.enqueue('=');
			}					
			else if(random < 39) {
				elements.enqueue('*');
			}					
			else if(random < 41) {
				elements.enqueue('C');
			}
	    }
		
		
		while(!(elements.isEmpty())) {
		
		char element = (Character) elements.peek();			
		flag = true;
		
		while(flag){
			
			int randomx = rnd.nextInt(55);
			int randomy = rnd.nextInt(23);
			
			for (int i = 0; i < gameElements.length; i++) {
				for (int j = 0; j < gameElements[0].length; j++) {
					if (gameElements[randomy][randomx] == ' ') {
						gameElements[randomy][randomx] = element;
						flag = false;
					}
					
				}	
			}
			
		}
		
		elements.dequeue();
		
		}
		

		GameArray(gameElements);
		
	}
	
	public void RandomNumbers() {
		
		Random rnd = new Random();
		
		for (int i = 0; i < gameElements.length; i++) {
			for (int j = 0; j < gameElements[0].length; j++) {
				
				
				
				if ((gameElements[i][j] == '5' || gameElements[i][j] == '4' ))
						 {if((!((i==(trapposition_y-1)&& j ==(trapposition_x-1))||
								 (i==(trapposition_y-1)&& j ==(trapposition_x))||
								 (i==(trapposition_y-1)&& j ==(trapposition_x+1))||
								 (i==(trapposition_y)&& j ==(trapposition_x-1))||
								 (i==(trapposition_y)&& j ==(trapposition_x))||
								 (i==(trapposition_y)&& j ==(trapposition_x+1))||
								 (i==(trapposition_y+1)&& j ==(trapposition_x-1))||
								 (i==(trapposition_y+1)&& j ==(trapposition_x))||
								 (i==(trapposition_y+1)&& j ==(trapposition_x+1)))||
								 !trap)) {
					
					boolean flag = true;
					
					do {
						
					
					int choice = rnd.nextInt(4);
					
					if(choice == 0) {
						if (gameElements[i + 1][j] == ' ') {
							gameElements[i + 1][j] = gameElements[i][j];
							gameElements[i][j] = ' ';
							flag = false;
						}
					}
					
					if(choice == 1) {
						if (gameElements[i - 1][j] == ' ') {
							gameElements[i - 1][j] = gameElements[i][j];
							gameElements[i][j] = ' ';
							flag = false;
						}
					}
					 
					if(choice == 2) {
						if (gameElements[i][j + 1] == ' ') {
							gameElements[i][j + 1] = gameElements[i][j];
							gameElements[i][j] = ' ';
							flag = false;
						}
					}
					
					if(choice == 3) {
						if (gameElements[i][j - 1] == ' ') {
							gameElements[i][j - 1] = gameElements[i][j];
							gameElements[i][j] = ' ';
							flag = false;
						}
					}
					
					if(gameElements[i][j + 1] != ' ' && gameElements[i][j - 1] != ' ' && gameElements[i - 1][j] != ' '
							&& gameElements[i + 1][j] != ' ') {
						gameElements[i][j] = gameElements[i][j];
					}
					
					}
					
					while(flag);	
				}	
						 }
			}
			}
		}
		
	
	
	
	public void CollectNumbers(char element) {
        
		switch (element) {  
		  case '1':
			if(!Player.Backpack.isFull())
		    Player.Backpack.push(element);
		    Player.setScore(Player.getScore() + 1);
		    BackpackPowerControl();
		    break;
		  case '2':
			if(!Player.Backpack.isFull())
			Player.Backpack.push(element);
		    Player.setScore(Player.getScore() + 5);
		    BackpackPowerControl();
		    break;
		  case '3':
			if(!Player.Backpack.isFull())
			Player.Backpack.push(element);
		    Player.setScore(Player.getScore() + 15);
		    BackpackPowerControl();
		    break;
		  case '4':
			if(!Player.Backpack.isFull())
	   	    Player.Backpack.push(element);
		    Player.setScore(Player.getScore() + 50);
		    BackpackPowerControl();
		    break;
		  case '5':
			if(!Player.Backpack.isFull())
			Player.Backpack.push(element);
		    Player.setScore(Player.getScore() + 150);
		    BackpackPowerControl();
		    break;
		  case '=':
			if(!Player.Backpack.isFull())
			Player.Backpack.push(element);
			BackpackPowerControl();
		    break;
		  case '*':
			if(!Player.Backpack.isFull())
		    Player.Backpack.push(element);
			BackpackPowerControl();
		    break;
		  case 'C':
			
				Player.setLife(Player.getLife()-1);
			    break;
		 
		}
		
		StackPrint(Player.Backpack);
	}

	public void StackPrint(Stack stack) {
		Stack tempStack = new Stack(8);
		int size = Player.Backpack.size();
		for (int i = 8; i < 16; i++) {
			cn.getTextWindow().setCursorPosition(77, i);
			cn.getTextWindow().output(" ");
		
		}			
		for (int i = 16-size; i < 16; i++) {
			
			cn.getTextWindow().setCursorPosition(77, i);
			if(!stack.isEmpty())
			{
			cn.getTextWindow().output(String.valueOf(stack.peek()), new TextAttributes(Color.green,Color.black));
			tempStack.push(stack.pop());
			}
			
		}
		
		while (! (tempStack.isEmpty())) {
			stack.push(tempStack.pop());
		}
		
	}
	char[][] Warp(char[][]gamelements,int warp_y,int warp_x)
	{
		for(int j=-1;j < 2;j++)
		for(int i=-1;i < 2;i++)
		{
     if(gamelements[warp_y+i][warp_x+j]== '1' ||
        gamelements[warp_y+i][warp_x+j]== '2' ||
    	gamelements[warp_y+i][warp_x+j]== '3' ||
    	gamelements[warp_y+i][warp_x+j]== '4' ||
    	gamelements[warp_y+i][warp_x+j]== '5' ||
    	gamelements[warp_y+i][warp_x+j]== 'C')
     {
    	gamelements[warp_y+i][warp_x+j]= ' ';
    	gamelements[warp_y][warp_x]= ' ';
    	warp=false;

    	
     }
     
      
		}
	 return gamelements;
	}
public void FirstQueueHard(CircularQueue inputqueue,CircularQueue inputqueue2){
		
		int lineup = 0;
		
		Random rnd = new Random();
		for(int i=0;i<15;i++) {
			
			int random = rnd.nextInt(45);
			
			if(random < 13) {
				inputqueue.enqueue('1');
			}				
			else if(random < 21) {
				inputqueue.enqueue('2');
			}					
			else if(random < 27) {
				inputqueue.enqueue('3');
			}					
			else if(random < 32) {
				inputqueue.enqueue('4');
			}						
			else if(random < 36) {
				inputqueue.enqueue('5');
			}					
			else if(random < 38) {
				inputqueue.enqueue('=');
			}					
			else if(random < 39) {
				inputqueue.enqueue('*');
			}					
			else if(random < 46) {
				inputqueue.enqueue('C');
			}
	    }
		
					
		int queuelenght = inputqueue.size();
		
		for (int i = 0; i < queuelenght; i++) {
			char input = (Character) inputqueue.peek();
			inputqueue2.enqueue(inputqueue.dequeue());
			cn.getTextWindow().setCursorPosition(70 + lineup, 5);
			cn.getTextWindow().output(input);
			lineup++;
		}
	}
		
	public void InputQueueHard (CircularQueue inputqueue,CircularQueue inputqueue2) {
			
			int queuelenght = inputqueue.size();
			int lineup = 0;
			Random rnd = new Random();
			for (int i = 0; i < queuelenght; i++) {
				inputqueue2.peek();
				inputqueue.enqueue(inputqueue2.dequeue());								
			}
			
			inputqueue.peek();
			char input = (Character) inputqueue.peek();			
			
			boolean flag = true;
			while(flag){
				
				int randomx = rnd.nextInt(55);
				int randomy = rnd.nextInt(23);
				
				for (int i = 0; i < gameElements.length; i++) {
					for (int j = 0; j < gameElements[0].length; j++) {
						if (gameElements[randomy][randomx] == ' ') {
							gameElements[randomy][randomx] = input;
							GameArray(gameElements);
							flag = false;
						}
						
					}	
				}
				
			}
			inputqueue.dequeue();
			
			for (int i = 0; i < queuelenght - 1; i++) {
				inputqueue.peek();
				inputqueue2.enqueue(inputqueue.dequeue());								
			}
			
			for (int i = 0; i < queuelenght - 1; i++) {
				inputqueue2.peek();
				inputqueue.enqueue(inputqueue2.dequeue());								
			}
			
				int random = rnd.nextInt(45);
				
				if(random < 13) {
					inputqueue.enqueue('1');
				}				
				else if(random < 21) {
					inputqueue.enqueue('2');
				}					
				else if(random < 27) {
					inputqueue.enqueue('3');
				}					
				else if(random < 32) {
					inputqueue.enqueue('4');
				}						
				else if(random < 36) {
					inputqueue.enqueue('5');
				}					
				else if(random < 38) {
					inputqueue.enqueue('=');
				}					
				else if(random < 39) {
					inputqueue.enqueue('*');
				}					
				else if(random < 46) {
					inputqueue.enqueue('C');
				}
									
				lineup = 0;
				
				for (int i = 0; i < queuelenght; i++) {
					inputqueue.peek();
					input = (Character) inputqueue.peek();
					inputqueue2.enqueue(inputqueue.dequeue());
					cn.getTextWindow().setCursorPosition(70 + lineup, 5);
					cn.getTextWindow().output(input);
					lineup++;
				}	
		    }
	
	public void FirstElementsHard() {
		
		Random rnd = new Random();
		Queue elements = new Queue(19);
		
		boolean flag = true;
		
											
		elements.enqueue('C');
		
		for(int i=0;i<18;i++) {
			
			int random = rnd.nextInt(45);
			
			if(random < 13) {
				elements.enqueue('1');
			}				
			else if(random < 21) {
				elements.enqueue('2');
			}					
			else if(random < 27) {
				elements.enqueue('3');
			}					
			else if(random < 32) {
				elements.enqueue('4');
			}						
			else if(random < 36) {
				elements.enqueue('5');
			}					
			else if(random < 38) {
				elements.enqueue('=');
			}					
			else if(random < 39) {
				elements.enqueue('*');
			}					
			else if(random < 46) {
				elements.enqueue('C');
			}
	    }
	}
	public void findingPlayer(char[][] gameElements) {
		for (int i = 0; i < gameElements.length; i++) {
            for (int j = 0; j < gameElements[0].length; j++) {
            	if(gameElements[i][j]=='C') {
            		{if((!((i==(trapposition_y-1)&& j ==(trapposition_x-1))||
							 (i==(trapposition_y-1)&& j ==(trapposition_x))||
							 (i==(trapposition_y-1)&& j ==(trapposition_x+1))||
							 (i==(trapposition_y)&& j ==(trapposition_x-1))||
							 (i==(trapposition_y)&& j ==(trapposition_x))||
							 (i==(trapposition_y)&& j ==(trapposition_x+1))||
							 (i==(trapposition_y+1)&& j ==(trapposition_x-1))||
							 (i==(trapposition_y+1)&& j ==(trapposition_x))||
							 (i==(trapposition_y+1)&& j ==(trapposition_x+1)))||
							 !trap)) {
            		int compX=j;
            		int compY=i;
            		boolean xpos=true;
            		boolean ypos=true;            	
            		int distanceX=player_x-compX;//10-3 pos
            		int distanceY=player_y-compY;
            		if(distanceX<0)  {
            			xpos=false;
            		}
            		if(distanceY<0)  {
            			ypos=false;
            		}
            		if(Math.abs(distanceY)<Math.abs(distanceX)) {
            			if(xpos==true) {
            				if(gameElements[i][j+1]!='#') {
                				compX++;
                				gameElements[i][j]=' ';
                				gameElements[i][compX]='C';
                				break;
            				}
            				else {
                				if(gameElements[i+1][j]!='#') {
                    				compY++;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else if(gameElements[i-1][j]!='#') {
                					compY--;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else{
                    				compX--;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}

            				}
            			
            			}
            			else {

            				if(gameElements[i][j-1]!='#') {
                				compX--;
                				gameElements[i][j]=' ';
                				gameElements[i][compX]='C';
                				break;
            				}
            				else {
                				if(gameElements[i-1][j]!='#') {
                    				compY--;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else if(gameElements[i+1][j]!='#') {
                					compY++;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else{
                    				compX++;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}

            				}
            			
            			
            				
            			}
            		}
            		else if(Math.abs(distanceY)>Math.abs(distanceX)) {
            			if(ypos==true) {
            				if(gameElements[i+1][j]!='#') {
                				compY++;
                				gameElements[i][j]=' ';
                				gameElements[compY][j]='C';
                				break;
            				}
            				else {
                				if(gameElements[i][j-1]!='#') {
                    				compX--;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}
                				else if(gameElements[i][j+1]!='#') {
                    				compX++;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}
                				else{
                    				compY--;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}

            				}
            			}
            			else {
            				if(gameElements[i-1][j]!='#') {
                				compY--;
                				gameElements[i][j]=' ';
                				gameElements[compY][j]='C';
                				break;
            				}
            				else {
                				if(gameElements[i][j-1]!='#') {
                    				compX--;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}
                				else if(gameElements[i][j+1]!='#') {
                    				compX++;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}
                				else{
                    				compY++;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
            				}
            			}
            			
            		}
            		else {
            			if(xpos==true) {

            				if(gameElements[i][j+1]!='#') {
                				compX++;
                				gameElements[i][j]=' ';
                				gameElements[i][compX]='C';
                				break;
            				}
            				else {
                				if(gameElements[i-1][j]!='#') {
                    				compY--;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else if(gameElements[i+1][j]!='#') {
                					compY++;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else{
                    				compX--;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}

            				}
            			
            			}
            			else {

            				if(gameElements[i][j-1]!='#') {
                				compX--;
                				gameElements[i][j]=' ';
                				gameElements[i][compX]='C';
                				break;
            				}
            				else {
                				if(gameElements[i-1][j]!='#') {
                    				compY--;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else if(gameElements[i+1][j]!='#') {
                					compY++;
                    				gameElements[i][j]=' ';
                    				gameElements[compY][j]='C';
                    				break;
                				}
                				else{
                    				compX++;
                    				gameElements[i][j]=' ';
                    				gameElements[i][compX]='C';
                    				break;
                				}

            				}
            			
            			
            				
            			}
            		}
            	}
            	}
            }
            }
		}
		}
	
}
