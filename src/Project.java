import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

class MyCanvas extends Canvas { 
int x, y;
int StepX,StepY;
int moveX,moveY;
int useXY;
int NewX,NewY;
int xi,yi;
int xii,yii;
int arrTable[][];
int arrUse[][];
int arrUndo[][];
int moveKey;
int newColor;
int ja;
int NewTmpX,NewTmpY;
int xn,yn,xny,hin;
int ax,ay,axy,axyn,axyz;
int youWin,youLos;
int ranNo;
long ranTmp;
String newXY;
String arrXY[][];
String arrCom[];

		public MyCanvas(int xx,int yy) {
		x = xx;
		y = yy;
		
		newColor = 0xFF9933;
		moveKey = 0;
		
		ranNo = 1;

		StepX = (getWidth()-1)/y;
		StepY = (getHeight()-1)/x;

		moveX = 0;
		moveY = 0;
		xi = 6;
		yi = 0;

		arrXY = new String[x][y];
			for(int i=0;i<x;i++){
				for(int j=0;j<y;j++){
					//arrXY[i][j] = (StepX*i)+","+(StepY*j);
					arrXY[i][j] = (StepY*i)+","+(StepX*j);
				}
			}

			// Show Array arrXY
			/**
			for(int i=0;i<arrXY.length;i++){
				for(int j=0;j<arrXY[i].length;j++){
					System.out.println("arrXY["+i+"]["+j+"]="+arrXY[i][j]+" ## "+arrXY[i][j].substring(0,arrXY[i][j].indexOf(','))+" ### "+arrXY[i][j].substring(arrXY[i][j].indexOf(',')+1,arrXY[i][j].length()));
				}
			}
			**/

			arrTable = new int[x][y];
			for(int i=0;i<x;i++){
				for(int j=0;j<y;j++){
					if((i%2) == 0){
						if((j%2) == 0){
						arrTable[i][j] = 1;
						}else{
						arrTable[i][j] = 0;
						}
					}else{
						if((j%2) == 0){
						arrTable[i][j] = 0;
						}else{
						arrTable[i][j] = 1;
						}
					}
					//System.out.println(i+","+j+"  =  "+arrTable[i][j]);
				}
			}


			// Set 
			arrUse = new int[x][y];
			arrUndo = new int[x][y];
			for(int i=0;i<arrUse.length;i++){
				for(int j=0;j<arrUse[i].length;j++){
					arrUse[i][j] = 0;
				}
			}

			arrUse[0][0] = 1;
			arrUse[0][2] = 1;
			arrUse[0][4] = 1;
			arrUse[0][6] = 1;
			arrUse[1][1] = 1;
			arrUse[1][3] = 1;
			arrUse[1][5] = 1;
			arrUse[1][7] = 1;

			arrUse[6][0] = 2;
			arrUse[6][2] = 2;
			arrUse[6][4] = 2;
			arrUse[6][6] = 2;
			arrUse[7][1] = 2;
			arrUse[7][3] = 2;
			arrUse[7][5] = 2;
			arrUse[7][7] = 2;


		}

