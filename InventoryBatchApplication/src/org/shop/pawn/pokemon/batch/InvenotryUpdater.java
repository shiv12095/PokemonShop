package org.shop.pawn.pokemon.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class InvenotryUpdater {

	public static void main(String args[]) {
		try {
			System.out.println("Inventory update cron job started");
			Connection conn = createConnection();
			Collection<Integer> newOrderIds = getNewOrders(conn);
			Map<Integer, Integer> orderedItems = getOrderedLineItems(newOrderIds, conn);
			udpateInventory(orderedItems, conn);
			udpateOrderStatus(newOrderIds, conn);
			conn.close();
			System.out.println("Inventory update cron job completed");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/dent/h2db/PokemonPawnShopDB", "sa", "");
		return conn;
	}

	private static Collection<Integer> getNewOrders(Connection conn) throws SQLException {
		Collection<Integer> orderIds = new ArrayList<Integer>();
		ResultSet rset = conn.createStatement().executeQuery("select ID from CUSTOMER_ORDER where STATUS =  'New'");
		while (rset.next()) {
			orderIds.add(new Integer(rset.getInt("ID")));
		}
		return orderIds;
	}

	private static Map<Integer, Integer> getOrderedLineItems(Collection<Integer> newOrderIds, Connection conn)
			throws SQLException {
		String orderIds = getOrderIdsString(newOrderIds);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		ResultSet rset = conn.createStatement()
				.executeQuery("select ITEM_ID, ITEM_QUANTITY from CUSTOMER_ORDER_LINE_ITEM where CUSTOMER_ORDER_ID_FK IN " + orderIds);
		while (rset.next()) {
			Integer itemId = new Integer(rset.getInt("ITEM_ID"));
			Integer quantity = new Integer(rset.getInt("ITEM_QUANTITY"));
			int originalQuantity = map.getOrDefault(itemId, 0);
			map.put(itemId, quantity + originalQuantity);
		}
		return map;
	}

	private static void udpateInventory(Map<Integer, Integer> orderedItems, Connection conn) throws SQLException {
		List<Integer> list = new ArrayList<>(orderedItems.keySet());
		String orderIds = getOrderIdsString(list);
		ResultSet rset = conn.createStatement()
				.executeQuery("select ID, AVAILABLE_QUANTITY from ITEM where ID IN " + orderIds);

		while (rset.next()) {
			Integer id = new Integer(rset.getInt("ID"));
			if (orderedItems.containsKey(id)) {
				orderedItems.put(id, rset.getInt("AVAILABLE_QUANTITY") - orderedItems.get(id));
			}
		}
		for (Entry<Integer, Integer> entry : orderedItems.entrySet()) {
			conn.createStatement().executeUpdate("UPDATE ITEM SET AVAILABLE_QUANTITY = '" + entry.getValue() + "'"
					+ "WHERE ID = " + "'" + entry.getKey() + "'");
		}
	}

	private static void udpateOrderStatus(Collection<Integer> newOrderIds, Connection conn) throws SQLException {
		String orderIds = getOrderIdsString(newOrderIds);
		conn.createStatement().executeUpdate("UPDATE CUSTOMER_ORDER SET STATUS = 'Done' WHERE ID IN " + orderIds);
	}

	private static String getOrderIdsString(Collection<Integer> orderIds) {
		if(orderIds.size() == 0) {
			return "()";
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(");
		for (Integer integer : orderIds) {
			stringBuilder.append(integer);
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
