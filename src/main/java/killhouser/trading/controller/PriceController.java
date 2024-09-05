package killhouser.trading.controller;

import killhouser.trading.models.request.FetchTradingPriceRequest;
import killhouser.trading.models.response.FetchTradingPriceResponse;
import killhouser.trading.service.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {

    @Autowired PriceService priceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public FetchTradingPriceResponse fetchPrice(@RequestBody FetchTradingPriceRequest request){
        return priceService.fetchPrice(request);
    }

}
