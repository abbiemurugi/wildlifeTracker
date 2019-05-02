import org.sql2o.*;
import java.util.List;
import java.sql.Timestamp;


public class Sighting {
    private int animalId;
    private String location;
    private String rangerName;
    private Timestamp timestamp;
    private int id;


    public Sighting(int animalId, String location, String rangerName) {
        this.animalId = animalId;
        this.location = location;
        this.rangerName = rangerName;
    }

    public Sighting(int id) {
        this.id = id;
    }
    
    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


    public int getId() {
        return id;
    }

    public static List<Sighting> all() {
        String sql = "SELECT animalid, location, rangername, timestamp FROM sightings";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    @Override
    public boolean equals(Object otherSighting) {
        if (!(otherSighting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getAnimalId() == (newSighting.getAnimalId()) &&
                    this.getLocation().equals(newSighting.getLocation()) &&
                    this.getRangerName().equals(newSighting.getRangerName()) &&
                    this.getId() == newSighting.getId();

        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalid, location, rangername, timestamp ) VALUES (:animalid, :location, :rangername, now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalid", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("rangername", this.rangerName)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static Sighting find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }
}
