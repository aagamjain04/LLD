package FlipkartMiniWalletLLD.factory;

import FlipkartMiniWalletLLD.utils.sort.SortByAmount;
import FlipkartMiniWalletLLD.utils.sort.SortByTime;
import FlipkartMiniWalletLLD.utils.sort.SortStrategy;

public class SortFactory {
    public static SortStrategy getSortStrategy(String name){
        switch (name){
            case "amount":
                return new SortByAmount();
            case "time":
                return new SortByTime();
            default:
                throw new IllegalArgumentException("unsupported sort type");

        }
    }
}
