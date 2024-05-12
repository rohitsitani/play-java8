package com.rohit.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
		long sum = 0;
		for (Integer in : arr) {
			sum = sum + in;
		}
		System.out.println(String.format("%s %s", sum - arr.get(arr.size() - 1), sum - arr.get(0)));
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

	// Data structures: SinglyLinkedList
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

	static void printLinkedList(SinglyLinkedListNode head) {
		while (head != null) {
			System.out.println(head.data);
			head = head.next;
		}
	}

	// insert-a-node-at-the-tail-of-a-linked-list
	static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
		SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
		if (head == null) {
			// starting of the DS
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

	public static int getNode(SinglyLinkedListNode llist, int positionFromTail) {
		// Write your code here
		int size = 0;
		SinglyLinkedListNode node = llist;
		while (node != null) {
			++size;
			node = node.next;
		}
		System.out.println(size);
		int i = 1;
		while (llist != null) {
			if (i == size - positionFromTail) {
				return llist.data;
			}
			++i;
			llist = llist.next;
		}
		return -1;
	}

	static int printLinkedListN(SinglyLinkedListNode head) {
		SinglyLinkedListNode temp = head;
		int length = 0;
		while (temp != null) {
			System.out.println(temp.data + ":" + temp);
			temp = temp.next;
			length++;
		}
		return length;
	}

	static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
		int l1 = printLinkedListN(head1);
		System.out.println("List1:" + l1);
		int l2 = printLinkedListN(head2);
		System.out.println("List2:" + l2);
		// make sure the bigger list is skipped to match the smaller list length
		if (l1 > l2) {
			System.out.println("A:");
			int skip = l1 - l2;
			while (head1 != null && skip > 0) {
				System.out.println("AA:" + skip);

				if (skip > 0) {
					head1 = head1.next;
					skip--;
				}
			}
		} else if (l2 > l1) {
			System.out.println("B:");
			int skip = l2 - l1;
			while (head2 != null && skip > 0) {
				System.out.println("BB:" + skip);
				if (skip > 0) {
					head2 = head2.next;
					skip--;
				}
			}
		}
		// iterate and find if the object is matching exactly at which node
		int data = -1;
		while (head1 != null && head2 != null) {
			System.out.println("C:");
			if (head1 == head2) {
				System.out.println("D:" + head1.data);
				data = head1.data;
				break;
			}
			head1 = head1.next;
			head2 = head2.next;
		}
		return data;

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
				// last node should have next pointing to null
				tempList.get(i).next = null;
			} else {
				// all other nodes should point to next
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

	public static List<Integer> rotateLeft(int d, List<Integer> arr) {
		Collections.rotate(arr, -d);
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
				// descend
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

	static int getMoneySpent(int[] keyboards, int[] drives, int b) {
		/*
		 * Write your code here.
		 */
		List<Integer> costcombos = new ArrayList<>();
		for (int keyboard : keyboards) {
			for (int drive : drives) {
				if ((keyboard + drive) <= b)
					costcombos.add(keyboard + drive);
			}
		}
		Collections.sort(costcombos, Collections.reverseOrder());
		return costcombos.size() >= 1 ? costcombos.get(0) : -1;
	}

	static String catAndMouse(int a, int b, int m) {
		int catA = Math.abs(m - a);
		int catB = Math.abs(m - b);
		if (catA == catB) {
			return "Mouse C";
		} else if (catA < catB) {
			return "Cat A";
		} else {
			return "Cat B";
		}
	}

	// TODO fails the time test
	public static List<Integer> climbingLeaderboardOld(List<Integer> ranked, List<Integer> player) {
		// Write your code here
		ArrayList<Integer> newRank = new ArrayList<>();
		for (Integer p : player) {
			ArrayList<Integer> playerRanked = new ArrayList<>();
			playerRanked.addAll(ranked);
			playerRanked.add(p);
			Collections.sort(playerRanked, Collections.reverseOrder());
			HashMap<Integer, Integer> map = new HashMap<>();
			Integer count = 0;
			for (int rank = 0; rank < playerRanked.size(); rank++) {
				if (map.get(playerRanked.get(rank)) == null) {
					count++;
					map.put(playerRanked.get(rank), count);
				}
			}
			newRank.add(map.get(p));
		}
		return newRank;
	}

	// worked last bit after re running again in hackerrank
	// (not sure if execution depends on JVM)
	public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
		// Write your code here
		ArrayList<Integer> newRank = new ArrayList<>();
		List<Integer> playerRanked = ranked.stream().distinct().collect(Collectors.toList());
		for (Integer p : player) {
			int index = Collections.binarySearch(playerRanked, p, Collections.reverseOrder());
			if (index < 0) {
				index = -index - 1;
				playerRanked.add(index, p);
			}
			newRank.add(index + 1);
		}
		return newRank;
	}

	public static int hurdleRace(int k, List<Integer> height) {
		// Write your code here
		Collections.sort(height, Collections.reverseOrder());
		return k >= height.get(0) ? 0 : height.get(0) - k;
	}

	public static int designerPdfViewer(List<Integer> h, String word) {
		// Write your code here
		int max = 0;
		for (char ch : word.toCharArray()) {
			int index = ch - 'a';
			if (h.get(index) > max) {
				max = h.get(index);
			}
		}
		return word.length() * max;
	}

	public static int utopianTree(int n) {
		// Write your code here
		int height = 1;
		for (int i = 1; i <= n; i++) {
			System.out.println(i);
			if (i % 2 == 0) {
				System.out.println("add");
				height = height + 1;
			} else {
				System.out.println("multiply");
				height = height * 2;
			}
		}
		return height;

	}

	public static String angryProfessor(int k, List<Integer> arrival) {
		// Write your code here
		Integer arrivedCount = 0;
		for (Integer arrived : arrival) {
			if (arrived <= 0) {
				++arrivedCount;
			}
			if (arrivedCount >= k) {
				// short circuit
				return "NO";
			}
		}
		if (arrivedCount >= k) {
			// short circuit
			return "NO";
		}
		return "YES";
	}

	public static int viralAdvertising(int days) {
		// Write your code here
		int likes = 0;
		int shared = 5;
		int cumulativelikes = 0;
		for (int day = 0; day < days; day++) {
			likes = (shared / 2);
			shared = likes * 3;
			cumulativelikes = cumulativelikes + likes;
		}
		return cumulativelikes;
	}

	public static int beautifulDays(int i, int j, int k) {
		// Write your code here
		int beautifulDays = 0;
		for (int day = i; day <= j; day++) {
			StringBuilder sb = new StringBuilder();
			sb.append(String.valueOf(day));
			sb.reverse();
			if ((day - Integer.valueOf(sb.toString())) % k == 0)
				beautifulDays++;
		}
		return beautifulDays;
	}

	public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {
		// Write your code here
		Collections.rotate(a, k);
		LinkedList linkedList = new LinkedList<>();
		for (Integer query : queries) {
			linkedList.add(a.get(query));
		}
		return linkedList;

	}

	static class DoublyLinkedListNode {
		public int data;
		public DoublyLinkedListNode next;
		public DoublyLinkedListNode prev;

		public DoublyLinkedListNode(int nodeData) {
			this.data = nodeData;
			this.next = null;
			this.prev = null;
		}
	}

	static class DoublyLinkedList {
		public DoublyLinkedListNode head;
		public DoublyLinkedListNode tail;

		public DoublyLinkedList() {
			this.head = null;
			this.tail = null;
		}

		public void insertNode(int nodeData) {
			DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);
			if (this.head == null) {
				this.head = node;
			} else {
				this.tail.next = node;
				node.prev = this.tail;
			}

			this.tail = node;
		}
	}

	public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode llist1, int data) {
		// Write your code here
		DoublyLinkedListNode llist = llist1;
		DoublyLinkedListNode node = new DoublyLinkedListNode(data);
		DoublyLinkedListNode lastNode = null;
		while (llist != null) {
			lastNode = llist;
			if (llist.data > data) {
				DoublyLinkedListNode small = llist.prev;
				if (small == null) {
					// start of node
					node.next = llist;
					node.prev = small;
					llist.prev = node;
					return node;
				} else {
					//Middle of node
					small.next = node;
					node.next = llist;
					node.prev = small;
					llist.prev = node;
					return llist1;
				}
			}
			llist = llist.next;
		}
		//last node
		lastNode.next = node;
		return llist1;
	}

	public static void main(String[] args) {
		MyAlgorithms.staircase(10);
		MyAlgorithms.miniMaxSum(Arrays.asList(1, 2, 3, 4, 5));
		int count = MyAlgorithms.birthdayCakeCandles(Arrays.asList(12345, 123456, 123456, 123456, 123456, 1, 2, 2, 1000,
				9999, 9998, 10000, 10000, 10000, 10000));
		System.out.println(count);
		System.out.println((int) 'A');
	}

}
