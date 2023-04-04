package practical_tasks.PT_2;

public class TestNode {

	public static void main(String[] args) throws InterruptedException {
		NodeList list = new NodeList();
		System.out.println("Создать двусвязный список длиной 15 элементов");
		for (int i = 0; i < 15; i++) {
			int r = (int) (Math.random() * 100);
			Node node = new Node(r);
			if (i == 0) {
				list.head = node; // головной узел
			} else {
				list.head.append(node);
			}
			list.last = node; // хвостовой узел
		}

		// Исходный список
		System.out.println(list.toString());

		// Развернутый список
		System.out.println(list.reverse());

	}
}