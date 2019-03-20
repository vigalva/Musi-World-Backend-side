package smartspace.data;

public class TestsData {
	public static void main(String[] args) {
		(new ActionEntityTests()).test();
		(new ElementEntityTests()).test();
		(new LocationTests()).test();
		(new UserEntityTests()).test();
		System.out.println("DoneTestData");
	}
}
