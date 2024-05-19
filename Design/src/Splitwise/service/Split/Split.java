package Splitwise.service.Split;

import java.util.List;

public interface Split {
    void process(String user, Double amount, List<String> members, List<String> shares);
}
