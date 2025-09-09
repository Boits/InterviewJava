package algorithm.greedy_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GreedyAlgorithm {

    public static void method() {

        List<Item> items = new ArrayList<>();
        items.add(new Item("гитара", 7, 800));
        items.add(new Item("утюг", 6, 500));
        items.add(new Item("чайник", 3, 300));
        items.add(new Item("лампа", 4, 500));
        items.add(new Item("телевизор", 15, 2000));
        items.add(new Item("ваза", 2, 450));
        items.add(new Item("миксер", 1, 400));
        items.add(new Item("блендер", 3, 200));

        Collections.sort(items);

        System.out.println(items);

        Bag bag = new Bag(30);

//        fillBackpackByGreedyAlgorithm(bag, items); //Вес рюкзака состовляет - 29, общая стоимость вещей в рюкзаке - 3700
        //or
        effectiveFillBackpack(bag, items); //Вес рюкзака состовляет - 29, общая стоимость вещей в рюкзаке - 4150

        System.out.println("Вес рюкзака состовляет - " + bag.getCurrentWeight() +
                ", общая стоимость вещей в рюкзаке - " + bag.getCurrentCost());
    }

    /**
     * Заполняется рюкзак по жадному алгоритму.
     * <p>
     * Мы начинаем проходить по отсортированному по стоимости списку элементов
     * и складывать их в сумку, если позволяет вместимость.
     * Если же не позволяет, элемент будет пропущен и продолжится проход по остальным элементам до конца списка.
     */
    private static void fillBackpackByGreedyAlgorithm(Bag bag, List<Item> items) {
        for (Item item : items) {
            if (bag.getMaxWeight() > bag.getCurrentWeight() + item.getWeight()) {
                bag.addItem(item);
            }
        }
    }

    private static void effectiveFillBackpack(Bag bag, List<Item> items) {
        Map<Double, Item> sortByRatio = new TreeMap<>(Collections.reverseOrder());
        for (Item item : items) {
            sortByRatio.put((double)item.getCost() / item.getWeight(), item);
        }

        for (Map.Entry<Double, Item> entry : sortByRatio.entrySet()) {
            if(bag.getMaxWeight() > bag.getCurrentWeight() + entry.getValue().getWeight()) {
                bag.addItem(entry.getValue());
            }
        }
    }
}
