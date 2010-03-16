package com.cadrlife.jaml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cadrlife.jaml.filters.CdataFilter;
import com.cadrlife.jaml.filters.CssFilter;
import com.cadrlife.jaml.filters.EscapedFilter;
import com.cadrlife.jaml.filters.Filter;
import com.cadrlife.jaml.filters.JavaScriptFilter;
import com.cadrlife.jaml.filters.PlainFilter;
import com.cadrlife.jaml.filters.PreserveFilter;

public class JamlConfig {
	String format = "xhtml";
	List<String> autoclose = new ArrayList<String>(Arrays.asList("meta", "img", "link", "br", "hr", "input", "area", "param", "col", "base"));
	List<String> preserve = new ArrayList<String>(Arrays.asList("textarea", "pre"));
	public String attrWrapper = "'";
	
	public Map<String,Filter> filters = new HashMap<String,Filter>();
	{
		filters.put("plain", new PlainFilter(this));
		filters.put("javascript", new JavaScriptFilter(this));
		filters.put("cdata", new CdataFilter(this));
		filters.put("escaped", new EscapedFilter(this));
		filters.put("preserve", new PreserveFilter(this));
		filters.put("jsp", new PlainFilter(this));
		filters.put("css", new CssFilter(this));
	}
	
	// These options escapeHtml, suppressEval, and encoding may not be necessary.
//	boolean escapeHtml = false;
//	boolean suppressEval = false;
//	String encoding = "utf-8";
	
	public boolean isXhtml() {
		return !isHtml();
	}
	public boolean isHtml() {
		return isHtml4() || isHtml5();
	}
	public boolean isHtml4() {
		return "html4".equals(this.format);
	}
	public boolean isHtml5() {
		return "html5".equals(this.format);
	}
}
