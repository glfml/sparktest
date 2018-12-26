package testspark.controller;

import spark.Request;
import spark.Response;
import spark.Route;
import testspark.InvalidSequenceException;
import testspark.ResponseDTO;
import testspark.SequenceFacade;

public class SequenceController {

    SequenceFacade sf = new SequenceFacade();
    /*
     * /register => unique token
     * /next?unique_token => or 400
     *
     */

    public Route register = (Request request, Response response) -> {
        return new ResponseDTO(String.valueOf(sf.create()));
    };

    public Route next = (Request request, Response response) -> {
        String sequence = request.params("sequence");
        try {
            return new ResponseDTO(String.valueOf(sf.getNext(String.valueOf(sequence))));
        } catch (InvalidSequenceException e) {
            response.status(400);
        }

        return new ResponseDTO("Invalid");
    };
}
