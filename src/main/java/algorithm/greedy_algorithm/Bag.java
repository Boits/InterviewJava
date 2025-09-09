package algorithm.greedy_algorithm;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bag {

    private final int maxWeight;
    private final List<Item> items;
    private int currentWeight; //текущий вес, который мы увеличиваем при добавлении нового предмета в методе addItem.
    private int currentCost; //текущая стоимость всех вещей в рюкзаке, которую мы увеличиваем при добавлении нового предмета в методе addItem.

    public Bag(int maxWeight) {
        this.maxWeight = maxWeight;
        items = new ArrayList<>();
        currentCost = 0;
    }

    public void addItem(Item item) {
        items.add(item);
        currentWeight += item.getWeight();
        currentCost += item.getCost();
    }
}
