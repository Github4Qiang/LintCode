package dstructure.heap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Polylanger on 10/3/2017.
 */
public class BuildingsOutline {

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader(".\\src\\main\\java\\dstructure\\heap\\15.in");
        BufferedReader buffer = new BufferedReader(reader);
        String[] lines = buffer.readLine().split("],\\[");
        int[][] buildings = new int[lines.length][3];

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(",");
            int left = i == 0 ?
                    Integer.parseInt(line[0].substring(2)) : Integer.parseInt(line[0]);
            int right = Integer.parseInt(line[1]);
            int high = i == lines.length - 1 ?
                    Integer.parseInt(line[2].substring(0, line[2].length() - 2)) : Integer.parseInt(line[2]);
            buildings[i] = new int[]{left, right, high};
        }

        BuildingsOutline outline = new BuildingsOutline();
        System.out.println(outline.buildingOutline(buildings));
    }

//    /**
//     * @param buildings: A list of lists of integers
//     * @return: Find the outline of those buildings
//     */
//    public List<List<Integer>> buildingOutline(int[][] buildings) {
//        if (buildings.length == 0)
//            return new ArrayList<>();
//
//        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
//        for (int[] building : buildings) {
//            left = left < building[0] ? left : building[0];
//            right = right > building[1] ? right : building[1];
//        }
//        int[] high = new int[right + 1];
//        for (int[] building : buildings) {
//            for (int i = building[0]; i < building[1]; i++) {
//                high[i] = high[i] > building[2] ? high[i] : building[2];
//            }
//        }
//
//        List<List<Integer>> outline = new ArrayList<>();
//        int tail = 0, head = 0, last = 0;
//        while (head < high.length) {
//            if (head == 0 && tail == 0 && high[head] != 0) {
//                tail = head;
//                last = high[head];
//            } else if (last != high[head]) {
//                if (last != 0) {
//                    Integer[] building = {tail, head, last};
//                    outline.add(Arrays.asList(building));
//                }
//                tail = head;
//                last = high[head];
//            }
//            head += 1;
//        }
//        return outline;
//    }

//    /**
//     * @param buildings: A list of lists of integers
//     * @return: Find the outline of those buildings
//     */
//    public List<List<Integer>> buildingOutline(int[][] buildings) {
//        long start = System.currentTimeMillis();
//
//        if (buildings.length == 0)
//            return new ArrayList<>();
//        PriorityQueue<Integer[]> walls = new PriorityQueue<>(buildings.length * 2,
//                (Integer[] o1, Integer[] o2) ->
//                        !o1[0].equals(o2[0]) ? o1[0] - o2[0] : o2[1] - o1[1]);
//        for (int[] building : buildings) {
//            walls.add(new Integer[]{building[0], building[2]});
//            walls.add(new Integer[]{building[1], -building[2]});
//        }
//
//        System.out.println("建堆消耗的时间：" + (System.currentTimeMillis() - start) / 1000f + "s");
//        long upWallTime = 0, downWallTime = 0, downHigherTime = 0, downLowerTime = 0;
//
//        PriorityQueue<Integer[]> upWalls = new PriorityQueue<>(
//                (Integer[] o1, Integer[] o2) ->
//                        o2[1] - o1[1]);
//        int left = 0;
//        List<List<Integer>> outline = new ArrayList<>();
//        while (!walls.isEmpty()) {
//            Integer[] news = walls.poll();
//            int high = upWalls.isEmpty() ? 0 : upWalls.peek()[1];
//            if (news[1] > 0) {
//                upWallTime -= System.currentTimeMillis();
//                if (high == 0) {
//                    left = news[0];
//                } else if (news[1] > high) {
//                    Integer[] building = new Integer[]{left, news[0], high};
//                    outline.add(Arrays.asList(building));
//                    left = news[0];
//                }
//                upWalls.add(news);
//                upWallTime += System.currentTimeMillis();
//            } else if (news[1] < 0) {
//                downWallTime -= System.currentTimeMillis();
//                if (high + news[1] == 0 && left != news[0]) {
//                    downHigherTime -= System.currentTimeMillis();
//                    Integer[] building = new Integer[]{left, news[0], high};
//                    upWalls.poll();
//                    if ((!upWalls.isEmpty() && upWalls.peek()[1] + news[1] != 0) || upWalls.isEmpty()) {
//                        outline.add(Arrays.asList(building));
//                        left = news[0];
//                    }
//                    downHigherTime += System.currentTimeMillis();
//                } else if (high + news[1] > 0 || left == news[0]) {
//                    downLowerTime -= System.currentTimeMillis();
//                    Iterator<Integer[]> iterator = upWalls.iterator();
//                    while (iterator.hasNext() && iterator.next()[1] != -news[1]) ;
//                    iterator.remove();
//                    downLowerTime += System.currentTimeMillis();
//                }
//                downWallTime += System.currentTimeMillis();
//            }
//        }
//
//        System.out.println("整个while循环消耗的时间：" + (System.currentTimeMillis() - start) / 1000f + "s");
//        System.out.println("左边的墙消耗的时间：" + upWallTime + "ms");
//        System.out.println("右边的墙消耗的时间：" + downWallTime + "ms");
//        System.out.println("右边墙，比原来高的墙消耗的时间：" + downHigherTime + "ms");
//        System.out.println("右边墙，比原来低的墙消耗的时间：" + downLowerTime + "ms");
//        return outline;
//    }


    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        if (buildings.length == 0)
            return new ArrayList<>();
        PriorityQueue<Integer[]> walls = new PriorityQueue<>(buildings.length * 2,
                (Integer[] o1, Integer[] o2) ->
                        !o1[0].equals(o2[0]) ? o1[0] - o2[0] : o2[1] - o1[1]);
        for (int[] building : buildings) {
            walls.add(new Integer[]{building[0], building[2]});
            walls.add(new Integer[]{building[1], -building[2]});
        }

        SortedMap<Integer, Integer> upWalls = new TreeMap<>();
        int left = 0;
        List<List<Integer>> outline = new ArrayList<>();
        while (!walls.isEmpty()) {
            Integer[] news = walls.poll();
            int high = upWalls.isEmpty() ? 0 : upWalls.lastKey();
            if (news[1] > 0) {
                if (high == 0) {
                    left = news[0];
                } else if (news[1] > high) {
                    Integer[] building = new Integer[]{left, news[0], high};
                    outline.add(Arrays.asList(building));
                    left = news[0];
                }
                int count = upWalls.getOrDefault(news[1], 0);
                upWalls.put(news[1], count + 1);
            } else if (news[1] < 0) {
                if (high + news[1] == 0 && left != news[0]) {
                    Integer[] building = new Integer[]{left, news[0], high};
                    int lastKey = upWalls.lastKey();
                    if (upWalls.get(lastKey) == 1)
                        upWalls.remove(lastKey);
                    else
                        upWalls.put(lastKey, upWalls.get(lastKey) - 1);

                    if ((!upWalls.isEmpty() && upWalls.lastKey() + news[1] != 0) || upWalls.isEmpty()) {
                        outline.add(Arrays.asList(building));
                        left = news[0];
                    }
                } else if (high + news[1] > 0 || left == news[0]) {
                    if (upWalls.get(-news[1]) == 1)
                        upWalls.remove(-news[1]);
                    else
                        upWalls.put(-news[1], upWalls.get(-news[1]) - 1);
                }
            }
        }
        return outline;
    }


