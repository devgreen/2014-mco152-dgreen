package green.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	private ArrayList<Item> items;
	private Map<String, Item> map;

	public Inventory() {
		items = new ArrayList<Item>();
		map = new HashMap<String, Item>();

	}

	public void load(String inventoryFilename) throws IOException {
		Scanner inputFile = new Scanner(new File(inventoryFilename));
		while (inputFile.hasNext()) {
			String item = inputFile.nextLine();
			String[] splitItem = item.split(",");
			String code = splitItem[0];
			String name = splitItem[1];
			double price = Double.parseDouble(splitItem[2]);
			int quantity = Integer.parseInt(splitItem[3]);
			Item newItem = new Item(code, name, price, quantity);
			items.add(newItem);
			Item value = map.get(code);
			if (value == null) {
				map.put(code, newItem);
			}
			// System.out.println (items[i].toString());

		}
		inputFile.close();
	}

	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		Item value = map.get(code);
		if (value != null) {
			return value;

		}
		return null;

	}

	/**
	 * 
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		String code = item.getCode();
		Item value = map.get(code);
		if (value == null) {
			map.put(code, item);
		}
		
		items.add(item);

	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */
	public void removeOne(String code) {
		Item value = map.get(code);
		value.setQuantity(value.getQuantity() - 1);

	}

	/**
	 * 
	 * @param code
	 * @return true if the Item exists and there is at least one quantity,
	 *         otherwise false.
	 */
	public boolean isEmpty(String code) {
		Item value = map.get(code);
		int quantity = value.getQuantity();
		if (quantity == 0) {
			return true;

		}
		return false;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @
	 * price x quantity\n
	 */
	@Override
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("$0.00");
		StringBuilder info = new StringBuilder();
		for (int i = 0; i < items.size(); i++) {
			info.append(items.get(i).getCode());
			info.append(" ");
			info.append(items.get(i).getName());
			info.append(" @ ");
			info.append(formatter.format(items.get(i).getPrice()));
			info.append(" x ");
			info.append(items.get(i).getQuantity());
			if (i < items.size() - 1) {
				info.append("\n");
			}

		}
		return info.toString();

	}

}
