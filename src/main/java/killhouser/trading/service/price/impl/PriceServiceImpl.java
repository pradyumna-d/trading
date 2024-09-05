package killhouser.trading.service.price.impl;

import killhouser.trading.command.OlympTradeCommand;
import killhouser.trading.models.request.FetchTradingPriceRequest;
import killhouser.trading.models.response.FetchTradingPriceResponse;
import killhouser.trading.service.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceServiceImpl implements PriceService {

    @Autowired OlympTradeCommand olympTradeCommand;
    @Override
    public FetchTradingPriceResponse fetchPrice(FetchTradingPriceRequest request) {
        return olympTradeCommand.getPriceHistory(request);
    }
}
