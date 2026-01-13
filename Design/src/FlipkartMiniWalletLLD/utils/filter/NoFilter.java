package FlipkartMiniWalletLLD.utils.filter;

import FlipkartMiniWalletLLD.models.Transaction;

import java.util.List;

public class NoFilter implements FilterStrategy{
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions;
    }
}
