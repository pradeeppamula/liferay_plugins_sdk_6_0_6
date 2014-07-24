package org.computer.auth.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressUtil {
	
	public static String[] getValidIPAddress(String ipAddresses) {

		List<String> addresses = new ArrayList<String>();

		Pattern ipAddrPattern = Pattern.compile("[0-9]*\\.[0-9]*\\.[0-9]*\\.[0-9]*");
		Matcher m = ipAddrPattern.matcher(ipAddresses);
		for (int i = 0; m.find(); i++) {
			String s = m.group(0);
			addresses.add(i, s);
		}
		String[] addressArray = new String[addresses.size()];
		addressArray = addresses.toArray(addressArray);
		return addressArray;
	}

}
