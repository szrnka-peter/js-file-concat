package hu.szrnkapeter.cssjsminifier.compressor.js;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Assert;
import org.junit.Test;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;

public class GoogleClosureCompilerJSCompressorTest {

	@Test
	public void test_normal_advanced_empty() throws Exception {
		final GoogleClosureCompilerJSCompressor compressor = new GoogleClosureCompilerJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("var doit = function(str) {\r\nalert (str);\r\n};");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath(), JSCompileType.ADVANCED);

		tempFile.deleteOnExit();

		Assert.assertEquals("Wrong result!", "", result);
	}

	@Test
	public void test_normal_advanced_withcall() throws Exception {
		final GoogleClosureCompilerJSCompressor compressor = new GoogleClosureCompilerJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("var doit = function(str) {\r\nalert (str);\r\n};doit();");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath(), JSCompileType.ADVANCED);

		tempFile.deleteOnExit();

		Assert.assertEquals("Wrong result!", "alert(void 0);", result);
	}

	@Test
	public void test_normal_simple() throws Exception {
		final GoogleClosureCompilerJSCompressor compressor = new GoogleClosureCompilerJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("var doit = function(str) {\r\nalert (str);\r\n};");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath(), JSCompileType.SIMPLE);

		tempFile.deleteOnExit();

		Assert.assertEquals("Wrong result!", "var doit=function(a){alert(a)};", result);
	}

	@Test
	public void test_normal_whitespace() throws Exception {
		final GoogleClosureCompilerJSCompressor compressor = new GoogleClosureCompilerJSCompressor();
		final File tempFile = File.createTempFile("prefix", "suffix");
		final BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		final StringBuilder sb = new StringBuilder();
		sb.append("var doit = function(str) {\r\nalert (str);\r\n};");
		bw.write(sb.toString());
		bw.close();
		final String result = compressor.compress(tempFile.getAbsolutePath(), JSCompileType.WHITESPACE);

		tempFile.deleteOnExit();

		Assert.assertEquals("Wrong result!", "var doit=function(str){alert(str)};", result);
	}
}
