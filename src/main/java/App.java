import spark.ModelAndView;
import java.util.HashMap;
import java.util.Map;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String endangered = request.queryParams("endangered").toLowerCase();
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            String health = request.queryParams("health").toLowerCase();
            String age = request.queryParams("age").toLowerCase();
            Animal newAnimal = new Animal(name, endangered);

            response.redirect("/");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


    }
}