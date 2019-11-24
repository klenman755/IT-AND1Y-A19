package hello;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


import model.ClosedBill;
import model.PLU;
import model.PluGroup;
import model.User;

public class LocalDataStorage {

	private User[] users;
	private PluGroup[] pluGroups;
	private PLU[] PLUs;
	private ArrayList<ClosedBill> closedBills;
	private static LocalDataStorage  instance;

	private LocalDataStorage() {
		pluGroups = new PluGroup[] { new PluGroup("Non Alcoholic Drinks", 1), new PluGroup("Alcoholic Drinks", 2),
				new PluGroup("Breakfast", 3), new PluGroup("Lunch", 4) };
		PLUs = new PLU[] { new PLU("Faxe Kondi", 20.0, 1, 1), new PLU("Coffee", 25.0, 2, 1),
				new PLU("Vodka", 25.0, 3, 2), new PLU("Beer", 35.0, 4, 2), new PLU("Chicken sandwich", 27.0, 5, 3),
				new PLU("Hot Coffee with vodka + 1 cigarette", 30, 6, 3), new PLU("Meatballs", 53.0, 7, 4),
				new PLU("Salmon", 78.0, 8, 4) };
		users = new User[] { new User("User1", "011"), new User("User2", "2067") };
		closedBills = new ArrayList<ClosedBill>();
	}

	public static LocalDataStorage getInstance() {
		if (instance == null) {
			instance = new LocalDataStorage();
		}
		return instance;

	}

	public User login(String quickPassword) {
		for(int i =0;i<users.length;i++) {
			if(users[i].getQuickPassword().equals(quickPassword)) {
				return users[i];
			}
		}
		return null;
	}
	
	public User[] getUsers() {
		
		return users;
	}

	public PluGroup[] getPluGroups() {
		return pluGroups;
	}

	public PLU[] getPLUs() {
		return PLUs;
	}

	public void addClosedBill(ClosedBill cb) {
		closedBills.add(cb);
		System.out.println(closedBills);
	}

	public BigDecimal getSalesOfEmployee(String employeeId) {
		BigDecimal sales = BigDecimal.ZERO;
		if (!employeeId.equals("-1")) {
			for (ClosedBill cb : closedBills) {
				if (cb.getClosedBy().equals(employeeId)) {
					sales = sales.add(cb.getFullPrice());
				}
			}
		} else {
			for (ClosedBill cb : closedBills) {
				sales = sales.add(cb.getFullPrice());
			}

		}

		return sales.setScale(2, RoundingMode.HALF_UP);

	}

}
