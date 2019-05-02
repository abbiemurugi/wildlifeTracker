import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SightingTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "kingcubby", "abbie");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    @Test
    public void return_getAnimalId_int(){
        Sighting mySighting = new Sighting(1, "zone A", "bishop");
        assertEquals(1, mySighting.getAnimalId());
    }

    @Test
    public void return_getLocation_string(){
        Sighting mySighting = new Sighting(1, "zone A", "bishop");
        assertEquals("zone A", mySighting.getLocation());
    }

    @Test
    public void return_getRangerName_string(){
        Sighting mySighting = new Sighting(1, "zone A", "bishop");
        assertEquals("bishop", mySighting.getRangerName());
    }

    @Test
    public void return_setAnimalId_int(){
        Sighting mySighting = new Sighting(1, "zone A", "bishop");
        mySighting.setAnimalId(2);
        assertEquals(2,mySighting.getAnimalId() );
    }

    @Test
    public void return_setLocation_string(){
        Sighting mySighting = new Sighting(1, "zone A", "bishop");
        mySighting.setLocation("near the river");
        assertEquals("near the river", mySighting.getLocation());
    }

    @Test
    public void return_setRangerName_string(){
        Sighting mySighting = new Sighting(1, "zone A", "bishop");
        mySighting.setRangerName("ben");
        assertEquals("ben", mySighting.getRangerName());
    }

    @Test
    public void save_assignsIdToObject() {
        Sighting mySighting = new Sighting(1, "zone A", "bishop");
        mySighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertNotEquals(mySighting.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sighting firstSighting = new Sighting(1, "zone A", "bishop");
        firstSighting.save();
        Sighting secondSighting = new Sighting(1, "near the river", "Ann");
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }
}
