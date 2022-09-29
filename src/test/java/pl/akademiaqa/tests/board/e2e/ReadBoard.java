package pl.akademiaqa.tests.board.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.akademiaqa.requests.board.CreateBoardRequest;
import pl.akademiaqa.requests.board.DeleteBoardRequest;
import pl.akademiaqa.requests.board.ReadBoardRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReadBoardTest {

    private final String boardName = "Board do odczytu";
    private static String boardId;

    @Test
    @Order(1)
    void createNewBoardTest(){

        Map<String, String > queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response createBoardResponse = CreateBoardRequest.createBoardRequest(queryParams);
        Assertions.assertThat(createBoardResponse.statusCode()).isEqualTo(200);

        JsonPath json = createBoardResponse.jsonPath();
        boardId = json.getString("id");
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
    }

    @Test
    @Order(2)
    void readBoardTest(){
        final Response readResponse = ReadBoardRequest.readBoardResponse(boardId);

        Assertions.assertThat(readResponse.statusCode()).isEqualTo(200);
    }

    @Test
    @Order(3)
    void deleteBoardTest() {
        final Response deleteResponse = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    @Test
    @Order(4)
    void deleteNonExistingBoardTest() {
        final Response deleteResponse = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(404);
    }

}
