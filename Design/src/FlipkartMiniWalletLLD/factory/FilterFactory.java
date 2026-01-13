package FlipkartMiniWalletLLD.factory;

import FlipkartMiniWalletLLD.utils.filter.FilterStrategy;
import FlipkartMiniWalletLLD.utils.filter.ReceiveTransactionFilter;
import FlipkartMiniWalletLLD.utils.filter.SendTransactionFilter;

public class FilterFactory {

    public static FilterStrategy getFilterStrategy(String filter){

        switch (filter){
            case "send":
                return new SendTransactionFilter();
            case "receive":
                return new ReceiveTransactionFilter();
            default:
                throw new IllegalArgumentException("Unsupported filter method");
        }
    }
}
