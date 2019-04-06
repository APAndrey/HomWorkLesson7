package com.petrusandrey;

public class Main {

    public static void main(String[] args) {
        IntMap map = new IntHashMap();
        map.put(3, 4);
        map.put(2, 9);
        map.put(15, 88);

        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(18));
        System.out.println("Size: " + map.size());

        System.out.println("containsKey: "+map.containsKey(18));
        System.out.println("containsValue: "+map.containsValue(11));
        System.out.println(map);
    }
}
