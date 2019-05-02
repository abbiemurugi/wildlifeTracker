import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class EndangeredAnimalTest {
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
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("elephant","no",  "healthy", "adult");
        assertEquals("elephant", myEndangeredAnimal.getName());
    }

    @Test
    public void return_getHealth_string(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("elephant","no",  "healthy", "adult");
        assertEquals("healthy", myEndangeredAnimal.getHealth());
    }

    @Test
    public void return_getAge_string(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("elephant","no",  "healthy", "adult");
        assertEquals("adult", myEndangeredAnimal.getAge());
    }

    @Test
    public void EndangeredAnimal_instanciatesWithHealth_String(){
        assertEquals("healthy", EndangeredAnimal.HEALTH_HEALTHY);

    }

    @Test
    public void EndangeredAnimal_instanciatesWithHealthIll_String(){
        assertEquals("ill",EndangeredAnimal.HEALTH_ILL);

    }

    @Test
    public void EndangeredAnimal_instanciatesWithHealthOkay(){
        assertEquals("okay", EndangeredAnimal.HEALTH_OKAY);

    }

    @Test
    public void EndangeredAnimal_instanciatesWithAgeYoung_String(){
        assertEquals("young", EndangeredAnimal.AGE_YOUNG);

    }

    @Test
    public void EndangeredAnimal_instanciatesWithAgeNewBorn_String(){
        assertEquals("newborn", EndangeredAnimal.AGE_NEWBORN);

    }


    @Test
    public void EndangeredAnimal_instanciatesWithAgeAdult_String(){
        assertEquals("adult", EndangeredAnimal.AGE_ADULT);

    }

    @Test
    public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("elephant", "no", "healthy", "adult");
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("elephant", "no", "healthy", "adult");
        secondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
    }
    
}