//    /**
//     * @param buildings: A list of lists of integers
//     * @return: Find the outline of those buildings
//     */
//    public List<List<Integer>> buildingOutline(int[][] buildings) {
//        long start = System.currentTimeMillis();
//
//        if (buildings.length == 0)
//            return new ArrayList<>();
//        PriorityQueue<Integer[]> walls = new PriorityQueue<>(buildings.length * 2,
//                (Integer[] o1, Integer[] o2) ->
//                        !o1[0].equals(o2[0]) ? o1[0] - o2[0] : o2[1] - o1[1]);
//        for (int[] building : buildings) {
//            walls.add(new Integer[]{building[0], building[2]});
//            walls.add(new Integer[]{building[1], -building[2]});
//        }
//
//        System.out.println("建堆消耗的时间：" + (System.currentTimeMillis() - start) / 1000f + "s");
//        long upWallTime = 0, downWallTime = 0, downHigherTime = 0, downLowerTime = 0;
//
//        SortedMap<Integer, Integer> upWalls = new TreeMap<>();
//        int left = 0;
//        List<List<Integer>> outline = new ArrayList<>();
//        while (!walls.isEmpty()) {
//            Integer[] news = walls.poll();
//            int high = upWalls.isEmpty() ? 0 : upWalls.lastKey();
//            if (news[1] > 0) {
//                upWallTime -= System.currentTimeMillis();
//                if (high == 0) {
//                    left = news[0];
//                } else if (news[1] > high) {
//                    Integer[] building = new Integer[]{left, news[0], high};
//                    outline.add(Arrays.asList(building));
//                    left = news[0];
//                }
//                int count = upWalls.getOrDefault(news[1], 0);
//                upWalls.put(news[1], count + 1);
//                upWallTime += System.currentTimeMillis();
//            } else if (news[1] < 0) {
//                downWallTime -= System.currentTimeMillis();
//                if (high + news[1] == 0 && left != news[0]) {
//                    downHigherTime -= System.currentTimeMillis();
//                    Integer[] building = new Integer[]{left, news[0], high};
//
//                    int lastKey = upWalls.lastKey();
//                    if (upWalls.get(lastKey) == 1)
//                        upWalls.remove(lastKey);
//                    else
//                        upWalls.put(lastKey, upWalls.get(lastKey) - 1);
//
//                    if ((!upWalls.isEmpty() && upWalls.lastKey() + news[1] != 0) || upWalls.isEmpty()) {
//                        outline.add(Arrays.asList(building));
//                        left = news[0];
//                    }
//                    downHigherTime += System.currentTimeMillis();
//                } else if (high + news[1] > 0 || left == news[0]) {
//                    downLowerTime -= System.currentTimeMillis();
//                    if (upWalls.get(-news[1]) == 1)
//                        upWalls.remove(-news[1]);
//                    else
//                        upWalls.put(-news[1], upWalls.get(-news[1]) - 1);
//                    downLowerTime += System.currentTimeMillis();
//                }
//                downWallTime += System.currentTimeMillis();
//            }
//        }
//
//        System.out.println("整个while循环消耗的时间：" + (System.currentTimeMillis() - start) / 1000f + "s");
//        System.out.println("左边的墙消耗的时间：" + upWallTime + "ms");
//        System.out.println("右边的墙消耗的时间：" + downWallTime + "ms");
//        System.out.println("右边墙，比原来高的墙消耗的时间：" + downHigherTime + "ms");
//        System.out.println("右边墙，比原来低的墙消耗的时间：" + downLowerTime + "ms");
//        return outline;
//    }

}
