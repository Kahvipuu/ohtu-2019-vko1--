package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    public StatisticsTest() {
    }

    /*    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
     */
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void parhaatPisteenTekijat() {
        Player t = stats.topScorers(1).get(0);
        assertEquals(t.getName(), "Gretzky");
    }

    @Test
    public void tiiminPelaajat() {
        List<Player> lp = stats.team("PIT");
        assertEquals(lp.get(0).getName(), "Lemieux");
    }

    @Test
    public void etsi() {
        assertEquals(stats.search("Kurri").getName(), "Kurri");
    }

    @Test
    public void etsiLoytamatta() {
        assertEquals(stats.search("nobody"), null);
    }

}
