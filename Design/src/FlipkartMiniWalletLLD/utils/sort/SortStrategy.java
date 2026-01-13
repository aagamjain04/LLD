package FlipkartMiniWalletLLD.utils.sort;

import FlipkartMiniWalletLLD.models.Transaction;

import java.util.List;

public interface SortStrategy {
    void sort(List<Transaction> transactions);
}
