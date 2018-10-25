package app;

public class SayanDey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int x = 1, y = 2;
		for(int i = 1 ; i <= n ; i++) {
			if(i % 2 != 0) {
				for(int j = 1 ; j <= n ; j++) {
					System.out.print(x);
				}
				System.out.println(y);
				x = x + 2;
			} else {
				System.out.print(x);
				for(int j = 1 ; j <= n ; j++) {
					System.out.print(y);
				}
				System.out.println();
				y = y + 2;
			}
		}
	}

}
