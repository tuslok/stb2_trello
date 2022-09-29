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
import pl.akademiaqa.requests.card.CreateCardRequest;
import pl.akademiaqa.requests.card.UpdateCardRequest;
import pl.akademiaqa.requests.list.CreateListRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MoveCardBetweenListsTest {

    // "id": "6286a986480c9b835389de46

    private final String boardName = "Tablica do testu e2e";
    private final String firstListName = "First list";
    private final String secondListName = "Second list";
    private final String firstCardName = "My First card";
    private static String boardId;
    private static String firstListId;
    private static String secondListId;
    private static String cardId;

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
    void createFirstListTest(){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstListName);
        queryParams.put("idBoard", boardId);

        final Response response = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(firstListName);

        firstListId = json.getString("id");
    }

    @Test
    @Order(3)
    void createSecondListTest(){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", secondListName);
        queryParams.put("idBoard", boardId);

        final Response response = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(secondListName);

        secondListId = json.getString("id");
    }

    @Test
    @Order(4)
    void createCardOnFirstListTest(){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstCardName);
        queryParams.put("idList", firstListId);

        final Response response = CreateCardRequest.createListRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(firstCardName);

        cardId = json.getString("id");
    }

    @Test
    @Order(5)
    void moveCardToSecondListTest(){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", secondListId);

        final Response response = UpdateCardRequest.updateListRequest(queryParams, cardId);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("idList")).isEqualTo(secondListId);
    }

    @Test
    @Order(6)
    void deleteBoardTest(){
        final Response deleteResponse = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}