		public void paint(Graphics g) {

		// Translate X Y
		g.translate((((getWidth()-1) - (StepX*y))/2),(((getHeight()-1) - (StepY*x))/2));

		g.setColor(255, 255, 255) ;
		g.fillRect(0, 0, getWidth() , getHeight() ) ;
		
		//check Grid
		//g.setColor(0x00ff00) ;
		//g.drawLine(0,(getHeight()-1),getWidth() ,(getHeight()-1)) ;

		//g.setColor(0x00ff00) ;
		//g.drawLine((getWidth()-1),0,(getWidth()-1) ,getHeight()) ;
		
		g.setColor(0xff0000) ;

		// Width
		//System.out.println("x="+x);
		//System.out.println("StepX*="+(StepX*x));

		for(int i=0;i<=x;i++){
		g.setColor(0xff0000) ;
		int j = StepY*i;
		g.drawLine(0,j,(StepX*y) ,j) ;
		//System.out.println("StepX="+j);
		 }

		// Heigth
		//System.out.println("-----------------");
		//System.out.println("Height="+getHeight());
		//System.out.println("Width="+getWidth());
		//System.out.println("y="+y);
		//System.out.println("StepX*y="+(StepY*x));

		for(int i=0;i<=y;i++){
		 g.setColor(0xff0000) ;
		int j = StepX*i;
		g.drawLine(j,0,j,(StepY*x)) ;
		//System.out.println("StepY="+j);
		}

		// Create Table
		//g.setColor(0x0000ff) ;
		//g.drawRect((moveX+1),(moveY+1), (StepX-2), (StepY-2)); 

		// Create Table 
		for(int i=0;i<arrTable.length;i++){
				for(int j=0;j<arrTable[i].length;j++){
					if(arrTable[i][j] == 1){
					//System.out.println(i+","+j+"="+arrTable[i][j]);
					newXY = arrXY[i][j];
					NewX = Integer.parseInt(newXY.substring(0,newXY.indexOf(',')));
					NewY = Integer.parseInt(newXY.substring(newXY.indexOf(',')+1,newXY.length()));
					g.setColor(0x000000) ;
					g.fillRect((NewY+1),(NewX+1), (StepX-1), (StepY-1)); 
					//g.fillRect((NewX+1),(NewY+1), (StepX-1), (StepY-1)); 
					}
			}
		}

		// DrawArc
		for(int i=0;i<arrUse.length;i++){
				for(int j=0;j<arrUse[i].length;j++){

					if(arrUse[i][j] == 1){
					//System.out.println(i+","+j+"="+arrUse[i][j]);
					newXY = arrXY[i][j];
					NewX = Integer.parseInt(newXY.substring(0,newXY.indexOf(',')));
					NewY = Integer.parseInt(newXY.substring(newXY.indexOf(',')+1,newXY.length()));
					g.setColor(0xFFCC33) ;
					g.fillArc((NewY+1),(NewX+1),(StepX-1),(StepY-1),0,360); 
					}

					if(arrUse[i][j] == 10){
					//System.out.println(i+","+j+"="+arrUse[i][j]);
					newXY = arrXY[i][j];
					NewX = Integer.parseInt(newXY.substring(0,newXY.indexOf(',')));
					NewY = Integer.parseInt(newXY.substring(newXY.indexOf(',')+1,newXY.length()));
					g.setColor(0xFFCC33) ;
					g.fillArc((NewY+1),(NewX+1),(StepX-1),(StepY-1),0,360); 
					g.setColor(0x000000) ;
					//g.fillArc((NewY+4),(NewX+4),(StepX-9),(StepY-9),0,360); 
					g.drawLine((NewY+1),(NewX+1),(NewY+(StepX-1)),(NewX+(StepY-1))); 
					g.drawLine((NewY-1)+StepX,(NewX+1),(NewY+1),(NewX-1)+StepY); 
					}
					
					if(arrUse[i][j] == 2){
					newXY = arrXY[i][j];
					NewX = Integer.parseInt(newXY.substring(0,newXY.indexOf(',')));
					NewY = Integer.parseInt(newXY.substring(newXY.indexOf(',')+1,newXY.length()));
					g.setColor(0x0033CC) ;
					g.fillArc((NewY+1),(NewX+1),(StepX-1),(StepY-1),0,360); 
					}

					if(arrUse[i][j] == 20){
					newXY = arrXY[i][j];
					NewX = Integer.parseInt(newXY.substring(0,newXY.indexOf(',')));
					NewY = Integer.parseInt(newXY.substring(newXY.indexOf(',')+1,newXY.length()));
					g.setColor(0x0033CC) ;
					g.fillArc((NewY+1),(NewX+1),(StepX-1),(StepY-1),0,360); 
					g.setColor(0x000000) ;
					//g.fillArc((NewY+4),(NewX+4),(StepX-9),(StepY-9),0,360); 
					g.drawLine((NewY+1),(NewX+1),(NewY+(StepX-1)),(NewX+(StepY-1))); 
					g.drawLine((NewY-1)+StepX,(NewX+1),(NewY+1),(NewX-1)+StepY); 
					}
					
				}
		}

		// ---------
		newXY = arrXY[xi][yi];
		NewX = Integer.parseInt(newXY.substring(0,newXY.indexOf(',')));
		NewY = Integer.parseInt(newXY.substring(newXY.indexOf(',')+1,newXY.length()));
		g.setColor(newColor) ;
		g.drawRect((NewY+2),(NewX+2), (StepX-4), (StepY-4)); 
		//g.fillRect((NewY+1),(NewX+1), (StepX-1), (StepY-1)); 

		// Show You Win & You Los
		//(((getWidth()-1) - (StepX*y))/2),(((getHeight()-1) - (StepY*x))/2));

		if(youWin == 1){
		g.setColor(0x00ff00);
		g.fillRect(1,(StepY*x/2)-25,(StepX*y)-1,50); 
		g.setColor(0xff0000);
		g.drawRect(3,(StepY*x/2)-20,(StepX*y)-6,40); 
		g.drawString("YOU WIN", (StepX*y/2)-20, (StepY*x/2)-7, Graphics.TOP|Graphics.LEFT);
		}
		
		if(youLos == 1){
		g.setColor(0x00ff00);
		g.fillRect(1,(StepY*x/2)-25,(StepX*y)-1,50); 
		g.setColor(0xff0000);
		g.drawRect(3,(StepY*x/2)-20,(StepX*y)-6,40); 
		g.drawString("YOU LOS", (StepX*y/2)-20, (StepY*x/2)-7, Graphics.TOP|Graphics.LEFT);
		}

		}


