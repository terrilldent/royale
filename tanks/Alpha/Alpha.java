import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;

/**
 * Alpha Bot
 */
public class Alpha extends Bot {

    // The main method starts our bot
    public static void main(String[] args) {
        new Alpha().start();
    }

    // Constructor, which loads the bot config file
    Alpha() {
        super(BotInfo.fromFile("Alpha.json"));
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        // Repeat while the bot is running
        while (isRunning()) {
            forward(15);
            turnGunRight(40);
            turnLeft(10);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(1);
        turnGunRight(10);
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(BulletHitBotEvent e) {
        // Calculate the bearing to the direction of the bullet
        var bearing = calcBearing(e.getBullet().getDirection());

        // Turn 20 degrees to the bullet direction based on the bearing
        turnLeft(20 - bearing);
    }
}
