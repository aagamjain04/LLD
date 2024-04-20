public class Client {
    public static void main (String[] args){
        SortingContext sortingContext = new SortingContext(new SortingStrategyBubbleImpl());
        int[] arr = {1,2,3};
        sortingContext.performSort(arr);
        sortingContext.setSortingStrategy(new SortingStrategyMergeImpl());
        sortingContext.performSort(arr);
        sortingContext.setSortingStrategy(new SortingStrategyQuickImpl());
        sortingContext.performSort(arr);
    }
}
