public class PlayWithFirstLastList {
    public static void main(String[] args) {
        
    	IFirstLastList<String> items = FirstLastListFactory.create();
        items.add("zero");
        System.out.printf("Count: %d\r\n", items.getCount());
        System.out.printf("First: %s\r\n", items.first(1).iterator().next());
        System.out.printf("Last: %s\r\n", items.last(1).iterator().next());
        System.out.printf("Min: %s\r\n", items.min(1).iterator().next());
        System.out.printf("Max: %s\r\n", items.max(1).iterator().next());

        items.clear();

        items.add("first");
        items.add("second");
        items.add("third");
        items.add("fourth");
        System.out.printf("Count: %d\r\n", items.getCount());
        System.out.printf("First: %s\r\n", items.first(1).iterator().next());
        System.out.printf("Last: %s\r\n", items.last(1).iterator().next());
        System.out.printf("Min: %s\r\n", items.min(1).iterator().next());
        System.out.printf("Max: %s\r\n", items.max(1).iterator().next());

        System.out.printf("RemoveAll('first'): %d\r\n",
                items.removeAll("first"));
        System.out.printf("RemoveAll('fourth'): %d\r\n",
                items.removeAll("fourth"));
        System.out.printf("RemoveAll('first'): %d\r\n",
                items.removeAll("first"));
        System.out.printf("Count: %d\r\n", items.getCount());
        System.out.printf("First: %s\r\n", items.first(1).iterator().next());
        System.out.printf("Last: %s\r\n", items.last(1).iterator().next());
        System.out.printf("Min: %s\r\n", items.min(1).iterator().next());
        System.out.printf("Max: %s\r\n", items.max(1).iterator().next());
    }
}
