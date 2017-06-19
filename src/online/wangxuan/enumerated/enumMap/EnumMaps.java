package online.wangxuan.enumerated.enumMap;

import online.wangxuan.enumerated.enumSet.AlarmPoints;

import java.util.EnumMap;
import java.util.Map;

import static online.wangxuan.enumerated.enumSet.AlarmPoints.*;
import static net.mindview.util.Print.*;

/**
 * Created by wangxuan on 2017/6/19.
 */
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {
            @Override
            public void action() {
                print("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {
            @Override
            public void action() {
                print("Bathroom alert!");
            }
        });
        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            printnb(e.getKey() + ": ");
            e.getValue().action();
        }
        try { // if there's no value for a particular key
            em.get(UTILITY).action();
        } catch (Exception e) {
            print(e);
        }
    }
}
