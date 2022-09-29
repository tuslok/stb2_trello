package pl.akademiaqa.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.requests.board.CreateBoardRequest;
import pl.akademiaqa.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class CreateBoardTest {

    private String boardId;

    @DisplayName("Create a board with valid data")
    @ParameterizedTest(name = "Board name: {0}")
    @MethodSource("sampleBoardNameData")
    void createBoard1Test(String boardName){

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("name", boardName);

        //Create a board

        final Response response = CreateBoardRequest.createBoardRequest(queryParams);
        //Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        //Assertions.assertEquals(boardName, json.getString("name"));
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        boardId = json.getString("id");

        // Delete a board

        final Response deleteResponse = DeleteBoardRequest.deleteBoardResponse(boardId);
        //Assertions.assertEquals(200, deleteResponse.getStatusCode());
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);

    }

    private static Stream<Arguments> sampleBoardNameData(){
        return Stream.of(
                Arguments.of("Nazwa tablicy"),
                Arguments.of("NAZWA_TABLICY"),
                Arguments.of("N@ZW@ T@BLICY___"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("%")
        );
    }
}
