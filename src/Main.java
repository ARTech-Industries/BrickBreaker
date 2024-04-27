import GUIControls.Window;
import Game.GamePanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        new Window();
        GamePanel game = new GamePanel();
        Window.setContentPane(game);
        Window.setResizeable(false);
       // game.startGame();
        
        Window.setTitle("Press S to start");
        game.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_T) {
                    game.startGame();
                  System.out.println("game start");
                }
            }
        });
    
    }
}
