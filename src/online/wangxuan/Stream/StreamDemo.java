package online.wangxuan.Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangxuan on 2017/6/26.
 */
public class StreamDemo {

    private static void testMaxAndMin() {
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                                           new Track("Violets for Your Furs", 378),
                                           new Track("length Was", 451));

        Track shortestTrack = tracks.stream().min(Comparator.comparing(track -> track.getlength())).get();
        Track longestTrack = tracks.stream().max(Comparator.comparing(track -> track.getlength())).get();
        System.out.println("shortestTrack: " + shortestTrack.getName());
        System.out.println("longestTrack: " + longestTrack.getName());
        assertEquals(tracks.get(1), shortestTrack);
    }

    private static void getMinStack() {
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("length Was", 451));

        Track shortestTrack = tracks.get(0);
        for (Track track : tracks) {
            if (track.getlength() < shortestTrack.getlength()) {
                shortestTrack = track;
            }
        }
        assertEquals(tracks.get(1), shortestTrack);
    }

    public static void main(String[] args) {
        testMaxAndMin();
    }
}

