
public class Exdample {

	public static void main(String[] args) {
		
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        list.forEachValue(x -> System.out.println(x));
        System.out.println("--------------------");

        list.addLast(5);
        list.addFirst(3);
        list.addFirst(2);
        list.addLast(10);
        System.out.printf("Count = %d\n", list.size());

        list.forEachValue(x -> System.out.println(x));
        System.out.println("--------------------");

        list.removeFirst();
        list.removeLast();
        list.removeFirst();

        list.forEachValue(x -> System.out.println(x));
        System.out.println("--------------------");

        list.removeLast();

        list.forEachValue(x -> System.out.println(x));
        System.out.println("--------------------");
	}
}
