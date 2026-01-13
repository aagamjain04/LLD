package FlipkartMiniWalletLLD.utils.filter;

import FlipkartMiniWalletLLD.enums.TransactionType;
import FlipkartMiniWalletLLD.models.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class SendTransactionFilter implements FilterStrategy{
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> (t.getTransactionType()== TransactionType.SEND))
                .collect(Collectors.toList());
    }
}
