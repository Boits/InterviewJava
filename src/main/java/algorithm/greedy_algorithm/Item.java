package algorithm.greedy_algorithm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Item implements Comparable<Item> {

    private final String name;
    private final int weight;
    private final int cost;

    @Override
    public int compareTo(Item o) {
        return this.cost > o.cost ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Item: name = " + name + "; weight = " + weight + "; cost = " + cost;
    }
}
