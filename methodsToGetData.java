import org.json.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.time.*;
public class methodsToGetData
{
    public static URL connect;
    private static HttpURLConnection connection;
    private static final int FIVE_SECONDS = 5000;
    private static String date = "";
    private static int iD;

    public methodsToGetData() {
    }

    public String getData(String playerName) throws IOException, InterruptedException, JSONException {
        arrayListAdder arrayList = new arrayListAdder();


        connect = new URL("https://balldontlie.io/api/v1/players?search=" + playerName);
        connection = (HttpURLConnection) connect.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(FIVE_SECONDS);
        connection.setReadTimeout(FIVE_SECONDS);

        Scanner fromWebsite = new Scanner(connect.openStream());
        StringBuffer stringBuffer = new StringBuffer();

        while(fromWebsite.hasNext())
        {
            stringBuffer.append(fromWebsite.next() + " ");
        }
        String result = stringBuffer.toString();
        result = result.replaceAll("<br>", "\n");
        result = result.replaceAll("<[^>]*>", "");
        JSONObject tempObj = new JSONObject(result);

        JSONArray jsonTempArray = tempObj.getJSONArray("data");
        JSONObject jsonTempObject = jsonTempArray.getJSONObject(0);

        iD = jsonTempObject.getInt("id");

        date += "" + LocalDate.now();

        connect = new URL("https://balldontlie.io/api/v1/stats?season[]=" + date.substring(0,4) + "&player_ids[]=" + iD +  "&start_date=" + date.substring(0,8) + "01");

        connection = (HttpURLConnection) connect.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(FIVE_SECONDS);
        connection.setReadTimeout(FIVE_SECONDS);

        fromWebsite = new Scanner(connect.openStream());
        StringBuffer stringBuffer2 = new StringBuffer();

        while(fromWebsite.hasNext())
        {
            stringBuffer2.append(fromWebsite.next() + " ");
        }
        result = stringBuffer2.toString();
        result = result.replaceAll("<br>", "\n");
        result = result.replaceAll("<[^>]*>", "");
        JSONObject jsonObject = new JSONObject(result);

        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("data").toString());

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject temp = jsonArray.getJSONObject(i);
            JSONObject tempForDate = temp.getJSONObject("game");
            arrayList.add("Date: " + tempForDate.get("date") + "     Points: " + temp.get("pts") + "\n");
        }
        arrayList.sort();
        return arrayList.toString();

    }
}
