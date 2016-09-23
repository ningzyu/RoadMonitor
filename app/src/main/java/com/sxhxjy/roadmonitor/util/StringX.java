package com.sxhxjy.roadmonitor.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class StringX {
	public static final String ZEROS = "00000000000000000000000000000000";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String EMPTY_STRING = "";
	public static final String COMMA = ",";
	public static final String DIGEST_MD5 = "MD5";
	public static final StringX STRINGX = new StringX();
	public static final char[] TRIM_CHAR = { ' ', '\t', '\n', '\r' };

	public static boolean equals(Object o1, Object o2) {
		if (o1 == o2)
			return true;
		if ((o1 == null) || (o2 == null))
			return false;
		return o1.toString().equals(o2.toString());
	}

	public static String untrimXML(String xml) {
		StringBuffer buf = new StringBuffer();
		int tagStart = -1;
		int tagEnd = -1;
		Stack tag = new Stack();
		for (int i = 0; i < xml.length(); i++) {
			char ch = xml.charAt(i);
			buf.append(ch);
			if ((i > 0) && (i < xml.length() - 1) && (xml.charAt(i - 1) == '<')
					&& (ch == '/') && (xml.charAt(i + 1) == '>')) {
				buf.append((String) tag.pop());
				tagStart = tagEnd = -1;
			} else {
				if (ch == '<')
					tagStart = i + 1;
				if (ch == '>')
					tagEnd = i;
				if ((tagStart < 0) || (tagEnd <= tagStart)) {
					continue;
				}
				tag.push(xml.substring(tagStart, tagEnd));
				tagStart = tagEnd = -1;
			}
		}

		return buf.toString();
	}

	public static String trimXML(String xml) {
		StringBuffer buf = new StringBuffer();
		boolean isEndTag = false;
		for (int i = 0; i < xml.length(); i++) {
			char ch = xml.charAt(i);
			if ((i > 0) && (xml.charAt(i - 1) == '<') && (ch == '/')) {
				isEndTag = true;
				buf.append(ch);
			} else if (ch == '>') {
				isEndTag = false;
			}
			if (isEndTag)
				continue;
			buf.append(ch);
		}
		return buf.toString();
	}

	public static String checkXMLUnicode(String str) throws Exception {
		int i = 0;
		for (i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch > 65533)
				break;
			if (ch >= ' ')
				continue;
			if (((ch != '\t' ? 1 : 0) & (ch != '\n' ? 1 : 0) & (ch != '\r' ? 1
					: 0)) != 0)
				break;
		}
		if (i >= str.length())
			return str;

		StringBuffer buf = new StringBuffer();
		for (i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch > 65533)
				continue;
			if (ch < ' ')
				if (((ch != '\t' ? 1 : 0) & (ch != '\n' ? 1 : 0) & (ch != '\r' ? 1
						: 0)) != 0)
					continue;
				else
					buf.append(ch);
		}
		return buf.toString();
	}

	public static String byte2hex(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1)
				hex = '0' + hex;
			ret = ret + hex.toUpperCase();
		}
		return ret;
	}

	public static byte[] hex2byte(String hex) {
		byte[] bts = new byte[hex.length() / 2];
		int i = 0;
		for (int j = 0; j < bts.length; j++) {
			bts[j] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			i += 2;
		}
		return bts;
	}

	public static String removeNotInSpecificChar(String str, char min, char max) {
		if (nullity(str))
			return str;
		StringBuffer buf = new StringBuffer(str.length());
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if ((ch >= min) && (ch <= max))
				buf.append(ch);
		}
		return buf.toString();
	}

	public static boolean isAllSpecificChar(String str, char specificChar) {
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) != specificChar)
				return false;
		return true;
	}

	public static boolean isAllSpecificChar(String str, String specificStr) {
		for (int i = 0; i < str.length(); i++)
			if (specificStr.indexOf(str.charAt(i)) < 0)
				return false;
		return true;
	}

	public static boolean isAllSpecificChar(String str, char min, char max) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if ((ch < min) || (ch > max))
				return false;
		}
		return true;
	}

	public static boolean isAllSpecificChar(String str, char[][] range) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			boolean ok = false;
			for (int j = 0; j < range.length; j++) {
				if ((ch < range[j][0]) || (ch > range[j][1]))
					continue;
				ok = true;
				break;
			}

			if (!ok)
				return false;
		}
		return true;
	}

	public static boolean contain(String[] keys, String key, boolean ignoreCase) {
		for (int i = 0; i < keys.length; i++) {
			String k = keys[i].trim();
			if ((k.equals(key)) || ((ignoreCase) && (k.equalsIgnoreCase(key))))
				return true;
		}
		return false;
	}

	public static String getMessageFormat(int args) {
		if (args == 0)
			return "";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < args; i++) {
			if (i != 0)
				buf.append(" - ");
			buf.append('{');
			buf.append(i);
			buf.append('}');
		}
		return buf.toString();
	}

	public static StringBuffer map2queryString(Map map) {
		StringBuffer buf = new StringBuffer();
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object v = map.get(key);
			if (buf.length() > 0)
				buf.append('&');
			buf.append(key);
			buf.append('=');
			buf.append(v);
		}
		return buf;
	}

	public static int lastIndexOf(String str, int ch, int times) {
		for (int i = str.length() - 1; i >= 0; i--) {
			if (ch != str.charAt(i))
				continue;
			times--;
			if (times <= 0)
				return i;
		}

		return 0;
	}

	public static String printStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static String stackTrace(StackTraceElement[] stes, int depth) {
		StringBuffer buf = new StringBuffer();
		if ((stes == null) && (depth == 0))
			return buf.toString();
		for (int i = 0; (i < stes.length) && ((depth < 0) || (i < depth)); i++) {
			buf.append(stes[i]);
			buf.append('\n');
		}
		return buf.toString();
	}

	public static String arrayToDelimitedString(List arr, String prefix,
			String delim) {
		if (arr == null)
			return "";

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.size(); i++) {
			if (i > 0)
				sb.append(delim);
			sb.append(prefix + arr.get(i));
		}
		return sb.toString();
	}

	public static boolean isVariableName(String name) {
		if (((name.charAt(0) < 'a') || (name.charAt(0) > 'z'))
				&& ((name.charAt(0) < 'A') || (name.charAt(0) > 'Z')))
			return false;
		for (int i = 1; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (((ch < 'a') || (ch > 'z')) && ((ch < 'A') || (ch > 'Z'))
					&& ((ch < '0') || (ch > '9')) && (ch != '_'))
				return false;
		}
		return true;
	}

	public static Date timeMillis2Date(String timeMillis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.parseLong(timeMillis));
		return c.getTime();
	}

	public static String toHTML(String text) {
		return text.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("\n", "<br>")
				.replaceAll(" ", "&nbsp;")
				.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	}

	public static String url2xml(String text) {
		return text.replaceAll("%3C", "<").replaceAll("%3E", ">")
				.replaceAll("%2F", "/").replaceAll("%20", " ")
				.replaceAll("%09", "\t");
	}

	public static String array2json(List columnName, List column) {
		StringBuffer buf = new StringBuffer();
		buf.append('{');
		for (int i = 0; i < column.size(); i++) {
			if (i != 0)
				buf.append(',');
			buf.append(columnName.get(i));
			buf.append(":'");
			String str = column.get(i).toString();
			if (str.indexOf('\'') >= 0)
				str = str.replace("'", "\\'");
			if (str.indexOf('\n') >= 0)
				str = str.replace("\n", "\\n");
			buf.append(str);
			buf.append('\'');
		}
		buf.append('}');
		return buf.toString();
	}

	public static String array2json(Object[] columnName, List column) {
		StringBuffer buf = new StringBuffer();
		buf.append('{');
		for (int i = 0; i < column.size(); i++) {
			if (i != 0)
				buf.append(',');
			buf.append(columnName[i]);
			buf.append(":'");
			String str = column.get(i).toString();
			if (str.indexOf('\'') >= 0)
				str = str.replace("'", "\\'");
			if (str.indexOf('\n') >= 0)
				str = str.replace("\n", "\\n");
			buf.append(str);
			buf.append('\'');
		}
		buf.append('}');
		return buf.toString();
	}

	public static String int2str(String number, int len) {
		boolean negative = number.charAt(0) == '-';
		if (negative)
			number = number.substring(1);
		StringBuffer buf = new StringBuffer("00000000000000000000000000000000");
		buf.append(number);
		return negative ? '-' + buf.substring(buf.length() - len + 1) : buf
				.substring(buf.length() - len);
	}

	public static String float2str(String number, int decimal) {
		if (decimal <= 0)
			return number;
		int index = number.indexOf('.');
		if (index < 0)
			return number
					+ '.'
					+ "00000000000000000000000000000000"
							.substring("00000000000000000000000000000000"
									.length() - decimal);
		String intPart = number.substring(0, index);
		String decimalPart = number.substring(index + 1)
				+ "00000000000000000000000000000000";
		return intPart + '.' + decimalPart.substring(0, decimal);
	}

	public static String float2str(String number, int len, int decimal,
			boolean withDot) {
		if (decimal <= 0)
			withDot = false;

		boolean negative = number.charAt(0) == '-';
		if (negative)
			number = number.substring(1);
		char dot = '.';
		int index = number.indexOf(dot);
		String temp = null;
		temp = "00000000000000000000000000000000"
				+ (index >= 0 ? number.substring(0, index) : number);
		String value = temp.substring(temp.length() - len + decimal
				+ (withDot ? 1 : 0));
		if (withDot)
			value = value + dot;
		temp = index >= 0 ? number.substring(index + 1)
				+ "00000000000000000000000000000000"
				: "00000000000000000000000000000000";
		value = value + temp.substring(0, decimal);
		return negative ? '-' + value.substring(1) : value.toString();
	}

	public static boolean isContainCN(String str) {
		if (str == null)
			return false;
		for (int i = 0; i < str.length(); i++)
			if (isCN(str.charAt(i)))
				return true;
		return false;
	}

	public static boolean isCN(char c) {
		return c > 'ÿ';
	}

	public static int bcdLength(String str) {
		int len = 0;
		boolean cn = false;
		for (int i = 0; i < str.length(); i++) {
			if (isCN(str.charAt(i))) {
				if (!cn)
					len++;
				len += 2;
				cn = true;
			} else {
				if (cn)
					len++;
				len++;
				cn = false;
			}
		}
		return len;
	}

	public static String byte2binary(byte b) {
		StringBuffer buf = new StringBuffer();
		for (int i = 7; i >= 0; i--)
			buf.append((1 << i & b) == 0 ? '0' : '1');
		return buf.toString();
	}

	public static String bytes2binary(byte[] bs, String delim) {
		return bytes2binary(bs, 0, bs.length, delim);
	}

	public static String bytes2binary(byte[] bs, int offset, int len,
			String delim) {
		StringBuffer buf = new StringBuffer();
		for (int j = offset; j < offset + len; j++) {
			if (buf.length() > 0)
				buf.append(delim);
			byte b = bs[j];
			for (int i = 7; i >= 0; i--)
				buf.append((1 << i & b) == 0 ? '0' : '1');
		}
		return buf.toString();
	}

	public static String bytes2str(byte[] bytes, int column) {
		if ((bytes == null) || (bytes.length == 0))
			return "";
		StringBuffer buf = new StringBuffer();
		int rows = bytes.length / column;
		for (int i = 0; i <= rows; i++) {
			StringBuffer byteBuf = new StringBuffer();
			StringBuffer textBuf = new StringBuffer();
			int rowLen = column < bytes.length - i * column ? column
					: bytes.length - i * column;
			for (int j = 0; j < rowLen; j++) {
				int intVal = bytes[(i * column + j)];
				String hexString = Integer.toHexString(intVal);
				if (hexString.length() == 1)
					hexString = "0" + hexString;
				else if (hexString.length() > 2)
					hexString = hexString.substring(hexString.length() - 2);
				byteBuf.append(hexString + " ");

				if ((intVal <= 127) && (intVal >= 32))
					textBuf.append((char) intVal);
				else
					textBuf.append('.');
			}
			if (byteBuf.length() < column * 3) {
				int spacesLen = column * 3 - byteBuf.length();
				for (int k = 0; k < spacesLen; k++)
					byteBuf.append(' ');
			}
			if (textBuf.length() < column) {
				int spacesLen = column - textBuf.length();
				for (int k = 0; k < spacesLen; k++)
					textBuf.append(' ');
			}
			buf.append(byteBuf.toString() + "   " + textBuf.toString());
			if (rows <= 1)
				continue;
			buf.append(System.getProperty("line.separator"));
		}
		return buf.toString();
	}

	public static String str2utf8(String str) {
		if (str == null)
			return "";

		StringBuffer sb = new StringBuffer();

		sb.setLength(0);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c > 'ÿ') {
				sb.append("\\u");
				int j = c >>> '\b';
				String tmp = Integer.toHexString(j);
				if (tmp.length() == 1)
					sb.append('0');
				sb.append(tmp);
				j = c & 0xFF;
				tmp = Integer.toHexString(j);
				if (tmp.length() == 1)
					sb.append('0');
				sb.append(tmp);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String utf82str(String str) {
		if (str == null)
			return null;
		if (str.indexOf("\\u") < 0)
			return str;
		StringBuffer sb = new StringBuffer();

		sb.setLength(0);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c == '\\') && (i + 1 < str.length())
					&& (str.charAt(i + 1) == 'u') && (i + 5 < str.length())) {
				sb.append(utf8Ch2Str(str.substring(i + 2, i + 6)));
				i += 5;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	static String utf8Ch2Str(String str) {
		try {
			int c1 = Integer.parseInt(str.substring(0, 2), 16);
			int c2 = Integer.parseInt(str.substring(2, 4), 16);
			return String.valueOf((char) (c1 << 8 | c2));
		} catch (Exception localException) {
		}
		return str;
	}

	public static List delimitedList(String str, String delimiter) {
		List result = new ArrayList();
		if (str == null)
			return result;
		if (delimiter == null) {
			result.add(str);
			return result;
		}
		int pos = 0;
		int delPos = 0;
		while ((delPos = str.indexOf(delimiter, pos)) != -1) {
			result.add(str.substring(pos, delPos));
			pos = delPos + delimiter.length();
		}
		if ((str.length() > 0) && (pos <= str.length()))
			result.add(str.substring(pos));
		return result;
	}

	public static StringBuffer splitArray(String[] array, String delim) {
		StringBuffer strBuf = new StringBuffer(50);
		if ((array == null) || (array.length < 1)) {
			strBuf.append("");
			return strBuf;
		}
		strBuf.append(array[0]);
		for (int i = 1; i < array.length; i++) {
			strBuf.append(delim);
			strBuf.append(array[i]);
		}
		return strBuf;
	}

	public static String map2str(Map m, char delim) {
		return map2str(m, String.valueOf(delim));
	}

	public static String map2str(Map m, String delim) {
		if ((m == null) || (m.size() == 0))
			return "";
		StringBuffer buf = new StringBuffer(50);
		Iterator keys = m.keySet().iterator();
		while (keys.hasNext()) {
			if (buf.length() > 0)
				buf.append(delim);
			Object key = keys.next();
			buf.append(key);
			buf.append('=');
			buf.append(m.get(key));
		}
		return buf.toString();
	}

	public static Map str2map(String queryString, char delim) {
		return str2map(queryString, String.valueOf(delim));
	}

	public static Map str2map(String queryString, String delim) {
		return str2map(queryString, delim, new HashMap());
	}

	public static Map str2map(String queryString, String delim, Map paramMap) {
		if ((queryString == null) || (queryString.length() < 3))
			return null;
		int lastIndex = 0;
		int currentIndex = queryString.indexOf(delim);
		if (currentIndex < 0)
			currentIndex = queryString.length();
		while (currentIndex > 0) {
			String str = queryString.substring(lastIndex, currentIndex);

			int index = str.indexOf('=');
			if (index > 0) {
				String paramName = str.substring(0, index);
				String paramValue = str.substring(index + 1);
				if ((index > 0) && (index < str.length()))
					paramMap.put(paramName, paramValue);
			}

			lastIndex = currentIndex + 1;
			currentIndex = queryString.indexOf(delim, lastIndex);
			if ((lastIndex < queryString.length()) && (currentIndex < 0)
					&& (currentIndex != queryString.length()))
				currentIndex = queryString.length();
		}
		return paramMap;
	}

	public static boolean startsWithNumber(String str) {
		if (nullity(str))
			return false;
		return (str.charAt(0) >= '0') && (str.charAt(0) <= '9');
	}

	public static String capitalize(String src) {
		if ((src == null) || (src.length() == 0))
			return src;
		char[] cs = src.toCharArray();
		cs[0] = Character.toUpperCase(cs[0]);
		return new String(cs);
	}

	public static String capitalize(String src, boolean more) {
		if ((src == null) || (src.length() == 0))
			return src;
		return capitalize(src.toLowerCase());
	}

	public static String left(String src, String substr) {
		int idx = src.indexOf(substr);
		if (idx == -1)
			return "";
		return src.substring(0, idx);
	}

	public static String leftback(String src, String substr) {
		int idx = src.indexOf(substr);
		if (idx == -1)
			return "";
		int len = substr.length();
		while (true) {
			int tmp = src.indexOf(substr, idx + len);
			if (tmp == -1)
				break;
			idx = tmp;
		}
		int tmp;
		return src.substring(0, idx);
	}

	public static String right(String src, String substr) {
		int idx = src.indexOf(substr);
		if (idx == -1)
			return "";
		return src.substring(idx + substr.length());
	}

	public static String rightback(String src, String substr) {
		int idx = src.indexOf(substr);
		if (idx == -1)
			return "";
		int len = substr.length();
		while (true) {
			int tmp = src.indexOf(substr, idx + len);
			if (tmp == -1)
				break;
			idx = tmp;
		}
		int tmp;
		return src.substring(idx + len);
	}

	public static String between(String src, String str1, String str2) {
		String rt = src;
		rt = right(rt, str1);
		rt = left(rt, str2);
		return rt;
	}

	public static String replaceFirst(String src, String from, String to) {
		StringBuffer sb = new StringBuffer(src);
		int idx = src.indexOf(from);
		int len = from.length();
		if (idx != -1)
			sb = sb.replace(idx, idx + len, to);
		return new String(sb);
	}

	public static String replace(String src, String from, String to) {
		return replaceFirst(src, from, to);
	}

	public static String replace(String src, String[] from, String[] to) {
		String rt = src;
		int len = Math.min(from.length, to.length);
		for (int i = 0; i < len; i++)
			rt = replace(rt, from[i], to[i]);
		return rt;
	}

	public static String replaceBetween(String src, String before,
			String after, String to) {
		String from = between(src, before, after);
		return replaceFirst(src, from, to);
	}

	public static String trim(String src) {
		if (nullity(src))
			return "";
		return trim(src, TRIM_CHAR);
	}

	public static String null2emptystr(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public static String null2emptystr(Object obj, String def) {
		return obj == null ? def : obj.toString();
	}

	public static String trim(String src, String chs) {
		return trim(src, chs.toCharArray());
	}

	public static String trim(String src, char[] chArray) {
		int st = 0;
		int len = src.length();
		do {
			st++;

			if (st >= len)
				break;
		} while (containChar(chArray, src.charAt(st)));

		while ((st < len) && (containChar(chArray, src.charAt(len - 1)))) {
			len--;
		}
		return (st > 0) || (len < src.length()) ? src.substring(st, len) : src;
	}

	static boolean containChar(char[] chArray, char ch) {
		for (int i = 0; i < chArray.length; i++)
			if (chArray[i] == ch)
				return true;
		return false;
	}

	public static String[] split(String src, String sep, String quo) {
		if ((src == null) || (src.length() == 0) || (sep == null)
				|| (sep.length() == 0) || (quo == null)
				|| ((!quo.equals("\"")) && (!quo.equals("'"))))
			return new String[0];
		List v = new ArrayList();
		int i0 = 0;
		int i1 = 1;
		int len = sep.length();
		boolean has_quo = false;
		while (true) {
			if ((has_quo = src.startsWith(quo, i0)))
				i1 = src.indexOf(quo + sep, i0 + 1);
			else {
				i1 = src.indexOf(sep, i0);
			}
			if (i1 == -1)
				break;
			if (has_quo) {
				v.add(src.substring(i0, i1 + 1));
				i0 = i1 + (1 + len);
				continue;
			}

			v.add(src.substring(i0, i1));
			i0 = i1 + len;
		}

		v.add(src.substring(i0));

		return (String[]) v.toArray(new String[0]);
	}

	public static String join(String[] array, String sep) {
		if ((array == null) || (array.length == 0))
			return "";
		int len = array.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len - 1; i++)
			sb.append(array[i]).append(sep);
		sb.append(array[(len - 1)]);
		return sb.toString();
	}

	public static String join(String[] array, String sep, String pre,
			String post) {
		if ((array == null) || (array.length == 0))
			return "";
		int len = array.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len - 1; i++)
			sb.append(pre).append(array[i]).append(post).append(sep);
		sb.append(pre).append(array[(len - 1)]).append(post);
		return sb.toString();
	}

	public static String join(int[] array, String sep) {
		if ((array == null) || (array.length == 0))
			return "";
		int len = array.length;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len - 1; i++) {
			String item = new Integer(array[i]).toString();
			sb.append(item).append(sep);
		}
		sb.append(array[(len - 1)]);
		return sb.toString();
	}

	public static String join(boolean[] array, String sep) {
		if ((array == null) || (array.length == 0))
			return "";
		int len = array.length;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len - 1; i++) {
			String item = new Boolean(array[i]).toString();
			sb.append(item).append(sep);
		}
		sb.append(array[(len - 1)]);
		return sb.toString();
	}

	public static String join(String[][] table, String lineSep, String colSep) {
		if ((table == null) || (table.length == 0))
			return "";
		StringBuffer rt = new StringBuffer();
		for (int i = 0; i < table.length; i++) {
			if (i > 0)
				rt.append(lineSep);
			rt.append(join(table[i], colSep));
		}
		return rt.toString();
	}

	public static String[] unique(String[] strArray) {
		List unique = new ArrayList();
		for (int i = 0; i < strArray.length; i++) {
			if (!unique.contains(strArray[i]))
				unique.add(strArray[i]);
		}
		return (String[]) unique.toArray(new String[0]);
	}

	public static String[] set(String[] strArray) {
		return unique(strArray);
	}

	public static boolean isSet(String[] strArray) {
		if (strArray == null)
			return false;
		int n = strArray.length;
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < n; i++) {
			s1 = strArray[i];
			for (int j = 0; j < n; j++) {
				if (i != j)
					s2 = strArray[j];
				if (s1.equals(s2))
					return false;
			}
		}
		return true;
	}

	public static void sort(String[] strArray) {
		Arrays.sort(strArray);
	}

	public static String[][] group(String[] src_list) {
		if ((src_list == null) || (src_list.length == 0))
			return new String[][] { new String[0], new String[0] };
		List value = new ArrayList();
		List num = new ArrayList();
		String cur_str = src_list[0];
		value.add(cur_str);
		int n = 1;

		String tmp = null;
		for (int i = 1; i < src_list.length; i++) {
			tmp = src_list[i];
			if (tmp.equals(cur_str)) {
				n++;
			} else {
				num.add(n);
				value.add(tmp);
				n = 1;
				cur_str = tmp;
			}
		}
		num.add(n);
		String[] rt_v = (String[]) value.toArray(new String[0]);
		String[] rt_n = (String[]) num.toArray(new String[0]);
		return new String[][] { rt_v, rt_n };
	}

	public static boolean include(String str, String[] strArray) {
		return in(str, strArray);
	}

	public static boolean in(String str, String[] strArray) {
		if ((str == null) || (strArray == null))
			return false;
		int i = 0;
		for (int n = strArray.length; i < n; i++)
			if (str.equals(strArray[i]))
				return true;
		return false;
	}

	public static boolean isSubset(String[] ss1, String[] ss2) {
		if ((ss1 == null) || (ss2 == null))
			return false;
		for (int i = 0; i < ss1.length; i++)
			if (!in(ss1[i], ss2))
				return false;
		return true;
	}

	public static String[] remove(String[] source, String[] remove) {
		if ((source == null) || (remove == null))
			return new String[0];
		List rt = new ArrayList();
		for (int i = 0; i < source.length; i++) {
			if (in(source[i], remove))
				continue;
			rt.add(source[i]);
		}
		return (String[]) rt.toArray(new String[rt.size()]);
	}

	public static int indexOf(String str, String[] strArray) {
		if ((str == null) || (strArray == null))
			return -1;
		for (int i = 0; i < strArray.length; i++)
			if (str.equals(strArray[i]))
				return i;
		return -1;
	}

	public static int indexOf(String str, String[] strArray, boolean ignoreCase) {
		if ((str == null) || (strArray == null))
			return -1;

		for (int i = 0; i < strArray.length; i++) {
			boolean equals = ignoreCase ? str.equalsIgnoreCase(strArray[i])
					: str.equals(strArray[i]);
			if (equals)
				return i;
		}
		return -1;
	}

	public static String insert(String str, String substr, String newstr) {
		if ((str == null) || (substr == null) || (newstr == null))
			return "";
		String rt = left(str, substr) + newstr + substr + right(str, substr);
		return rt;
	}

	public static String insertback(String str, String substr, String newstr) {
		if ((str == null) || (substr == null) || (newstr == null))
			return "";
		String rt = leftback(str, substr) + newstr + substr
				+ rightback(str, substr);
		return rt;
	}





	public static String md5(byte[] buf) {
		return digest(buf, "MD5", null);
	}

	public static String digest(String str, String charset, String algorithm) {
		if (nullity(str))
			return "";
		try {
			return digest(str.getBytes(charset), algorithm, null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String digest(byte[] buf, String algorithm) {
		return digest(buf, algorithm, null);
	}

	public static String digest(byte[] buf, String alg, byte[] key) {
		return digest(buf, 0, buf.length, alg, key);
	}

	public static String digest(byte[] buf, int offset, int len, String alg,
			byte[] key) {
		if (buf == null) {
			System.err.println("StringX.digest(byte[]) : null");
			return "";
		}
		try {
			MessageDigest digest = MessageDigest.getInstance(alg);
			digest.update(buf, offset, len);
			return key == null ? bigint2str(digest.digest())
					: bigint2str(digest.digest(key));
		} catch (Exception localException) {
		}
		return "";
	}

	public static String bigint2str(byte[] bigint) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < bigint.length; i++) {
			String str = Integer.toHexString(bigint[i] & 0xFF).toUpperCase();
			buf.append(str.length() < 2 ? '0' + str : str);
		}
		return buf.toString();
	}

	public static String check(String param, String src_default) {
		if ((param == null) || (param.trim().length() == 0))
			return src_default;
		return param;
	}

	public static boolean nullity(String param) {
		return (param == null)
				|| (param.length() <= 0 || param.equals("null") || param
						.equals("(null)"));
	}

	public static boolean isTrue(String param) {
		if (param == null)
			return false;
		return param.equalsIgnoreCase("true");
	}






	public static String[][] map2array(Map map) {
		if (map == null)
			return new String[2][0];
		int n = map.size();
		String[][] rt = new String[2][n];
		rt[0] = ((String[]) map.keySet().toArray(new String[n]));
		rt[1] = ((String[]) map.values().toArray(new String[n]));
		return rt;
	}

	public static String[] list2array(List list) {
		return collection2array(list);
	}

	public static String[] collection2array(Collection c) {
		if (c == null)
			return new String[0];
		return (String[]) c.toArray(new String[c.size()]);
	}

	public static String format(String s, Object[] param) {
		String rt = MessageFormat.format(s, param);
		return rt;
	}

	public static void printf(String s, Object[] param) {
		System.out.print(format(s, param));
	}
}