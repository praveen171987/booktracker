package com.softwaresmithy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapList<K, E> {
  private Map<K, List<E>> map = new HashMap<K, List<E>>();

  public void addList(K key, List<E> newItems) {
    if (map.containsKey(key)) {
      map.get(key).addAll(new ArrayList<E>(newItems));
    } else {
      map.put(key, newItems);
    }
  }

  public void addItem(K key, E newItem) {
    if (map.containsKey(key)) {
      map.get(key).add(newItem);
    } else {
      map.put(key, new ArrayList<E>());
      map.get(key).add(newItem);
    }
  }

  public List<E> getList(K key) {
    return map.get(key);
  }
}
