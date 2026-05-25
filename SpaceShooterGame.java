import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

// Player class
class Player {
    int x, y, width, height;
    int speed = 15;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width =40;
        this.height = 40;
    }

    public void moveLeft() { x -= speed; if (x < 0) x = 0; }
    public void moveRight(int screenWidth) { x += speed; if (x + width > screenWidth) x = screenWidth - width; }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height); // simple rectangle player
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

// Bullet class
class Bullet {
    int x, y, width = 5, height = 10;
    int speed = 10;

    public Bullet(int x, int y) { this.x = x; this.y = y; }

    public void update() { y -= speed; }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

// Enemy class
class Enemy {
    int x, y, width = 40, height = 40;
    int speed = 4;

    public Enemy(int x, int y) { this.x = x; this.y = y; }

    public void update() { y += speed; }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height); // simple rectangle enemy
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

// Main Game Panel
class GamePanel extends JPanel implements ActionListener, KeyListener {
    Player player;
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    Timer timer;
    Random rand = new Random();
    int score = 0;
    boolean gameOver = false;

    public GamePanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        player = new Player(280, 500);
        timer = new Timer(30, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    private void spawnEnemy() {
        int x = rand.nextInt(560); // screen width - enemy width
        enemies.add(new Enemy(x, 0));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameOver) {
            player.draw(g);
            for (Bullet b : bullets) b.draw(g);
            for (Enemy e : enemies) e.draw(g);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER!", 200, 300);
            g.drawString("Final Score: " + score, 200, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Update bullets
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                b.update();
                if (b.y < 0) bullets.remove(i--);
            }

            // Update enemies
            for (int i = 0; i < enemies.size(); i++) {
                Enemy en = enemies.get(i);
                en.update();
                // Check collision with player
                if (en.getBounds().intersects(player.getBounds())) {
                    gameOver = true;
                    timer.stop();
                }
            }

            // Check bullet-enemy collisions
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy en = enemies.get(j);
                    if (b.getBounds().intersects(en.getBounds())) {
                        bullets.remove(i--);
                        enemies.remove(j);
                        score++;
                        break;
                    }
                }
            }

            // Randomly spawn enemies
            if (rand.nextInt(30) == 0) spawnEnemy();

            repaint();
        }
    }

    // Key controls
    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) player.moveLeft();
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.moveRight(600);
            if (e.getKeyCode() == KeyEvent.VK_SPACE) bullets.add(new Bullet(player.x + 17, player.y));
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}

// Main Class
public class SpaceShooterGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Space Shooter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
