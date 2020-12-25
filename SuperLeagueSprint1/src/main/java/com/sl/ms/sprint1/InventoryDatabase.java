package com.sl.ms.sprint1;

import java.util.ArrayList;
import java.util.List;

public class InventoryDatabase {

  private List<Inventory> inventoryList = new ArrayList<Inventory>();

public List<Inventory> getInventoryList() {
	return inventoryList;
}

public void setInventoryList(List<Inventory> list) {
	this.inventoryList = list;
}

public void addInventoryList(List<Inventory> list) {
	this.inventoryList.addAll(list);
}
}
