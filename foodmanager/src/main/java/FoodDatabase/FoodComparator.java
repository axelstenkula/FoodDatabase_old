package FoodDatabase;

import java.util.Comparator;

public class FoodComparator implements Comparator<Food> {

	public int compare(Food o1, Food o2) {

		String a = o1.getFoodName();
		if (a == null) {
			a = "";
		}

		String b = o2.getFoodName();
		if (b == null) {
			b = "";
		}

		return a.compareToIgnoreCase(b);
	}
}