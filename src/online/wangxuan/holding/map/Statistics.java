package online.wangxuan.holding.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 理想状态下，Random可以将产生理想的数字分布，但要想测试它，则需要产生大量的随机数，<br>
 * 并对落入各种不同范围的数字进行计数。<br><br>
 * Map可以很容易解决该问题，本例中，键是由random产生的数字，而值是该数字出现的次数：
 * @author wx
 *
 */
public class Statistics {
	public static void main(String[] args) {
		Random rand = new Random(47);
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for (int i = 0; i < 10000; i++) {
			// Produce a number between 0 and 20
			int r = rand.nextInt(20);
			Integer freq = m.get(r);
			m.put(r, freq == null ? 1 : freq + 1);
		}
		System.out.println(m);
	}
}
