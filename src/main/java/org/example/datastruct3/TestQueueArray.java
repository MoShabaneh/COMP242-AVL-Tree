package org.example.datastruct3;

public class TestQueueArray {

	public static void main(String[] args) {

		QueueArray<String> Queue1 = new QueueArray<>(50);

		Queue1.enqueue("50");
		Queue1.enqueue("55");
		Queue1.enqueue("60");
		Queue1.enqueue("70");

		System.out.println(Queue1.dequeue());

		String data = Queue1.getFront();
		System.out.println(data);
		data = Queue1.dequeue();
	}

	// -----------------------------------------------------------

}
