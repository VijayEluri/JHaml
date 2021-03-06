package com.cadrlife.jhaml;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class FiltersTest {
	private JHaml jhaml = new JHaml();
	
	@Test
	public void javascript() {
		String haml = 
			":javascript\n" +
			"  \"use strict\";\n" +
			"  jQuery(function() {});"; 
		String html =
			"<script type='text/javascript'>\n" + 
			"  //<![CDATA[\n" + 
			"    \"use strict\";\n" +
			"    jQuery(function() {});\n" + 
			"  //]]>\n" + 
			"</script>";
		assertEquals(html, jhaml.parse(haml));
	}
	@Test
	public void cdata() {
		String haml = 
			"%p\n" + 
			"  :cdata\n" +
			"    Hello\n" +
			"    %p"; 
		String html =
			"<p>\n" + 
			"  <![CDATA[\n" + 
			"    Hello\n" +
			"    %p\n" + 
			"  ]]>\n" +
			"</p>"; 
		assertEquals(html, jhaml.parse(haml));
	}
	@Test
	public void emptyCdata() {
		String haml = 
			":cdata\n" +
			"Hello";
		String html =
			"<![CDATA[\n" + 
			"]]>\n" + 
			"Hello";
		assertEquals(html, jhaml.parse(haml));
	}
	@Test
	public void plain() {
		String haml = 
			"%p\n" + 
			"  :plain\n" +
			"    %div\n" +
			"    Hello";
		String html =
			"<p>\n" + 
			"  %div\n" + 
			"  Hello\n" +
			"</p>"; 
		assertEquals(html, jhaml.parse(haml));
	}
	@Test
	public void escaped() {
		String haml = 
			":escaped\n" +
			"  <div />";
		String html = "&lt;div /&gt;"; 
		assertEquals(html, jhaml.parse(haml));
	}
	@Test
	public void preserve() {
		String haml = 
			":preserve\n" +
			"  a\n   b\n\n    c";
		String html = "a&#x000A; b&#x000A;&#x000A;  c"; 
		assertEquals(html, jhaml.parse(haml));
	}
	
	@Test
	public void css() {
		String haml= 
			":css\n" +
			"  body {}";
		String html = 
			"<style type='text/css'>\n" +
			"<!--\n" +
			"  body {}\n" +
			"-->\n" +
			"</style>"; 
		assertEquals(html, jhaml.parse(haml));
	}
	
	@Test
	public void jsp() {
		String haml=
			":jsp\n" +
			"  %dontParseMe\n" +
			"  <%= foo(); %>";
		String html = 
			"%dontParseMe\n" +
			"<%= foo(); %>";
		assertEquals(html, jhaml.parse(haml));
	}
	
	@Test
	public void markdown() {
		String haml=
			":markdown\n" +
			"  *Bold*";
		String html = "<p><em>Bold</em></p>";
		assertEquals(html, jhaml.parse(haml));
	}
	
	@Test
	public void markdownWithinDiv() {
		String haml=
			"%div\n" +
			"  :markdown\n" +
			"    *Bold*";
		String html = "<div>\n  <p><em>Bold</em></p>\n</div>";
		assertEquals(html, jhaml.parse(haml));
	}
}