		// Start keyPress Move
		/**
		protected void keyPressed(int keyCode){
			if(keyCode == KEY_NUM1){
				repaint();
			}
		}
		**/
		// End keyPress Move

		// Start  Key Game
		public void keyPressed(int keyCode){
			if(getGameAction(keyCode) == Canvas.FIRE){
					if(moveKey == 0){
						if((arrUse[xi][yi] == 2) || (arrUse[xi][yi] == 20)){
							
							// Start Undo Array
								for(int i=0;i<arrUse.length;i++){
									for(int j=0;j<arrUse[i].length;j++){
										arrUndo[i][j] = arrUse[i][j];
									}
								}
							// End Undo Array

							moveKey = 1;
							xii = xi;
							yii = yi;
							newColor = 0x339933F;
							repaint();
						}
					}else{
						if(!(xii == xi && yii == yi)){
							// ตรวจสอบการเดินของผู้เล่น ที่ยังไม่ได้เป็น ฮอท
							if(arrUse[xi][yi] == 0 && arrUse[xii][yii] == 2 && xii != xi && yii != yi && arrTable[xi][yi] == 1 && (yi == yii+(xi-xii) || yi == yii-(xi-xii))){
								// ตรวจสอบว่าเป็นการเดิน ไม่ได้ กิน
								if((xi == xii-1) && ((yi == yii+1) || (yi == yii-1))){
									moveKey = 0;
									newColor = 0xFF9933;
									if(xi == 0){
									arrUse[xi][yi] = 20;
									}else{
									arrUse[xi][yi] = arrUse[xii][yii];
									}
									arrUse[xii][yii] = 0;
									repaint();
									// Computer RUN
									ComRun();
								}
								// ตรวจสอบว่าเป็นการกิน
								if((xi == xii-2) && (yi == yii+2)){
									if(arrUse[xii-1][yii+1] != 0){
										moveKey = 0;
										newColor = 0xFF9933;
										if(xi == 0){
										arrUse[xi][yi] = 20;
										}else{
										arrUse[xi][yi] = arrUse[xii][yii];
										}
										arrUse[xii-1][yii+1] = 0;
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}
								if((xi == xii-2) && (yi == yii-2)){
									if(arrUse[xii-1][yii-1] != 0){
										moveKey = 0;
										newColor = 0xFF9933;
										if(xi == 0){
										arrUse[xi][yi] = 20;
										}else{
										arrUse[xi][yi] = arrUse[xii][yii];
										}
										arrUse[xii-1][yii-1] = 0;
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}
							}

							// ตรวจสอบการเดินของผู้เล่น ที่เป็น ฮอท
							if(arrUse[xi][yi] == 0 && arrUse[xii][yii] == 20 && xii != xi && yii != yi && arrTable[xi][yi] == 1 && (yi == yii+(xi-xii) || yi == yii-(xi-xii))){
								// ตรวจสอบว่าเป็นการเดิน ไม่ได้ กิน
								axyn = 0;
								if(axyn == 0){
									ax = xii+1;
									ay = yii+1;
									axy = 0;
									axyz = 0;
									while(ax <= xi && ay <= yi){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax++;
										ay++;
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}

								if(axyn == 0){
									ax = xii+1;
									ay = yii-1;
									axy = 0;
									axyz = 0;
									while(ax <= xi && ay >= yi){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax++;
										ay--;
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}
								if(axyn == 0){
									ax = xii-1;
									ay = yii+1;
									axy = 0;
									axyz = 0;
									while(ax >= xi && ay <= yi){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax--;
										ay++;
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}

								if(axyn == 0){
									ax = xii-1;
									ay = yii-1;
									axy = 0;
									axyz = 0;
									while((ax >= xi && ay >= yi)){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax--;
										ay--;
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}

								//ตรวจสอบว่าเป็นการเดินที่ กินได้
								if(axyn == 0){
									ax = xii+1;
									ay = yii+1;
									axy = 0;
									axyz = 0;
									while(ax <= xi && ay <= yi){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax++;
										ay++;
									}
									if(axy == 1){
										if(arrUse[xi-1][yi-1] == 1 || arrUse[xi-1][yi-1] == 10){
											axy = 0;
										}
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xi-1][yi-1] = 0;
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}

								if(axyn == 0){
									ax = xii+1;
									ay = yii-1;
									axy = 0;
									axyz = 0;
									while(ax <= xi && ay >= yi){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax++;
										ay--;
									}
									if(axy == 1){
										if(arrUse[xi-1][yi+1] == 1 || arrUse[xi-1][yi+1] == 10){
											axy = 0;
										}
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xi-1][yi+1] = 0;
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}
								if(axyn == 0){
									ax = xii-1;
									ay = yii+1;
									axy = 0;
									axyz = 0;
									while(ax >= xi && ay <= yi){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax--;
										ay++;
									}
									if(axy == 1){
										if(arrUse[xi+1][yi-1] == 1 || arrUse[xi+1][yi-1] == 10){
											axy = 0;
										}
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xi+1][yi-1] = 0;
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}

								if(axyn == 0){
									ax = xii-1;
									ay = yii-1;
									axy = 0;
									axyz = 0;
									while((ax >= xi && ay >= yi)){
										if(arrUse[ax][ay] != 0){
											axy++;
										}
										axyz++;
										ax--;
										ay--;
									}
									if(axy == 1){
										if(arrUse[xi+1][yi+1] == 1 || arrUse[xi+1][yi+1] == 10){
											axy = 0;
										}
									}
									if(axy == 0 && axyz != 0){
										axyn++;
										moveKey = 0;
										newColor = 0xFF9933;
										arrUse[xi][yi] = arrUse[xii][yii];
										arrUse[xi+1][yi+1] = 0;
										arrUse[xii][yii] = 0;
										repaint();
										// Computer RUN
										ComRun();
									}
								}

							}
						}else{
							moveKey = 0;
							newColor = 0xFF9933;
							repaint();
						}
					}
			}

				switch(getGameAction(keyCode)){
					case Canvas.DOWN:
						if(xi< x-1){
							xi = xi + 1;
						}
						repaint();
					break;
					case Canvas.UP:
						if(xi > 0){
							xi = xi - 1;
						}
						repaint();
					break;
					case Canvas.LEFT:
						if(yi > 0){
							yi = yi - 1;
						}
						repaint();
					break;
					case Canvas.RIGHT:
						if(yi < y-1){
							yi = yi + 1;
						}
						repaint();
					break;
				}

		}
		// End  Key Game

		//Start Computer RUN
			public void ComRun(){
				int k = 0;
				for(int i=0;i<arrUse.length;i++){
					for(int j=0;j<arrUse[i].length;j++){
						if(arrUse[i][j] == 1 || arrUse[i][j] == 10){
						k++;
						//System.out.println(i+","+j+"="+arrUse[i][j]);
						}
					}
				}
				
				if(k != 0){
				int kk = 0;
				ja = 0;
				arrCom = new String[k];
				for(int i=0;i<arrUse.length;i++){
					for(int j=0;j<arrUse[i].length;j++){
						if(arrUse[i][j] == 1 || arrUse[i][j] == 10){
						arrCom[kk] = i+","+j;
						//System.out.println(arrCom[kk]);
						kk++;
						}
					}
				}
			//------------------------------------
			for(int i=0;i<arrCom.length;i++){
					NewTmpX = Integer.parseInt(arrCom[i].substring(0,arrCom[i].indexOf(',')));
					NewTmpY = Integer.parseInt(arrCom[i].substring(arrCom[i].indexOf(',')+1,arrCom[i].length()));

					if(ja == 0){
						if((NewTmpY+2 <= y-1) && (NewTmpX+2 <= x-1)){
							if(((arrUse[NewTmpX+1][NewTmpY+1] == 2) && (arrUse[NewTmpX+2][NewTmpY+2] == 0) ) || ((arrUse[NewTmpX+1][NewTmpY+1] == 20) && (arrUse[NewTmpX+2][NewTmpY+2] == 0))){
								if(NewTmpX+2 == x-1){
								arrUse[NewTmpX+2][NewTmpY+2] = 10;
								arrUse[NewTmpX+1][NewTmpY+1] = 0;
								}else{
								arrUse[NewTmpX+2][NewTmpY+2] = arrUse[NewTmpX][NewTmpY];
								arrUse[NewTmpX+1][NewTmpY+1] = 0;
								}
								arrUse[NewTmpX][NewTmpY] = 0;
								ja++;
							}
						}
					}
					
					if(ja == 0){
						if((NewTmpY-2 >= 0) && (NewTmpX+2 <= x-1)){
							if(((arrUse[NewTmpX+1][NewTmpY-1] == 2) && (arrUse[NewTmpX+2][NewTmpY-2] == 0)) || ((arrUse[NewTmpX+1][NewTmpY-1] == 20) && (arrUse[NewTmpX+2][NewTmpY-2] == 0))){
								if(NewTmpX+2 == x-1){
								arrUse[NewTmpX+2][NewTmpY-2] = 10;
								arrUse[NewTmpX+1][NewTmpY-1] = 0;
								}else{
								arrUse[NewTmpX+2][NewTmpY-2] = arrUse[NewTmpX][NewTmpY];
								arrUse[NewTmpX+1][NewTmpY-1] = 0;
								}
								arrUse[NewTmpX][NewTmpY] = 0;
								ja++;
							}
						} 
					}

					if(ja == 0){
						xn = NewTmpX+1;
						yn = NewTmpY+1;
						if(arrUse[NewTmpX][NewTmpY] == 10 && (xn+1 <= x-1 && yn+1 <= y-1)){
							xny = 0;
							hin = 0;
							while(xn < x-1 && yn < y-1 && ja == 0 && hin == 0){
									if(((arrUse[xn][yn] == 2) && (arrUse[xn+1][yn+1] == 0)) || ((arrUse[xn][yn] == 20) && (arrUse[xn+1][yn+1] == 0))){
									arrUse[xn+1][yn+1] = 10;
									arrUse[xn][yn] = 0;
									arrUse[NewTmpX][NewTmpY] = 0;
									ja++;
									}else if(arrUse[xn][yn] != 0){
										hin++;
									}

								xn++;
								yn++;
							}
						}
					}

					if(ja == 0){
						xn = NewTmpX+1;
						yn = NewTmpY-1;
						if(arrUse[NewTmpX][NewTmpY] == 10 && (xn+1 <= x-1 && yn-1 >= 0)){
							xny = 0;
							hin = 0;
							while(xn < x-1 && yn > 0 && ja == 0 && hin == 0){
									if(((arrUse[xn][yn] == 2) && (arrUse[xn+1][yn-1] == 0)) || ((arrUse[xn][yn] == 20) && (arrUse[xn+1][yn-1] == 0))){
									arrUse[xn+1][yn-1] = 10;
									arrUse[xn][yn] = 0;
									arrUse[NewTmpX][NewTmpY] = 0;
									ja++;
									}else if(arrUse[xn][yn] != 0){
										hin++;
									}
								xn++;
								yn--;
							}
						}
					}

					if(ja == 0){
						xn = NewTmpX-1;
						yn = NewTmpY-1;
						if(arrUse[NewTmpX][NewTmpY] == 10 && (xn-1 >= 0 && yn-1 >= 0)){
							xny = 0;
							hin = 0;
							while(xn > 0 && yn > 0 && ja == 0 && hin == 0){
									if(((arrUse[xn][yn] == 2) && (arrUse[xn-1][yn-1] == 0)) || ((arrUse[xn][yn] == 20) && (arrUse[xn-1][yn-1] == 0))){
									arrUse[xn-1][yn-1] = 10;
									arrUse[xn][yn] = 0;
									arrUse[NewTmpX][NewTmpY] = 0;
									ja++;
									}else if(arrUse[xn][yn] != 0){
										hin++;
									}
								xn--;
								yn--;
							}
						}
					}

					if(ja == 0){
						xn = NewTmpX-1;
						yn = NewTmpY+1;
						if(arrUse[NewTmpX][NewTmpY] == 10 && (xn-1 >= 0 && yn+1 <= y-1)){
							xny = 0;
							hin = 0;
							while(xn > 0 && yn < y-1 && ja == 0 && hin == 0){
									if(((arrUse[xn][yn] == 2) && (arrUse[xn-1][yn+1] == 0)) || ((arrUse[xn][yn] == 20) && (arrUse[xn-1][yn+1] == 0))){
									arrUse[xn-1][yn+1] = 10;
									arrUse[xn][yn] = 0;
									arrUse[NewTmpX][NewTmpY] = 0;
									ja++;
									}else if(arrUse[xn][yn] != 0){
										hin++;
									}
								xn--;
								yn++;
							}
						}
					}

			}
			//------------------------------------

			// Random
			
			if(ja == 0){
					for(int i=0;i<arrCom.length;i++){
						NewTmpX = Integer.parseInt(arrCom[i].substring(0,arrCom[i].indexOf(',')));
						NewTmpY = Integer.parseInt(arrCom[i].substring(arrCom[i].indexOf(',')+1,arrCom[i].length()));
						
						if((NewTmpX+1 == x-1 && NewTmpY+1 <= y-1)){
							if(arrUse[NewTmpX+1][NewTmpY+1] == 0 && NewTmpX+1 == x-1 && NewTmpY+1 <= y-1 && ja == 0){
									arrUse[NewTmpX+1][NewTmpY+1] = 10;
									arrUse[NewTmpX][NewTmpY] = 0;
									ja++;
							}
						}

						if((NewTmpX+1 == x-1 && NewTmpY-1 >= 0)){
							if(arrUse[NewTmpX+1][NewTmpY-1] == 0 && NewTmpX+1 == x-1 && NewTmpY-1 >= 0 && ja == 0){
									arrUse[NewTmpX+1][NewTmpY-1] = 10;
									arrUse[NewTmpX][NewTmpY] = 0;
									ja++;
							}
						}

					}

			}
			

			if(ja == 0){

					// Random
					ranNo++;
					ranTmp = System.currentTimeMillis() * ranNo;
					ranTmp = ranTmp^0x5DEECE66DL;
					ranTmp = ranTmp^100;

					String arrComTmp = arrCom[(int)(ranTmp%k)];
					int TimeTmp = (int)(ranTmp%2);

					NewX = Integer.parseInt(arrComTmp.substring(0,arrComTmp.indexOf(',')));
					NewY = Integer.parseInt(arrComTmp.substring(arrComTmp.indexOf(',')+1,arrComTmp.length()));
						if(TimeTmp == 0){
							if(arrUse[NewX][NewY] == 1){
									if((NewY+1 < y) && (NewX+1 < x)){
										if(arrUse[NewX+1][NewY+1] == 0){
											if(NewX+1 == x-1){
											arrUse[NewX+1][NewY+1] = 10;
											}else{
											arrUse[NewX+1][NewY+1] = arrUse[NewX][NewY];
											}
											arrUse[NewX][NewY] = 0;
										}else{
											ComRun();
										}
									}else{
									ComRun();
									}
							}else{
							// Start หากเป็น ฮอท
									if(TimeTmp == 0){
											if((NewY+1 < y) && (NewX+1 < x)){
												if(arrUse[NewX+1][NewY+1] == 0){
												arrUse[NewX+1][NewY+1] = arrUse[NewX][NewY];
												arrUse[NewX][NewY] = 0;
												}else{
												ComRun();
												}
											}else{
											ComRun();
											}
									}else{
											if((NewY-1 > 0) && (NewX+1 < x)){
												if(arrUse[NewX+1][NewY-1] == 0){
												arrUse[NewX+1][NewY-1] = arrUse[NewX][NewY];
												arrUse[NewX][NewY] = 0;
												}else{
												ComRun();
												}
											}else{
											ComRun();
											}

									}
							// End หากเป็น ฮอท
							}
						}else{
								if(arrUse[NewX][NewY] == 1){
									if((NewY-1 >= 0) && (NewX+1 < x)){
										if(arrUse[NewX+1][NewY-1] == 0){
											if(NewX+1 == x-1){
											arrUse[NewX+1][NewY-1] = 10;
											}else{
											arrUse[NewX+1][NewY-1] = arrUse[NewX][NewY];
											}
											arrUse[NewX][NewY] = 0;
										}else{
											ComRun();
										}
									}else{
										ComRun();
									}
								}else{
								// Start หากเป็น ฮอท
									if(TimeTmp == 0){
											if((NewY+1 < y) && (NewX-1 > 0)){
												if(arrUse[NewX-1][NewY+1] == 0){
												arrUse[NewX-1][NewY+1] = arrUse[NewX][NewY];
												arrUse[NewX][NewY] = 0;
												}else{
												ComRun();
												}
											}else{
											ComRun();
											}
									}else{
											if((NewY-1 > 0) && (NewX-1 > 0)){
												if(arrUse[NewX-1][NewY-1] == 0){
												arrUse[NewX-1][NewY-1] = arrUse[NewX][NewY];
												arrUse[NewX][NewY] = 0;
												}else{
												ComRun();
												}
											}else{
											ComRun();
											}

								}
							// End หากเป็น ฮอท
								}
				}
			}
				}

// Check your win
int win = 0;
for(int i=0;i<arrUse.length;i++){
	for(int j=0;j<arrUse[i].length;j++){
		if(arrUse[i][j] == 1 || arrUse[i][j] == 10){
		win++;
		//System.out.println(i+","+j+"="+arrUse[i][j]);
		}
	}
}

if(win == 0){
//System.out.println("Your Win");
youWin = 1;
}

// Check your loss
int los = 0;
for(int i=0;i<arrUse.length;i++){
	for(int j=0;j<arrUse[i].length;j++){
		if(arrUse[i][j] == 2 || arrUse[i][j] == 20){
		los++;
		//System.out.println(i+","+j+"="+arrUse[i][j]);
		}
	}
}

if(los == 0){
//System.out.println("Your Los");
youLos = 1;
}


			}
//End Computer RUN

// Start  Undo Game
public void undoGame(){
for(int i=0;i<arrUndo.length;i++){
	for(int j=0;j<arrUndo[i].length;j++){
		arrUse[i][j] = arrUndo[i][j];
	}
}
repaint();
}
// End  Undo Game

 }

public class Project extends MIDlet implements CommandListener
{
	 Display display = null;
	 MyCanvas c;
	 public Project(){}
	 public void startApp(){
	  c = new MyCanvas(8,8);
	  display = Display.getDisplay(this);
	  
	  Command undoGame = new Command("Undo",Command.BACK,0);
	  Command newGame = new Command("New",Command.SCREEN,0);
	  Command exitGame = new Command("Exit",Command.SCREEN,0);

	  c.addCommand(newGame);
	  c.addCommand(undoGame);
	  c.addCommand(exitGame);

	  c.setCommandListener(this);
	  display.setCurrent(c);
	 }

	 public void undoGame(){
		c.undoGame();
	 }
	 public void pauseApp(){}
	 public void destroyApp(boolean unconditional){
	 notifyDestroyed();
	 }

	 public void commandAction(Command cc, Displayable d){
		 String label = cc.getLabel();
		 if(label.equals("Undo")){
			//System.out.println("Command Undo");
			undoGame();
		 }else if(label.equals("New")){
			//System.out.println("Command New");
			startApp();
		 }else if(label.equals("Exit")){
			//System.out.println("Command Exit");
			destroyApp(true);
		 }
    }
}

