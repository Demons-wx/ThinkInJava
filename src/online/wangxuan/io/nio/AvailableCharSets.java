package online.wangxuan.io.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

import net.mindview.util.Print;

/**
 * 缓冲器容纳的是普通字节，为了把它们转换成字符，我们要么在输入它们<br>
 * 的时候对其进行编码，要么将其从缓冲器中输出时对他们进行解码。<br><br>
 * 
 * 我们可以使用java.nio.charset.Charset类实现这些功能，<br>
 * 该类提供了把数据编码成多种不同类型的字符集工具： 
 * @author wx
 *
 */
public class AvailableCharSets {
	public static void main(String[] args) {
		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		Iterator<String> it = charSets.keySet().iterator();
		while(it.hasNext()) {
			String csName = it.next();
			Print.printnb(csName);
			Iterator aliases = charSets.get(csName).aliases().iterator();
			if(aliases.hasNext()) {
				Print.printnb(": ");
			}
			while(aliases.hasNext()) {
				Print.printnb(aliases.next());
				if(aliases.hasNext()) {
					Print.printnb(", ");
				}
			}
			Print.print();
		}
	}
}
