package community;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChatBotRetrofitAPI {

    @GET
    Call<ChatBotMsgModel> getMessage(@Url String url);

}
