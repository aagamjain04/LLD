package FlipkartMiniWalletLLD.utils.filter;

import FlipkartMiniWalletLLD.models.Transaction;

import java.util.List;

public interface FilterStrategy {

    List<Transaction> filter(List<Transaction> transactions);
}
