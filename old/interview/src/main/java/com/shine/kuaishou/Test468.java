package com.shine.kuaishou;

/**
 * description:验证IP地址
 * 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/20 17:30
 */
public class Test468 {
	public String validIPAddress(String IP) {
		if (IP.contains(".")) {
			String[] args = IP.split("\\.", -1);
			if (args.length == 4) {
				for (String arg : args) {
					try {
						if (arg.charAt(0) == '0' && arg.length() > 1 || arg.length() > 3) {
							return "Neither";
						} else if (Integer.parseInt(arg) > 255 || Integer.parseInt(arg) < 0 || arg.contains("-")) { //-0
							return "Neither";
						}
					} catch (Exception ex) {
						return "Neither";
					}
				}
				return "IPv4";
			} else {
				return "Neither";
			}

		} else if (IP.contains(":")) {
			String[] args = IP.split(":", -1);
			if (args.length == 8) {
				for (int i = 0; i < args.length; i++) {
					if (args[i].length() > 4 || args[i].length() == 0) {
						return "Neither";
					} else {
						char[] ca = args[i].toCharArray();
						for (char c : ca) {
							if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')))
								return "Neither";
						}
					}
				}
				return "IPv6";
			} else {
				return "Neither";
			}
		} else {
			return "Neither";
		}
	}
}
