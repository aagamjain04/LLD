package FlipkartMiniWalletLLD.utils.sort;

import FlipkartMiniWalletLLD.models.Transaction;

import java.util.Comparator;
import java.util.List;

public class SortByAmount implements SortStrategy{

    @Override
    public void sort(List<Transaction> transactions) {
        transactions.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());

    }
}
