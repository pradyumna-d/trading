package killhouser.trading.command;

import com.irex.http.ApacheHttpClient;
import com.irex.http.config.HttpAppConfiguration;
import com.irex.http.model.HttpMethod;
import com.irex.http.model.payload.JsonRequestBody;
import killhouser.trading.config.olymptrade.OlympTradeConfig;
import killhouser.trading.exception.BaseException;
import killhouser.trading.exception.ResponseCode;
import killhouser.trading.models.request.FetchTradingPriceRequest;
import killhouser.trading.models.response.FetchTradingPriceResponse;
import killhouser.trading.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import com.irex.http.model.HttpRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.irex.http.ApacheHttpClient.fetchExecutor;
import static killhouser.trading.config.command.ClientType.OLYMP_TRADE_CLIENT_NAME;

@Slf4j
@Component
public class OlympTradeCommand {
    private final CloseableHttpClient httpClient;
    private final OlympTradeConfig olympTradeConfig;
    private final HttpAppConfiguration httpAppConfiguration;

    public OlympTradeCommand(@Qualifier(OLYMP_TRADE_CLIENT_NAME) OlympTradeConfig olympTradeConfig, HttpAppConfiguration httpAppConfiguration) {
        this.httpClient = ApacheHttpClient.createClient(olympTradeConfig);
        this.olympTradeConfig = olympTradeConfig;
        this.httpAppConfiguration = httpAppConfiguration;
    }

    public FetchTradingPriceResponse getPriceHistory(FetchTradingPriceRequest request){
        try {
            HttpRequest httpRequest = HttpRequest.builder()
                    .httpClient(this.httpClient)
                    .httpAppConfiguration(httpAppConfiguration)
                    .httpMethod(HttpMethod.POST)
                    .httpRequestBody(JsonRequestBody.builder().data(request).build())
                    .path("/quote-history/ftt/v1")
                    .objectMapper(JsonUtils.getMapper())
                    .build();
            return fetchExecutor(httpRequest).execute(FetchTradingPriceResponse.class, null);
        } catch (Exception e){
            String logMessage = String.format("Error while calling olymp trade price history api :: message :: %s ", e.getMessage());
            throw BaseException.create(ResponseCode.OLYMP_TRADE_ERROR, "can't call olymp trade", logMessage, e);
        }
    }
}
