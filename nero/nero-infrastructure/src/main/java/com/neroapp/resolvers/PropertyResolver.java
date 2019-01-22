package com.neroapp.resolvers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.neroapp.file.filters.PropertyFileFilter;

@ApplicationScoped
public class PropertyResolver {

	private Properties properties = new Properties();

	private File getFileFromURL(URL url) {
		File result;

		try {
			result = new File(url.toURI());
		} catch (URISyntaxException e) {
			result = new File(url.getPath());
		}

		return result;
	}

	private List<File> getPropertyFiles(ClassLoader cl) throws IOException {
		List<File> result = new ArrayList<>();

		Enumeration<URL> resources = cl.getResources("");

		while (resources.hasMoreElements()) {
			File resource = getFileFromURL(resources.nextElement());

			File[] files = resource.listFiles(new PropertyFileFilter());
			result.addAll(Arrays.asList(files));
		}

		return result;
	}

	public String getValue(String key, String defaultValue) {
		Object value = this.properties.getProperty(key, defaultValue);
		return (value != null) ? String.valueOf(value) : null;
	}

	@PostConstruct
	private void init() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		try {
			List<File> propertyFiles = getPropertyFiles(cl);

			for (File file : propertyFiles) {
				Properties p = new Properties();
				p.load(new FileInputStream(file));
				this.properties.putAll(p);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
