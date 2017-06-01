package online.wangxuan.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Arrays.asList()方法的限制是它对所产生的List的类型做出了最理想的假设，<br>
 * 而并没有注意你对它赋予了什么样的类型。
 * @author wx
 *
 */
public class AsListInference {
	public static void main(String[] args) {
		List<Snow> snow1 = Arrays.asList(
				new Crusty(), new Slush(), new Powder());
	//	Won't compile Arrays.asList()中只有Powder类型，因此它会创建List<Powder>而不是List<Snow>
	//	List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());
		
	//	Collections.addAll() doesn't get confused 
		List<Snow> snow3 = new ArrayList<Snow>();
		Collections.addAll(snow3, new Light(), new Heavy());
		
	//	give a hint using an explicit type argument specification
	//	可以在Arrays.asList()中插入一条"线索"，以告诉编译器对于由Arrays.asList()产生的List类型，
	//	实际的目标类型应该是什么。
		List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy());
	}
}

class Snow {}
class Powder extends Snow {}
class Light extends Powder {}
class Heavy extends Powder {}
class Crusty extends Snow {}
class Slush extends Snow {}
