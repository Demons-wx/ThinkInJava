package online.wangxuan.Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangxuan on 2017/6/27.
 */
public class Reconsitution {

    /**
     * 遗留代码：找出长度大于1分钟的曲目
     *
     * 首先，初始化一个Set对象，用来保存找到的曲目名称，然后使用for循环遍历所有专辑，
     * 每次循环中再使用一个for循环遍历每张专辑上的每首曲目，检查集中长度是否大于60s。
     * @param albums
     * @return
     */
    public static Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getlength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }

    /**
     * 重构第一步：找出长度大于1分钟的曲目
     *
     * 第一步要修改的是for循环。首先使用Stream的forEach方法替换掉for循环，但还是暂时
     * 保留原来循环体中的代码，这是在重构时非常方便的一个技巧。调用stream方法从专辑列表
     * 中生成第一个Stream，同时getTracks方法本身就返回一个Stream对象。
     *
     * @param albums
     * @return
     */
    public static Set<String> findLongTracks1(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream().forEach(album -> {
            album.getTracks().forEach(track -> {
                if (track.getlength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            });
         });
        return trackNames;
    }

    /**
     * 重构第二步：找出长度大于1分钟的曲目
     *
     * 在重构第一步中，虽然使用了流，但是并没有充分发挥它的作用。事实上，重构后的代码
     * 还不如原来的代码好。因此是时候引入一些更符合流风格的代码，最内层的forEach方法
     * 正是主要突破口。
     *
     * 最内层的forEach方法有三个功能：找出长度大于1分钟的曲目，得到符合条件的曲目名称，
     * 将曲目名称加入集合Set。这就意味着需要三项Stream操作：找出满足某种条件的曲目是
     * filter的功能，得到曲目名称则可用map达成，终结操作可使用forEach方法将曲目名称
     * 加入一个集合。
     * @param albums
     * @return
     */
    public static Set<String> findLongTracks2(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream().forEach(album -> {
            album.getTracks().filter(track -> track.getlength() > 60)
                    .map(track -> track.getName())
                    .forEach(name -> trackNames.add(name));
        });
        return trackNames;
    }

    /**
     * 重构第三步：找出长度大于1分钟的曲目
     *
     * 代码看起来还是冗长繁琐，将各种流嵌套起来并不理想，最好还是用干净整洁的顺序调用一些方法。
     *
     * 理想的操作莫过于找到一种方法，将专辑转化成一个曲目的Stream。众所周知，任何时候想转化
     * 或替换代码，都应该使用map操作。这里将使用比map更复杂的flatMap操作，把多个Stream合并
     * 成一个Stream并返回。
     *
     * @param albums
     * @return
     */
    public static Set<String> findLongTracks3(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();

        albums.stream().flatMap(album -> album.getTracks())
                .filter(track -> track.getlength() > 60)
                .map(track -> track.getName())
                .forEach(name -> trackNames.add(name));

        return trackNames;
    }

    /**
     * 重构第四步：找出长度大于1分钟的曲目
     *
     * 上面的代码使用一组简洁的方法调用替换掉了两个嵌套的for循环，看起来清晰很多。但是，仍需手动
     * 创建一个Set对象并将元素加入其中，但我们希望看到的是整个计算任务由一连串Stream操作完成。
     *
     * 使用collect(Collectors.toSet())可以将Stream中的值转换成一个集合。因此，将最后的forEach
     * 方法替换成collect，并删掉变量trackNames。
     * @param albums
     * @return
     */
    public static Set<String> findLongTracks4(List<Album> albums) {
        return albums.stream().flatMap(album -> album.getTracks())
                .filter(track -> track.getlength() > 60)
                .map(track -> track.getName())
                .collect(Collectors.toSet());
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {

        // 构造数据
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("length Was", 45));

        List<Track> tracks2 = Arrays.asList(new Track("Ni Jiu Bu Yao Xiang Qi Wo", 524),
                new Track("Wen", 378),
                new Track("ChuMai", 45));

        Album album = new Album(tracks);
        Album album2 = new Album(tracks2);

        List<Album> albums = Arrays.asList(album, album2);

        Set<String> trackNames = findLongTracks4(albums);
        System.out.println(trackNames);

    }
}

class Album {

    List<Track> trackList;

    public Album(List<Track> trackList) {
        this.trackList = trackList;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public Stream<Track> getTracks() {
        return trackList.stream();
    }
}

class Track {
    private String name;
    private Integer length;

    public Track(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getlength() {
        return length;
    }

    public void setlength(Integer length) {
        this.length = length;
    }
}