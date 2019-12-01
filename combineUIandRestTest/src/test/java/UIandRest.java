import com.codeborne.selenide.Condition;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class UIandRest {

    private static final String URL = "https://freesound.org/";
    private static final String API_PATH = "apiv2/search/text/";
    private static final String API_KEY = "rcBTauOX7I4qJbioXWtd04GWp1f9lLq9RFd2rXpJ";
    private static String query = "dog";
    private static String filename;
    private static String username;
    private static String soundId;


    @BeforeEach
    public void beforeTest () throws UnirestException {
        String json = Unirest.get(URL+API_PATH)
                .queryString("token", API_KEY)
                .queryString("query", query)
                .asString()
                .getBody();
        //System.out.println(json);
        filename = JsonPath.read(json, "$.results[0].name");
        username = JsonPath.read(json, "$.results[0].username");
        soundId = String.valueOf((Integer) JsonPath.read(json, "$.results[0].id"));
    }

    @Test
    public void playButtonTest() {
        open(URL+String.format("people/%s/sounds/%s/", username, soundId));
        $("#single_sample_player .play").should(Condition.visible);

}

    @Test
    public void checkFilename() {
        open(URL+"search/?q="+query);
        $$(".sound_filename")
                .get(0)
                .should(Condition.text(filename));
    }

}
