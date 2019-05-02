import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AnimalTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "kingcubby", "abbie");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    @Test
    public void return_getName_string(){
    Animal myAnimal = new Animal("lion","no");
    assertEquals("lion", myAnimal.getName());
    }

    @Test
    public void return_setName_string(){
        Animal myAnimal = new Animal("lion", "no");
        myAnimal.setName("lioness");
        assertEquals("lioness", myAnimal.getName());
    }

    @Test
    public void save_assignsIdToObject() {
        Animal myAnimal = new Animal("lion", "no");
        myAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(myAnimal.getId(), myAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = new Animal("lion", "no");
        firstAnimal.save();
        Animal secondAnimal = new Animal("tiger", "no");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }
}

