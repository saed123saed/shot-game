import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ShooterGame extends JFrame {
    private int playerX, playerY;
    private int bulletX, bulletY;
    private boolean isShooting = false;

    public ShooterGame() {
        playerX = 150;
        playerY = 350;
        bulletX = playerX;
        bulletY = playerY;

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bulletY -= 2;
                if (bulletY < 0) {
                    isShooting = false;
                    bulletY = playerY;
                }
                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT && playerX > 0) {
                    playerX -= 5;
                }
                if (keyCode == KeyEvent.VK_RIGHT && playerX < 300) {
                    playerX += 5;
                }
                if (keyCode == KeyEvent.VK_SPACE && !isShooting) {
                    isShooting = true;
                    bulletX = playerX + 15;
                    bulletY = playerY;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 400);
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 30, 30);
        g.setColor(Color.BLUE);
        g.fillRect(bulletX, bulletY, 2, 5);
    }

    public static void main(String[] args) {
        ShooterGame game = new ShooterGame();
        game.setSize(400, 400);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
