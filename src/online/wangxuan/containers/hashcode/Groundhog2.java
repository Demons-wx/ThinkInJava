package online.wangxuan.containers.hashcode;

public class Groundhog2 extends Groundhog {
	public Groundhog2(int n) {
		super(n);
	}
	public int hashCode() {
		return number;
	}
	/* 看起来equals()方法只是检查其参数是否为Groundhog2的实例，但是instanceof悄悄的检查了此对象是否为null */
	public boolean equals(Object o) {
		return o instanceof Groundhog2 && (number == ((Groundhog2)o).number);
	}
}
