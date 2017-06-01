package online.wangxuan.enumerated;

import static net.mindview.util.Print.*;

enum Signal {
	GREEN, YELLOW, RED,
}

public class TrafficLight {
	Signal color = Signal.RED;
	
	public void change() {
		switch(color) {
			// 注意：在case语句中不必声明Signal.RED
			case RED: 
				color = Signal.GREEN;
				break;
			case GREEN: 
				color = Signal.YELLOW;
				break;
			case YELLOW: 
				color = Signal.RED;
				break;
		}
	}
	
	public String toString() {
		return "The traffic light is " + color;
	}
	
	public static void main(String[] args) {
		TrafficLight t = new TrafficLight();
		for (int i = 0; i < 7; i++) {
			print(t);
			t.change();
		}
	}
}
