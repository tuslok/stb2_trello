package pl.akademiaqa.requests.board;

import io.restassured.response.Response;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class ReadBoardRequest {

    public static Response readBoardResponse(String boardId){

        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .get(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract().response();
    }
}
