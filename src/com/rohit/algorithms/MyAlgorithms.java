package com.rohit.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyAlgorithms {

	public static void staircase(int n) {
		// Write your code here
		for (int i = 0; i < n; i++) {
			String str = "#";
			String space = "";
			for (int j = i; j < n - 1; j++) {
				space = " " + space;
			}
			for (int j = 0; j < i; j++) {
				str = "#" + str;
			}
			str = space + str;
			System.out.println(str);
		}
	}

	public static void miniMaxSum(List<Integer> arr) {
		// Write your code here
		Collections.sort(arr);
		Long minSum = 0L;
		Long maxSum = 0L;
		for (int i = 0; i < arr.size(); i++) {
			if (i != arr.size() - 1)
				minSum = minSum + arr.get(i);
			if (i != 0) {
				maxSum = maxSum + arr.get(i);
			}
		}
		System.out.println(minSum + " " + maxSum);

	}

	public static int birthdayCakeCandles(List<Integer> candles) {
		// Write your code here
		Collections.sort(candles);
		System.out.println(candles);
		int count = 0;
		for (Integer candle : candles) {
			// if we use == here then it will only work with -127 to 127 autobox of int.
			if (candle.equals(candles.get(candles.size() - 1))) {
				count = count + 1;
			}
		}
		return count;

	}

	public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
		List<Integer> response = new ArrayList();
		for (int i = 0; i < queries.size(); i++) {
			response.add(0);
			for (String list : stringList) {
				if (list.equals(queries.get(i))) {
					response.set(i, response.get(i) + 1);
				}
			}
		}
		return response;

	}

	// insert-a-node-at-the-tail-of-a-linked-list
	static class SinglyLinkedListNode {
		public int data;
		public SinglyLinkedListNode next;

		public SinglyLinkedListNode(int nodeData) {
			this.data = nodeData;
			this.next = null;
		}
	}

	static class SinglyLinkedList {
		public SinglyLinkedListNode head;

		public SinglyLinkedList() {
			this.head = null;
		}
	}

	static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
		SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
		if (head == null) {
			// stsrting of the DS
			return newNode;
		} else {
			SinglyLinkedListNode temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
			return head;
		}
	}

	// insert-a-node-at-the-head-of-a-linked-list
	static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {
		SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
		if (llist != null) {
			newNode.next = llist;
		}
		return newNode;
	}

	// insert-a-node-at-a-specific-position-in-a-linked-list
	// Result as static class
	// solution piggyback Result static method
	public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
		// Write your code here
		SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
		if (llist == null) {
			// list empty
			return newNode;
		}

		if (position == 0) {
			newNode.next = llist;
			return newNode;
		}

		SinglyLinkedListNode temp = llist;
		int cursor = 0;
		while (temp.next != null) {
			if (position - 1 == cursor) {
				newNode.next = temp.next;
				temp.next = newNode;
				break;
			}
			temp = temp.next;
			cursor++;
		}
		return llist;

	}

	// delete-a-node-from-a-linked-list
	public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {
		// Write your code here
		if (llist == null) {
			return null;
		}

		if (position == 0) {
			return llist.next;
		}

		SinglyLinkedListNode temp = llist;
		int cursor = 0;
		while (temp.next != null) {
			if (cursor == position - 1) {
				temp.next = temp.next.next;
				break;
			}
			cursor++;
			temp = temp.next;
		}

		return llist;
	}

	// print-the-elements-of-a-linked-list-in-reverse/
	public static void reversePrint(SinglyLinkedListNode llist) {
		// Write your code here
		SinglyLinkedListNode temp = llist;
		ArrayList<Integer> list = new ArrayList();
		while (temp != null) {
			list.add(temp.data);
			// System.out.println(temp.data);
			temp = temp.next;
		}
		Collections.reverse(list);
		for (Integer in : list) {
			System.out.println(in);
		}
	}

	public static int hourglassSum(List<List<Integer>> arr) {
		// Write your code here
		List<Integer> sums = new ArrayList<>();
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				int sum = 0;
				List<Integer> row1 = arr.get(row);
				List<Integer> row2 = arr.get(row + 1);
				List<Integer> row3 = arr.get(row + 2);
				sum = sum + row1.get(col) + row1.get(col + 1) + row1.get(col + 2);
				sum = sum + row2.get(col + 1);
				sum = sum + row3.get(col) + row3.get(col + 1) + row3.get(col + 2);
				sums.add(sum);
			}
		}
		Collections.sort(sums);
		System.out.println(sums);
		return sums.get(sums.size() - 1);
	}

	public static List<Integer> rotateLeft(int d, List<Integer> arr) {
		Collections.rotate(arr, d * -1);
		return arr;
	}

	static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
		// only 1 node
		if (head1.next == null && head2.next == null) {
			return head1.data == head2.data;
		}
		// multiple nodes and does not match
		while (head1.next != null && head2.next != null) {
			if (head1.data != head2.data) {
				return false;
			}
			head1 = head1.next;
			head2 = head2.next;
		}
		// extra nodes
		if (head1.next != null ^ head2.next != null) {
			return false;
		}
		return true;
	}

	// declare the enclosing class static and then add the same method in the
	// calling main class and call this static method in that
	public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
		List<SinglyLinkedListNode> tempList = new ArrayList<>();
		while (llist != null) {
			tempList.add(llist);
			llist = llist.next;
		}
		Collections.reverse(tempList);
		for (int i = 0; i < tempList.size(); i++) {
			if (i == tempList.size() - 1) {
				tempList.get(i).next = null;
			} else {
				tempList.get(i).next = tempList.get(i + 1);
			}
		}
		return tempList.get(0);
	}

	static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

		List<SinglyLinkedListNode> tempList = new ArrayList<>();
		while (head1 != null) {
			tempList.add(head1);
			head1 = head1.next;
		}
		while (head2 != null) {
			tempList.add(head2);
			head2 = head2.next;
		}
		Collections.sort(tempList, (obj1, obj2) -> obj1.data - obj2.data);
		for (int i = 0; i < tempList.size(); i++) {
			if (i == tempList.size() - 1) {
				tempList.get(i).next = null;
			} else {
				tempList.get(i).next = tempList.get(i + 1);
			}
		}
		return tempList.get(0);
	}

	public static int pageCount(int n, int p) {
		// Write your code here
		// no flips needed.
		if (n == p || p == 1 || (n - p == 1 && n % 2 != 0)) {
			System.out.println("returning 0");
			return 0;
		}
		int forward = (p - p % 2) / 2;
		int diff = (n - p);
		if (diff == 1) {
			return 1;
		}
		int backward = (diff - diff % 2) / 2;
		if (forward >= backward) {
			System.out.println("returning backward:" + backward);
			return backward;
		} else {
			System.out.println("returning forward:" + forward);
			return forward;
		}

	}

	public static int countingValleys(int steps, String path) {
		// Write your code here
		int location = 0;
		char currently = 'I';
		int valleyCount = 0;
		for (char step : path.toCharArray()) {
			if ('D' == step) {
				// decent
				location--;
				if (currently == 'I')
					currently = 'D';
			} else {
				// climb
				location++;
				if (currently == 'I')
					currently = 'U';
			}
			if (location == 0) {
				if (currently == 'D') {
					valleyCount++;
				}
				currently = 'I';
			}
		}
		return valleyCount;
	}

	public static void main(String[] args) {
		MyAlgorithms.staircase(10);
		MyAlgorithms.miniMaxSum(Arrays.asList(1, 2, 3, 4, 5));
		int count = MyAlgorithms.birthdayCakeCandles(Arrays.asList(12345, 123456, 123456, 123456, 123456, 1, 2, 2, 1000,
				9999, 9998, 10000, 10000, 10000, 10000));
		System.out.println(count);
	}

}
