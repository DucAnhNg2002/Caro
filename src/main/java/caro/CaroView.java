package caro;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class CaroView extends JFrame {
    private final CaroController caroController;
    private final JButton[][] buttons;
    private final int sizeButton = 45;
    private final int preBotMove[] = {0, 0};
    private boolean isEndGame = false;
    private boolean isWaiting = false;
    private static CaroView caroView = null;

    private CaroView() {
        caroController = CaroController.getInstance();
        int sizeMatrix = caroController.getSizeMatrix();

        this.setSize(sizeButton * sizeMatrix, sizeButton * sizeMatrix);
        this.setLocationRelativeTo(null);
        this.setTitle("Caro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        buttons = new JButton[sizeMatrix + 1][sizeMatrix + 1];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(sizeMatrix, sizeMatrix));

        for(int i = 1; i <= sizeMatrix; i++) {
            for(int j = 1; j <= sizeMatrix; j++) {
                JButton button = new JButton();
                Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
                button.setFont(font);
                button.setBackground(Color.WHITE);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setPreferredSize(new Dimension(sizeButton, sizeButton));
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    if(buttons[finalI][finalJ].getText().length() == 0) {
                        if(isEndGame == false) {
                            caroController.playerMove(finalI, finalJ);
                        }
                    }
                });
                panel.add(button);
                buttons[i][j] = button;
            }
        }
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
    public static CaroView getInstance() {
        if(caroView == null) {
            caroView = new CaroView();
        }
        return caroView;
    }
    public void playerMove(int x, int y) {
        buttons[x][y].setText("X");
    }
    public void botMove(int x, int y) {
        if(preBotMove[0] != 0 && preBotMove[1] != 0) {
            buttons[preBotMove[0]][preBotMove[1]].setForeground(Color.BLACK);
        }
        buttons[x][y].setText("O");
        buttons[x][y].setForeground(Color.RED);
        preBotMove[0] = x;
        preBotMove[1] = y;
    }
    public void endGame(int [][] listPointEnd) {
        for(int i = 0; i < listPointEnd.length; i++) {
            buttons[listPointEnd[i][0]][listPointEnd[i][1]].setForeground(Color.RED);
        }
        isEndGame = true;
    }
}
