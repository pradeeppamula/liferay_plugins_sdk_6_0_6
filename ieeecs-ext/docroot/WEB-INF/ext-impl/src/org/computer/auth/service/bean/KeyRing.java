/**
 * 
 */
package org.computer.auth.service.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;



/**
 * Keys are kept within categories and within a category, they are sorted by
 * their price sort order.  Keys of the same price are merged (or'd) to create a single 
 * key for that price.
 * 
 * Once a key ring has been created, there should be one key for each price
 * (that has a key, some prices may not be active) within a catgory.
 * 
 * @author wberks
 *
 */
public class KeyRing implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3394259465484192008L;

	public List<KeyMask> getKeys(KeyCategory category) {
		List<KeyMask> keys = this.keyLists.get(category.getName());
		if (keys != null)
			return keys;
		else
			return new Vector<KeyMask>(0);
	}
	
	/**
	 * Add the list of keys to the specified category.  If there are already
	 * keys within that category, the new list will be added to them.  Keys
	 * within the same category that also have the same price, will be merged
	 * together to create a single key.
	 * 
	 * @param keys
	 * @param category
	 */
	public void addKeys(List<KeyMask> keys, KeyCategory category) {
		// Merge the existing list with the new list and
		// put it back
		this.keyLists.put(category.getName(), mergeLists(this.getKeys(category), keys));
	}
	
	public void addKey(KeyMask key) {
		
		// Cheat and just create a single entry list and use the addKeys function
		List<KeyMask> oneItemList = new Vector<KeyMask>(1);
		oneItemList.add(key);
		this.addKeys(oneItemList, key.getKeyCategory());
	}
	
	public void clearAll() {
		this.keyLists.clear();
	}
	
	public void clearCategory(KeyCategory category) {
		this.keyLists.remove(category.getName());
	}
	
	/**
	 * Merge the two lists of keys into a single list, sorted by Price sort order.
	 * 
	 * @param currentKeys
	 * @param newKeys
	 * @return
	 */
	private List<KeyMask> mergeLists(List<KeyMask> currentKeys, List<KeyMask> newKeys) {

		// Sort the current keys by price/sort order

		
		SortedMap<Short, KeyMask> listByPrice = new TreeMap<Short, KeyMask>();
		for (int i = 0; i < currentKeys.size(); i++) {
			KeyMask currentKey = currentKeys.get(i);
			// Look for an existing entry with this sort order
			KeyMask existingKey = listByPrice.get(currentKey.getPricing().getSortOrder());
			// If there is an entry
			if (existingKey != null) {
				// Merge them together
				existingKey.merge(currentKey);
				// and put it back into the list
				listByPrice.put(existingKey.getPricing().getSortOrder(), existingKey);
			}
			// Else, no existing key found
			else
				// Put the key into the list
				listByPrice.put(currentKey.getPricing().getSortOrder(), currentKey);
		}
		
		// Now, add in the new keys
		if (newKeys != null) {
			for (int i = 0; i < newKeys.size(); i++) {
				KeyMask newKey = newKeys.get(i);
				if (newKey == null) 
				{
					
				}
				else {
					// Look for an existing entry with this sort order
					KeyMask existingKey = listByPrice.get(newKey.getPricing().getSortOrder());
					// If there is an entry and it does not have the same code
					if ((existingKey != null) && !existingKey.getCode().equals(newKey.getCode())) {
						// Merge them together
						KeyMask mergedKey = existingKey.merge(newKey);
						// and put it back into the list
						listByPrice.put(existingKey.getPricing().getSortOrder(), mergedKey);
					}
					// Else, no existing key found
					else {
						// Put the key into the list
						listByPrice.put(newKey.getPricing().getSortOrder(), newKey);
					}
				}
			}
		}
		// Now extract the results into a list
		Set<Short> prices = listByPrice.keySet();
		List<KeyMask> results = new Vector<KeyMask>();
		for (Iterator<Short> itr = prices.iterator(); itr.hasNext(); ) {
			results.add(listByPrice.get(itr.next()));
		}
		return results;
	}
	
	public KeyRing copyOf() {
		KeyRing newKeyRing = new KeyRing();
		List<String> categories = this.getKeyCategories();
		for (int i = 0; i < categories.size(); i++) {
			String category = categories.get(i);
			List<KeyMask> keys = this.keyLists.get(category);
			List<KeyMask> keysCopy = new Vector<KeyMask>(keys.size());
			for (int j = 0; j < keys.size(); j++) {
				keysCopy.add(keys.get(j).copyOf());
			}
			newKeyRing.addKeys(keysCopy, new KeyCategory(category));
		}
		return newKeyRing;
	}
	
	public void addKeyRing(KeyRing keyRing) {
		List<String> categories = keyRing.getKeyCategories();
		for (int i = 0; i < categories.size(); i++) {
			KeyCategory category = new KeyCategory(categories.get(i));
			List<KeyMask> keys = keyRing.getKeys(category);
			List<KeyMask> keysCopy = new Vector<KeyMask>(keys.size());
			for (int j = 0; j < keys.size(); j++) {
				keysCopy.add(keys.get(j).copyOf());
			}
			this.addKeys(keysCopy, category);
		}
	}
	
	private List<String> getKeyCategories() {
		Set<String> keys = this.keyLists.keySet();
		List<String> results = new Vector<String>(keys.size());
		for (Iterator<String> itr = keys.iterator(); itr.hasNext(); )
			results.add(itr.next());
		return results;
	}
	
	public String toString() {
		StringBuffer results = new StringBuffer();
		List<String> categories = this.getKeyCategories();
		for (int i = 0; i < categories.size(); i++) {
			String category = categories.get(i);
			results.append(category + ":\n");
			List<KeyMask> keys = this.keyLists.get(category);
			for (int j = 0; j < keys.size(); j++) {
				results.append("\t" + keys.get(j).toString() + "\n");
			}
		}
		return results.toString();
	}
	
	private HashMap<String, List<KeyMask>> keyLists = new HashMap<String, List<KeyMask>>();
	

}
