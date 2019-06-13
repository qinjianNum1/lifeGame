package lifeGame;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.JPanel;  
import javax.swing.Timer;  
  
/** 
 *  
  */
  
public class WorldMap extends JPanel {  
    private final int width = 22;   
    private final int cols=22;
    private final int rols=22;
    private final char WORLD_MAP_NONE = 'N';  
    private final char WORLD_MAP_ALIVE = 'A';  
    private char[][] nextStatus = new char[cols][rols];  
    private char[][] tempStatus = new char[cols][rols];  
  
    private Timer timer;  
  
    // 动画帧之间的延时，每秒60帧  
    private final int DELAY_TIME = 600;  
  
    public WorldMap() {  
    	game_cycle();  
    }  
  
    /** 
     * 画图形界面 
     *  
     */  
    @Override  
    protected void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        for (int i = 0; i < nextStatus.length; i++) {  
            for (int j = 0; j < nextStatus[i].length; j++) {  
                if (nextStatus[i][j] == WORLD_MAP_ALIVE) {  
                    g.fillRect( j * width, i * width, width, width);  
                   
                } else {  
                	 g.drawRect( j * width, i * width, width, width); 
                }  
            }  
        }  
    }  
  
    /** 
     * 改变细胞状态 
     *  
        */  
    void init_()       
    {
    	
    	for(int i=0;i<cols;i++)
    		for(int j=0;j<rols;j++)
    		{
    			 nextStatus[i][j]=tempStatus[i][j]=WORLD_MAP_NONE;
    		}
    }
    void reset()
    {
    	for(int i=0;i<cols;i++)
    		for(int j=0;j<rols;j++)
    		{
    	        if(Math.random()>0.5)
			        tempStatus[i][j]=WORLD_MAP_ALIVE;
		        else
		 	        tempStatus[i][j]=WORLD_MAP_NONE;
		        nextStatus[i][j]=tempStatus[i][j];
    		}
    }
    private void changeCellStatus() {  
        for (int row = 0; row < cols; row++) {  
            for (int col = 0; col < rols; col++) {  
                switch ( get_neighbort_count(row, col)) {  
                case 0:  
                case 1:  
                case 4:  
                case 5:  
                case 6:  
                case 7:  
                case 8:  
                    nextStatus[row][col] = WORLD_MAP_NONE;  
                    break;  
                case 2:  
                    nextStatus[row][col] = tempStatus[row][col];  
                    break;  
                case 3:  
                    nextStatus[row][col] = WORLD_MAP_ALIVE;  
                    break;  
                }  
            }  
        }  
        for (int row = 0; row < nextStatus.length; row++) {  
            for (int col = 0; col < nextStatus[row].length; col++) {  
                tempStatus[row][col] = nextStatus[row][col];  
            }  
        }
    }  
  
    /** 
     * 获取当前坐标点临近细胞个数 
     *  
         */  
    int get_neighbort_count(int c,int r)
    {
    	int count=0;
    	for(int i=-1;i<=1;i++)
    		for(int j=-1;j<=1;j++)
    		{
    			if((c+i<0)||(c+i>=cols)||(r+j>=rols)||(r+j<0))
    				continue;
    			if(tempStatus[c+i][r+j]==WORLD_MAP_ALIVE)
    					  count+=1;
    		}
    	if(tempStatus[c][r]==WORLD_MAP_ALIVE)
    		count-=1;
    	return count;
    }
    private void game_cycle() {
    	init_();
        // 创建计时器 
    	reset();    
        timer = new Timer(DELAY_TIME, new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                changeCellStatus();
                
                repaint();  
            }  
        });  
        // 开启计时器  
        timer.start();  
    }     
}  