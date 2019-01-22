package com.neroapp.file.filters;

import java.io.File;
import java.io.FileFilter;

public class PropertyFileFilter implements FileFilter {

	/**
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		
		if (pathname == null) {
			return false;
		}

		boolean isDirectory = pathname.isDirectory();

		boolean isWebInfResource = pathname.getAbsolutePath().contains(
				"classes/");
		
		if (isDirectory || !isWebInfResource) {
			return false;
		}
		
		String extension = getExtension(pathname.getName());
		return extension.equals("properties");
	}

	protected String getExtension(String filename) {
		if (filename == null) {
			return "";
		}

		int lastDotIdx = filename.lastIndexOf(".");

		if (lastDotIdx == -1) {
			return "";
		}

		return filename.substring(lastDotIdx + 1);
	}
}