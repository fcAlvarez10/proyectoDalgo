import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class P2 {
	public static void main(String[] args) {
		P2 program = new P2();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			while(line != null) {
				int n = Integer.parseInt(line);
				int[] sig = new int[n];
				for(int i = 0; i < sig.length; i++) {
					String[] params = br.readLine().split(" ");
					int v = Integer.parseInt(params[0]);
					int e = Integer.parseInt(params[1]);
					ArrayList<Integer>[] arcs = new ArrayList[v];
					for(int j = 0; j < v; j++) arcs[j] = new ArrayList<>();
					for(int j = 0; j < e; j++) {
						int p = Integer.parseInt(params[2*(j + 1)]);
						int q = Integer.parseInt(params[2*(j+1) + 1]);
						arcs[p].add(q);
						arcs[q].add(p);
//						System.out.println("p,q = " + p + ", " + q);
					}
//					for(int k1 = 0; k1 < arcs.length; k1++) 
//						System.out.println("At " + k1 +": " + Arrays.toString(arcs[k1].toArray()));
					sig[i] = program.levelDFS(arcs);
				}
//				System.out.println("Sigmas = " + Arrays.toString(sig));
				System.out.println(program.calcMinSum(sig));
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int levelDFS(ArrayList<Integer>[] arcs) {
		int x = 0;
		int y = 0;

		int marked[] = new int[arcs.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		marked[0] = 1;
		x++;
		while(!stack.isEmpty()) {
			int k = stack.pop();
			int mark = (marked[k] == 1? 2 : 1);
			for(Integer j : arcs[k]) {
				if(marked[j] == 0) {
					marked[j] = mark;
					if(mark == 1) x++; 
					else y++;
					stack.push(j);
				}
			}
		}
//		System.out.println("Marked: " + Arrays.toString(marked));
		return Math.abs(x-y);
	}

	public int calcMinSum(int[] sig) {
		int alfa = 0;
		for (int i : sig) alfa += i;
		
		//Llenar el caso base
		int[] arr1 = new int[alfa + 1];
		for (int i = 0; i < arr1.length; i++) arr1[i] = Math.abs(i-sig[0]);
		
		//Recursion
		int[] arr2 = new int[alfa + 1];
		int[] last = arr2;
		int[] current = arr1;
		for(int k = 1; k < sig.length; k++) {
//			System.out.println(Arrays.toString(current));
			if(k%2 == 1) {
				current = arr2; last = arr1;
			} else {
				current = arr1; last = arr2;
			}
			
			for(int i = 0; i < current.length; i++) {
				int min = last[i] + sig[k];
				for(int m = 0; m < last.length; m++) {
					int temp = Math.abs(i - m) + last[m];
					min = Math.min(min, temp >= sig[k]? temp - sig[k] : temp + sig[k]);
				}
				current[i] = min;
			}
		}
		return current[0];
	}
}
