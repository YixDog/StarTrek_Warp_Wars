public class Computer {
	private int score;
	private String computer = "C";
	int C_x;
	int C_y;
	
	public Computer(int c_x, int c_y) {
		C_x = c_x;
		C_y = c_y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComputer() {
		return computer;
	}

	public void setComputer(String computer) {
		this.computer = computer;
	}
	boolean computerFindEnd(char [][] gameElements,String path) {
        int computerX = C_x;
        int computerY = C_y;
        for (int i=0;i<path.length();i++) 
        { 
          char check=path.charAt(i);
          if(check=='L') {
        	  computerX--;
          }
          else if(check=='R') {
        	  computerX++;
          }
          else if(check=='D') {
        	  computerY++;
          }
          else if(check=='U') {
        	  computerY--;
          }
        }
        if(gameElements[computerY][computerX]=='P') {
        	return true;
        }
        return false;
	}
	boolean computerCanMove(char [][] gameElements,String put) {
        int computerX = C_x;
        int computerY = C_y;
        for (int i=0;i<put.length();i++) 
        { 
          char check=put.charAt(i);
          if(check=='L') {
        	  computerX--;
          }
          else if(check=='R') {
        	  computerX++;
          }
          else if(check=='D') {
        	  computerY++;
          }
          else if(check=='U') {
        	  computerY--;
          }
        }
        if(!((0<=computerY)&&(computerY<gameElements.length))&&((0<=computerX)&&(computerX<gameElements[0].length))){
        	return false;
        }
        if(gameElements[computerY][computerX]=='#') {
        	return false;
        }
        return true;
	}
	void computerPathFinder(char [][] gameElements) {
		CircularQueue finder = new CircularQueue(100000);
		String add="";
		finder.enqueue(add);
		String path="";
		while(computerFindEnd(gameElements,add)==false) {
			add=(String) finder.dequeue();
			String[]ways= {"L", "R", "U", "D"};
			for(String j:ways) {
				path=add+j;
				if(computerCanMove(gameElements,path)==true) {
					finder.enqueue(path);
				}
			}
		}
		 for (int i=0;i<add.length();i++) 
	        { 
	          char check=add.charAt(i);
	          if(check=='L') {
	        	  gameElements[C_y][C_x]=' ';
	        	  gameElements[C_y][C_x-1]='C';
	        	  
	        	  C_x--;
	          }
	          else if(check=='R') {
	        	  gameElements[C_y][C_x]=' ';
	        	  gameElements[C_y][C_x+1]='C';
	        	  
	        	  C_x++;
	          }
	          else if(check=='D') {
	        	  gameElements[C_y][C_x]=' ';
	        	  gameElements[C_y+1][C_x]='C';
	        	  
	        	  C_y++;
	          }
	          else if(check=='U') {
	        	  gameElements[C_y][C_x]=' ';
	        	  gameElements[C_y-1][C_x]='C';
	        	  C_y--;
	          }
	        }
	}
}
