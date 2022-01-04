package top.bchome.snake;

import top.bchome.snake.entity.Snake;
import top.bchome.snake.operation.SnakeOperation;
import top.bchome.snake.operation.SnakeOperationImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author billy hu
 */
public class Main {
    static JFrame frame ;
    static Container main ;

    /**
     * create gui
     * @param snake
     * @param operation
     * @return
     */
    private static JFrame createAndShowGUI(Snake snake, SnakeOperation operation) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        int width = snake.getWidth();
        int height = snake.getHeight();
        // 创建及设置窗口
        frame = new JFrame("Snake created by billy");
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/3,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        Container head = new Container();
        head.setLayout(new GridLayout(width,height));
        Button left = new Button("left");
        left.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation.turnLeft(snake);
                refresh(snake);

            }
        });
        left.setVisible(true);
        Button right = new Button("right");
        right.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation.turnRight(snake);
                refresh(snake);
            }
        });
        right.setVisible(true);
        Button start = new Button("start");
        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snake.reset();
                refresh(snake);
                new Thread(()->{
                    refresh(snake);
                    while (true){
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ae) {
                            ae.printStackTrace();
                        }
                        try {
                            operation.goAhead(snake);
                        }catch (IllegalArgumentException ex){
                            JDialog jDialog = new JDialog(frame);
                            jDialog.setSize(100,100);
                            jDialog.setLocationRelativeTo(main);
                            jDialog.setTitle("tips");
                            jDialog.add(new Label("game over!!!"));
                            jDialog.setVisible(true);
                            break;
                        }
                        refresh(snake);
                    }
                }).start();
            }
        });
        start.setVisible(true);
        head.add(start);
        head.add(left);
        head.add(right);
        frame.add(head);
        main = new Container();
        main.setLayout(new GridLayout(width,height));

        for (int[] matrix : snake.getMatrix()) {
            for (int i : matrix) {
                Label label = new Label();
                label.setBackground(i==0? Color.WHITE:Color.BLUE);
                main.add(label);
            }
        }
        frame.add(main);


        // 显示窗口
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    public static void main(String[] args) {
        int width= 30 ,height = 30;
        Snake snake = new Snake(height, width,false);
        SnakeOperationImpl snakeOperation = new SnakeOperationImpl();
        createAndShowGUI(snake,snakeOperation);

    }


    /**
     * refresh map
     * @param snake
     */
    static void refresh(Snake snake){
        int[][] matrix = snake.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Component component = main.getComponent(j + snake.getHeight() * i);
                component.setBackground(matrix[i][j] == 0? Color.WHITE:Color.BLUE);
            }
        }
    }


}
