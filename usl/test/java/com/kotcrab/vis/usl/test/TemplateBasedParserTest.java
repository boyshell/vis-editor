package com.kotcrab.vis.usl.test;

import com.kotcrab.vis.usl.USL;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class TemplateBasedParserTest {
	@Test
	public void testParserUsingVisUITemplate () throws Exception {
		testUsingTemplate("/test-visui.usl", "/test-visui-expected.json");
	}

	@Test
	public void testParserUsingGdxTemplate () throws Exception {
		testUsingTemplate("/test-gdx.usl", "/test-gdx-expected.json");
	}

	@Test
	public void testParserUsingTintedTemplate () throws Exception {
		testUsingTemplate("/test-tinted.usl", "/test-tinted-expected.json");
	}

	@Test
	public void testParserAlias () throws Exception {
		testUsingTemplate("/test-alias.usl", "/test-alias-expected.json");
	}

	@Test
	public void testParserComments () throws Exception {
		testUsingTemplate("/test-comments.usl", "/test-comments-expected.json");
	}

	private void testUsingTemplate (String uslPath, String jsonPath) throws Exception {
		String expected = readFile(jsonPath);
		String result = USL.parse(null, readFile(uslPath));
		compareResult(expected, result);
	}

	private void compareResult (String expectedJson, String resultJson) {
		String[] expected = expectedJson.replace("\r\n", "\n").split("\n");
		String[] result = resultJson.replace("\r\n", "\n").split("\n");

		for (int i = 0; i < expected.length; i++) {
			if (i >= result.length) {
				throw new IllegalStateException("Unexpected end of result string at line " + (i + 1));
			}

			Assert.assertEquals("Json mismatch at line " + (i + 1), expected[i], result[i]);
		}
	}

	private String readFile (String path) throws IOException {
		return new Scanner(TemplateBasedParserTest.class.getResourceAsStream(path), "UTF-8").useDelimiter("\\A").next();
	}
}
