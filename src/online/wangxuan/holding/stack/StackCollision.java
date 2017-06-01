package online.wangxuan.holding.stack;

/**
 * 这两个Stack具有相同的接口，但是在java.util中没有任何公共的Stack接口， <br>
 * 这可能是因为在Java1.0中设计欠佳的最初的java.util.stack类占用了这个名字。
 * @author wx
 *
 */
public class StackCollision {
	public static void main(String[] args) {
		Stack<String> stack = new online.wangxuan.holding.stack.Stack<String>();
		for (String s : "My dog has fleas".split(" ")) {
			stack.push(s);
		}
		while(!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
		
		java.util.Stack<String> stack2 = new java.util.Stack<String>();
		for (String s : "My dog has fleas".split(" ")) {
			stack.push(s);
		}
		while(!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
	}
}
