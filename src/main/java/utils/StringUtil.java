/********************************************************************
 ***                                                              ***
 ***           COPYRIGHT  (C)  Bule Vessel CORPORATION  2015      ***
 ***                                                              ***
 ***   ALL RIGHTS RESERVED BY Bule Vessel CORPORATION.  THIS      ***
 ***   MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH IT WAS       ***
 ***   FURNISHED BY NEC CORPORATION.  NO PART OF THIS PROGRAM     ***
 ***   MAY BE REPRODUCED OR DISCLOSED TO OTHERS.  IN ANY FORM,    ***
 ***   WITHOUT THE PRIOR WRITTEN PERMISSION OF NEC CORPORATION.   ***
 ***   USE OF COPYRIGHT NOTICE DOES NOT EVIDENCE PUBLICATION      ***
 ***   OF THIS PROGRAM.                                           ***
 ***                                                              ***
 ***     Bule Vessel CONFIDENTIAL AND PROPRIETARY                 ***
 ***                                                              ***
 ********************************************************************/
package utils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {

	public static InputStream string2InputStream(String content) {
		
		InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(content.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			inputStream = null;
			e1.printStackTrace();
		} 
		return inputStream;
	}
	
	/**
	 * transfer the null to empty string.
	 * <pre>
	 * 	IF(<code>str</code> is null){
	 * 		return null;
	 * 	}ELSE{
	 * 		return "";
	 * 	}
	 * </pre>
	 * @param str	
	 * 		the String.
	 * @return	string.
	 */
	public static String null2EmptyStr(final String str) {
		
		if (str == null) {
			return "";
		}
		return str;
		
	}
	
	public static String removeUnknowString(String content) {
		
		String newContent = content;
		if (content != null) {
			newContent = newContent.replaceAll("\\?", "");
		}
		return newContent;
	}
	
	public static String removeAllSpace(String content) {
		
		String newContent = content;
		if (content != null) {
			newContent = newContent.replace("\n", "").replace("\t", "");
		}
		return newContent;
	}
	
	public static String trim(String content) {
		return null2EmptyStr(content).replaceAll("　","").replaceAll(" ","").replaceAll("\u3000", "").trim();
	}
	
	public static String upperCaseFirstChar(String content) {
		
		if (content == null || "".equals(content)) {
			return content;
		}
		
		return content.substring(0,1).toUpperCase() + content.substring(1);
	}
	
	public static boolean isEmpty(String content) {
		boolean r = false;
		if (content == null || "".equals(content.trim()) || "null".equals(content.trim())) {
			r = true;
		}
		return r;
	}
	
	public static String getIP(String ipAndPort) {
		String ip = "";
		String pattern = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(ipAndPort);
		if (m.find()) {
			ip = m.group();
		}
		
		return ip;
	}
	
	public static String gbEncoding(final String gbString) {
	    StringBuffer unicodeBytes = new StringBuffer();
	    if (gbString == null) {
	    	return unicodeBytes.toString();
	    }
	    for (int byteIndex = 0; byteIndex < gbString.length(); byteIndex++) {
	        UnicodeBlock ub = UnicodeBlock.of(gbString.charAt(byteIndex));
	    	if (ub == UnicodeBlock.BASIC_LATIN) {
	    		unicodeBytes.append(gbString.charAt(byteIndex));
	    	} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	    		int i = (int) gbString.charAt(byteIndex)  - 65248;
	    		unicodeBytes.append((char)i);
	    	} else {
	    		String hexB = Integer.toHexString(gbString.charAt(byteIndex));
	 	        unicodeBytes.append("\\u").append(hexB); 
	    	}
	        
	    }
	        
	    return unicodeBytes.toString();
	}
	
	public static String UnicodeToGBK(String input) {
		if (isEmpty(input)) {
			return "";
		} else {
			Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
	        Matcher matcher = pattern.matcher(input);
	        char ch;
	        while (matcher.find()) {
	            ch = (char) Integer.parseInt(matcher.group(2), 16);
	            input = input.replace(matcher.group(1), ch + "");    
	        }
	        return input;
		}
	}
	
	public static boolean cmpUrl (String url1, String url2) {
		
		boolean r = false;
		
		if (!url1.equalsIgnoreCase(url2)) {
			
			url1 = buildUrl(url1);
			url2 = buildUrl(url2);
			
			if (url1.equalsIgnoreCase(url2)) {
				r = true;
			} else {
				r = false;
			}
			
		} else {
			r = true;
		}
		
		return r;
	}
	
	public static String buildUrl(String url) {
		
		if (!url.startsWith("http://")) {
			url = "http://" + url;
		}
		
		if (!url.endsWith("/")) {
			url += "/";
		}
		
		return url;
	}
	
	public static boolean isLong(String str) {
		
		boolean r = false;
		
		try {
			Long.valueOf(str);
			r = true;
		} catch (NumberFormatException e) {
			r = false;
		}
		
		return r;
		
	}
	
	
}
