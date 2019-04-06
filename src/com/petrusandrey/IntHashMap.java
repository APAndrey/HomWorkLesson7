package com.petrusandrey;

public class IntHashMap implements IntMap{
    private class Entry {
        private int key;
        private int value;
        private Entry next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    private Entry[] table = new Entry[16];
    private final double LOAD_FACTOR = 0.75;

    private int indexCalc(int key) {
        return Math.abs(key % table.length);
    }

    private boolean loadCalc() {
        return  size / table.length >= LOAD_FACTOR;
    }

    private void expandTable() {
        Entry[] newTable = new Entry[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry tmp = table[i];
                int newIndex = tmp.key % newTable.length;
                newTable[newIndex] = tmp;
            }
        }
        table = newTable;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean containsKey(int key) {
        Entry tmp = table[indexCalc(key)];
        while (tmp != null) {
            if (tmp.key == key) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(int value) {
        for (int i = 0; i < table.length; i++) {
            Entry tmp = table[i];
            while (tmp != null) {
                if (tmp.value == value) {
                    return true;
                }
                tmp = tmp.next;
            }
        }
        return false;
    }

    @Override
    public int get(int key) {
        Entry tmp = table[indexCalc(key)];
        while (tmp.key != key && tmp.next != null){
            tmp = tmp.next;
        }
        return tmp.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(int key, int value) {
        Entry newEntry = new Entry(key, value);
        if (table[indexCalc(key)] == null) {
            table[indexCalc(key)] = newEntry;
        }
        else {
            if (loadCalc()) {
                expandTable();
            }
            Entry last = table[indexCalc(key)];
            table[indexCalc(key)] = newEntry;
            newEntry.next = last;
        }
        size++;
    }

    @Override
    public void remove(int key) {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry tmp = table[i];
                while (tmp != null) {
                    sb.append(tmp.key + "=" + tmp.value + ", ");
                    tmp = tmp.next;
                }
            }
        }
        return sb.toString();
    }
}
