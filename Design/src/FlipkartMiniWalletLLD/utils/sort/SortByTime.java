package FlipkartMiniWalletLLD.utils.sort;

import FlipkartMiniWalletLLD.models.Transaction;

import java.util.Comparator;
import java.util.List;

public class SortByTime implements SortStrategy{

    @Override
    public void sort(List<Transaction> transactions) {
        transactions.sort(Comparator.comparing(Transaction::getTime).reversed());
    }
}
