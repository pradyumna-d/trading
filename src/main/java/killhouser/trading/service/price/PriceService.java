package killhouser.trading.service.price;

import killhouser.trading.models.request.FetchTradingPriceRequest;
import killhouser.trading.models.response.FetchTradingPriceResponse;
import org.springframework.stereotype.Service;

@Service
public interface PriceService {
    FetchTradingPriceResponse fetchPrice(FetchTradingPriceRequest request);
}
