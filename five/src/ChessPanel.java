import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Administrator on 2015/5/19.
 */
public class ChessPanel extends JPanel {

    private int startX = 20;
    private int startY = 20;
    private int unit = 30;

    public ChessPanel() {
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int col = x / unit;
                //int col = (x+unit/2) / unit;
                if (x % unit > unit / 2) {
                    x++;
                }
                int y = e.getY();
                int row = y / unit;
                //int row =  (y+unit/2)/unit;
                if (y % unit > unit / 2) {
                    y++;
                }

                FiveGame.control.putChessInLocal(row, col);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPanel(g);
        drawChess(g);
      // g.drawImage(im,1000,700,this);
    }

    private void drawChess(Graphics g) {
        int[][] result = FiveGame.model.getChess();
        for (int row = 0; row < ChessModel.WIDTH; row++) {
            for (int col = 0; col < ChessModel.WIDTH; col++) {
                if (result[row][col] == ChessModel.BLACK) {
                    g.setColor(Color.black);
                    g.fillOval(startX + col * unit - unit / 2, startY + row * unit - unit / 2, unit, unit);
                } else if (result[row][col] == ChessModel.WHITE) {
                    g.setColor(Color.WHITE);
                    g.fillOval(startX + col * unit - unit / 2, startY + row * unit - unit / 2, unit, unit);

                }
            }
        }
    }

    private void drawPanel(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < ChessModel.WIDTH; i++) {
            g.drawLine(startX, startY + unit * i, startX + unit * (ChessModel.WIDTH - 1), startY + unit * i);
            g.drawLine(startX + unit * i, startY, startX + unit * i, startY + unit * (ChessModel.WIDTH - 1));

        }
    }
}
