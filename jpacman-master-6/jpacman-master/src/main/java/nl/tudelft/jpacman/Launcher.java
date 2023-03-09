package nl.tudelft.jpacman;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.ui.Action;
import nl.tudelft.jpacman.ui.PacManUI;
import nl.tudelft.jpacman.ui.PacManUiBuilder;

/**
 * Creates and launches the JPacMan UI.
 *
 * @author Jeroen Roosen
 */
@SuppressWarnings("PMD.TooManyMethods")
public class Launcher {

    private static final PacManSprites SPRITE_STORE = new PacManSprites();

    private static String levelMap ;
    public static void board(String levelName) {
        levelMap = levelName;
    }



    private static PacManUI pacManUI;
    private static Game game;

    /**
     * @return The game object this launcher will start when {@link #launch()}
     *         is called.
     */
    public static Game getGame() {
        return game;
    }

    /**
     * The map file used to populate the level.
     *
     * @return The name of the map file.
     */
    protected static String getLevelMap() {
        return levelMap;
    }

    /**
     * Set the name of the file containing this level's map.
     *
     * @param fileName
     *            Map to be used.
     * @return Level corresponding to the given map.
     */
    public Launcher withMapFile(String fileName) {
        levelMap = fileName;
        return this;
    }

    /**
     * Creates a new game using the level from {@link #makeLevel()}.
     *
     * @return a new Game.
     */
    public static Game makeGame() {
        GameFactory gf = getGameFactory();
        Level level = makeLevel();
        game = gf.createSinglePlayerGame(level, loadPointCalculator());
        return game;
    }

    private static PointCalculator loadPointCalculator() {
        return new PointCalculatorLoader().load();
    }

    /**
     * Creates a new level. By default this method will use the map parser to
     * parse the default board stored in the <code>board1.txt</code> resource.
     *
     * @return A new level.
     */
    public static Level makeLevel() {
        try {
            return getMapParser().parseMap(getLevelMap());
        } catch (IOException e) {
            throw new PacmanConfigurationException(
                "Unable to create level, name = " + getLevelMap(), e);
        }
    }

    /**
     * @return A new map parser object using the factories from
     *         {@link #getLevelFactory()} and {@link #getBoardFactory()}.
     */
    protected static MapParser getMapParser() {
        return new MapParser(getLevelFactory(), getBoardFactory());
    }

    /**
     * @return A new board factory using the sprite store from
     *         {@link #getSpriteStore()}.
     */
    protected static BoardFactory getBoardFactory() {
        return new BoardFactory(getSpriteStore());
    }

    /**
     * @return The default {@link PacManSprites}.
     */
    protected static PacManSprites getSpriteStore() {
        return SPRITE_STORE;
    }

    /**
     * @return A new factory using the sprites from {@link #getSpriteStore()}
     *         and the ghosts from {@link #getGhostFactory()}.
     */
    protected static LevelFactory getLevelFactory() {
        return new LevelFactory(getSpriteStore(), getGhostFactory(), loadPointCalculator());
    }

    /**
     * @return A new factory using the sprites from {@link #getSpriteStore()}.
     */
    protected static GhostFactory getGhostFactory() {
        return new GhostFactory(getSpriteStore());
    }

    /**
     * @return A new factory using the players from {@link #getPlayerFactory()}.
     */
    protected static GameFactory getGameFactory() {
        return new GameFactory(getPlayerFactory());
    }

    /**
     * @return A new factory using the sprites from {@link #getSpriteStore()}.
     */
    protected static PlayerFactory getPlayerFactory() {
        return new PlayerFactory(getSpriteStore());
    }

    /**
     * Adds key events UP, DOWN, LEFT and RIGHT to a game.
     *
     * @param builder
     *            The {@link PacManUiBuilder} that will provide the UI.
     */
    protected static void addSinglePlayerKeys(final PacManUiBuilder builder) {
        builder.addKey(KeyEvent.VK_UP, moveTowardsDirection(Direction.NORTH))
            .addKey(KeyEvent.VK_DOWN, moveTowardsDirection(Direction.SOUTH))
            .addKey(KeyEvent.VK_LEFT, moveTowardsDirection(Direction.WEST))
            .addKey(KeyEvent.VK_RIGHT, moveTowardsDirection(Direction.EAST));
    }

    private static Action moveTowardsDirection(Direction direction) {
        return () -> {
            assert game != null;
            getGame().move(getSinglePlayer(getGame()), direction);
        };
    }

    private static Player getSinglePlayer(final Game game) {
        List<Player> players = game.getPlayers();
        if (players.isEmpty()) {
            throw new IllegalArgumentException("Game has 0 players.");
        }
        return players.get(0);
    }

    /**
     * Creates and starts a JPac-Man game.
     */
    public static void launch() {
        makeGame();
        PacManUiBuilder builder = new PacManUiBuilder().withDefaultButtons();
        addSinglePlayerKeys(builder);
        pacManUI = builder.build(getGame());
        pacManUI.start();
    }

    /**
     * Disposes of the UI. For more information see
     * {@link javax.swing.JFrame#dispose()}.
     *
     * Precondition: The game was launched first.
     */
    public void dispose() {
        assert pacManUI != null;
        pacManUI.dispose();
    }

    /**
     * Main execution method for the Launcher.
     *
     * @param args
     *            The command line arguments - which are ignored.
     * @throws IOException
     *             When a resource could not be read.
     */
    public static void main(String[] args) throws IOException {
        new Launcher().launch();
    }
}