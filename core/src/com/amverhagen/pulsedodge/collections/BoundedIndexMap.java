package com.amverhagen.pulsedodge.collections;

import java.util.ArrayList;

public class BoundedIndexMap<K> {
	private K[] map;

	public BoundedIndexMap(int size) {
		if (size < 1)
			throw new IndexOutOfBoundsException("Index must be greater than 1.");
		map = newMap(size);
	}

	@SuppressWarnings({ "unchecked" })
	private K[] newMap(int size) {
		return (K[]) new Object[size];
	}

	public K get(int index) {
		if (index >= this.getSize() || index < 0)
			return null;
		return map[index];
	}

	public K remove(int index) {
		K item = map[index];
		map[index] = null;
		return item;
	}

	public void put(int index, K item) {
		map[index] = item;
	}

	public boolean hasValue(int index) {
		if (this.get(index) != null)
			return true;
		return false;
	}

	public ArrayList<K> getEntries() {
		ArrayList<K> items = new ArrayList<K>();
		for (int i = 0; i < map.length; i++) {
			if (map[i] != null) {
				items.add(map[i]);
			}
		}
		return items;
	}

	public int getSize() {
		return map.length;
	}

}
