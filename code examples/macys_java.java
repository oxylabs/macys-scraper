import okhttp3.*;
import org.json.JSONObject;

public class Main implements Runnable {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String USERNAME = "YOUR_USERNAME";
    public static final String PASSWORD = "YOUR_PASSWORD";

    public void run() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("source", "universal");
        jsonObject.put("url", "https://www.macys.com/shop/sale?id=3536&trackingid=407x733169&m_sc=sem&m_sb=google&m_tp=trademark&m_ac=google_trademark_international&m_ag=macy%27score_exact&m_cn=ggl_trademark_intl_lithuania_exact&m_pi=go_cmp-94807774_adg-154238318312_ad-674544461209_kwd-252677959_dev-c_ext-102882313401_prd-&gad_source=1&gclid=cj0kcqiayewrbhddarisagp1mwsg3z6ogoqrztdycjyqio5togc316ldkwuqkkbhrmiv4i_ho0gcjlkaagl6ealw_wcb");

        Authenticator authenticator = (route, response) -> {
            String credential = Credentials.basic(USERNAME, PASSWORD);

            return response
                    .request()
                    .newBuilder()
                    .header(AUTHORIZATION_HEADER, credential)
                    .build();
        };

        var client = new OkHttpClient.Builder()
                .authenticator(authenticator)
                .build();

        var mediaType = MediaType.parse("application/json; charset=utf-8");
        var body = RequestBody.create(jsonObject.toString(), mediaType);
        var request = new Request.Builder()
                .url("https://realtime.oxylabs.io/v1/queries")
                .post(body)
                .build();

        try (var response = client.newCall(request).execute()) {
            assert response.body() != null;
            System.out.println(response.body().string());
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
}