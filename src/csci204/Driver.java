package csci204;

public class Driver {

	public static void main(String[] args) throws DLListException {

		DLList<Integer> list = new DLList<Integer>();
		try {
			list.insert(20);
			for (int i = 0; i < 5; i++)
				list.append(i);

			list.moveToPos(2);
			list.insert(10);
			System.out.println(list.toString());
			list.remove();
			System.out.println(list.toString());
			list.moveToPos(3);
			list.moveToPos(4);
			list.moveToPos(5);

			list.moveToStart();
			list.moveToEnd();
			list.remove();
			list.next();
			list.prev();

			System.out.println(list.toString());
			list.clear();
			System.out.println(list.toString());
			list.insert(5);
			list.insert(4);
			list.clear();
		} catch (DLListException e) {
			e.printStackTrace();
		}
	}
}
