package testspark;

import spark.servlet.SparkApplication;
import testspark.controller.FirstController;
import testspark.controller.SequenceController;
import testspark.transformers.JsonTransformer;
import static spark.Spark.*;

public class Hello implements SparkApplication {
    private FirstController firstControllerInstance;

    public Hello(FirstController firstControllerInstance) { // Como lo inyecto??
        this.firstControllerInstance = firstControllerInstance;
    }

    private void setup()  {
        get(Path.ROOT, FirstController::main);
        get("/2", firstControllerInstance.closure, new JsonTransformer());

        SequenceController c = new SequenceController();
        get("/register", c.register, new JsonTransformer());
        get(Path.NEXT, c.next, new JsonTransformer());

        before((request, response) -> {
//            System.out.println(request);
        });

//        after((request, response) -> {
//            System.out.println(response.body());
//            if (response.status() == 400) {
//                String body = "{'error':'" + response.body() + "'}";
//                response.body(body);
//            }
////            response.body("MODIFICADO");
//        });
    }

    public static void main(String[] args) {
        (new Hello(new FirstController())).setup();
    }


    @Override
    public void init() {
        System.out.println("SE EJECUTA INIT");
        this.firstControllerInstance = new FirstController();
        this.setup();
    }

    @Override
    public void destroy() {
        System.out.println("SE EJECUTA DESTROY");
    }
}