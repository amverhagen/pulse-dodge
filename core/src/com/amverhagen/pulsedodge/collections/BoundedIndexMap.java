package com.amverhagen.pulsedodge.collections;

import java.util.ArrayList;

public class BoundedIndexMap<K> {
	private K[] map;
	public boolean wrappable;

	public BoundedIndexMap(int size) {
		if (size < 1)
			throw new IndexOutOfBoundsException("Index must be greater than or greater than 1.");
		map = newMap(size);
		wrappable = false;
	}

	@SuppressWarnings({ "unchecked" })
	private K[] newMap(int size) {
		return (K[]) new Object[size];
	}

	public K getValueAtIndex(int index) {
		if (index >= this.getMapSize() || index < 0)
			return null;
		return map[index];
	}

	public K removeEntryAtIndex(int index) {
		K item = map[index];
		map[index] = null;
		return item;
	}

	public void putEntryAtIndex(int index, K item) {
		map[index] = item;
	}

	public boolean hasValueAtIndex(int index) {
		if (this.getValueAtIndex(index) != null)
			return true;
		return false;
	}

	public void shiftAllEntriesUpOneIndex() {
		if (this.getMapSize() > 1) {
			K lastEntry = this.getValueAtIndex(this.getMapSize() - 1);
			if (!wrappable && lastEntry != null) {
				return;
			}
			for (int i = this.getMapSize() - 2; i >= 0; i--) {
				K tempEntry = this.removeEntryAtIndex(i);
				this.putEntryAtIndex((i + 1), tempEntry);
			}
			this.putEntryAtIndex(0, lastEntry);
		}
	}

	public void shiftAllEntriesDownOneIndex() {
		if (this.getMapSize() > 1) {
			K firstEntry = this.getValueAtIndex(0);
			if (!wrappable && firstEntry != null) {
				return;
			}
			for (int i = 1; i < this.getMapSize(); i++) {
				K tempEntry = this.removeEntryAtIndex(i);
				this.putEntryAtIndex((i - 1), tempEntry);
			}
			this.putEntryAtIndex(this.getMapSize() - 1, firstEntry);
		}
	}

	public ArrayList<K> getAllEntriesAsList() {
		ArrayList<K> items = new ArrayList<K>();
		for (int i = 0; i < map.length; i++) {
			if (map[i] != null) {
				items.add(map[i]);
			}
		}
		return items;
	}

	public int getMapSize() {
		return map.length;
	}
}
