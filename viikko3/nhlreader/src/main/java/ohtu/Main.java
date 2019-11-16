package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        System.out.println("Oliot:");
        ArrayList<Player> tulostettava = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                tulostettava.add(player);
            }
        }
        Collections.sort(tulostettava);
        for (Player player : tulostettava) {
            System.out.println(player);
        }
    }

}
