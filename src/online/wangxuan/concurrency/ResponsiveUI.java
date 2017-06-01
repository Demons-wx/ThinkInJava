package online.wangxuan.concurrency;

import java.io.IOException;

/**
 * <p>把计算放在run()方法中，这样它就能让出处理器给别的程序。</p>
 * 当按下“回车”键时，既可以看到计算确实在后台运行，同时还在等待用户的输入。
 * @author wx
 *
 */
public class ResponsiveUI extends Thread {
	private static volatile double d = 1;
	public ResponsiveUI() {
		setDaemon(true);
		start();
	}
	public void run() {
		while(d > 0){
			d = d + (Math.PI + Math.E) / d;
		}
	}
	public static void main(String[] args) throws IOException {
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);
	}
}

class UnresponsiveUI {
	private static volatile double d = 1;
	public UnresponsiveUI() throws IOException {
		while(d > 0){
			d = d + (Math.PI + Math.E) / d;
		}
		System.in.read(); // never gets here
	}
}