package cn.zxbetter.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * demo
 *
 * @author devin
 */
public class Application {
    public static void main(String[] args) {
        Map<Long, List<String>> map = new HashMap<>();
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("aa");
        map.put(1L, a);

        List<String> b = new ArrayList<>();
        b.add("b");
        b.addAll(new ArrayList<>());
        map.put(2L, b);

        System.out.println(map.size());
        for (List<String> value : map.values()) {
            System.out.println(value);
        }
    }
}
