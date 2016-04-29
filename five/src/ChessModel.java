
/**
 * Created by Administrator on 2015/5/19.
 */
public class ChessModel {
	public static final int BLACK = -1;
	public static final int WHITE = 1;
	public static final int SPACE = 0;
	public static final int WIDTH = 19;

	private int[][] data = new int[WIDTH][WIDTH];
	private int lastRow;
	private int lastCol;
	private int lastColor;

	public boolean putChess(int row, int col, int color) {
		if (isAvailable(row) && isAvailable(col)
				&& (color == BLACK || color == WHITE)) {
			if (data[row][col] == SPACE) {
				data[row][col] = color;
				lastRow = row;
				lastCol = col;
				lastColor = color;
				return true;
			}
		}
		return false;
	}

	private boolean isAvailable(int rowOrColumn) {
		return rowOrColumn >= 0 && rowOrColumn < WIDTH;
	}
//重置棋子
	public void resetChess(int row,int col){
		data[row][col]=SPACE;
	}
	public int[][] getChess() {
		int[][] result = new int[WIDTH][WIDTH];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = data[i][j];
			}
		}
		return result;
	}
//认输
	public void rschess(){
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<WIDTH;j++)
				data[i][j]=0;
			FiveGame.view.repaint();
		}
	}
//判断输赢
	public int whoWin(){
    	//依靠最后一颗棋子判断胜负
    	int row ,col;
    	//列判断 向上
    	int countOfrow = 1;
    	int i = 1;
    	while(true){
    		row = lastRow - i;
    		col =lastCol;
    		
    		if(row < 0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfrow++;
        		i++;
    		}else break;
    		//data[lastRow - i][lastCol]
    	}
    	//向下
    	i=1;                  //初始化变量
    	while(true){
    		row = lastRow + i;
    		col =lastCol;
    		
    		if(row > 18){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfrow++;
        		i++;
    		}else break;
    	}
    	
    	//行判断 向左
    	int countOfcol = 1;
    	i=1;
    	while(true){
    		row = lastRow;
    		col =lastCol - i;
    		
    		if(row < 0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfcol++;
        		i++;
    		}else break;
    	}
    	
    	//向右
    	i=1;
    	while(true){
    		row = lastRow;
    		col =lastCol + i;
    		
    		if(row > 18){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfcol++;
        		i++;
    		}else break;
    	}
    	
    	//  '\'左上       backslash
    	int countOfbs =1;            
    	i=1;
    	while(true){
    		row = lastRow - i;
    		col =lastCol  - i;
    		
    		if(row < 0 || col<0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfbs++;
        		i++;
    		}else break;
    	}
    	// ‘\’右下
    	i=1;
    	while(true){
    		row = lastRow + i;
    		col =lastCol  + i;
    		
    		if(row > 18 || col > 18){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfbs++;
        		i++;
    		}else break;
    	}
    	// '/'右上       slash
    	int countOfslash = 1;
    	i=1;
    	while(true){
    		row = lastRow - i;
    		col =lastCol  + i;
    		
    	//	if(row < 0 || col > 18){
    	//		break;
    	//	}
    		
    		if(data[row][col] == lastColor){
    			countOfslash++;
        		i++;
    		}else break;
    	}
    	//   '/'左下          slash
    	i =1;
    	while(true){
    		row = lastRow + i;
    		col =lastCol  - i;
    		
    		if(row >18  || col < 0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfslash++;
        		i++;
    		}else break;
    	}
    	
    	
    	
    	
    	
    	if(countOfrow >= 5 || countOfcol >=5 ||countOfbs >=5 || countOfslash >=5){
    		return lastColor;
    	}
    	
    	
        return 10;

    }
//悔棋
	public void regretChess() {
		// TODO 自动生成的方法存根
		data[lastRow][lastCol]=SPACE;
		FiveGame.view.repaint();
	}
}
