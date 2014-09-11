package com.neroapp.services.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.Hashtag;

public class HashtagsResource extends ResourceList<HashtagResource, Hashtag> {

	public static final String TEMPLATE_URI = "/places/{id}/recommendedhashtags;qualificationType={qualificationType}{?maxResults}";

	public HashtagsResource(List<Hashtag> values, Map<String, Object> parameters) {
		super(values);
		String uri = resolveTemplate(parameters);
		add(new Link(uri));
	}

	public List<HashtagResource> getHashtags() {
		return this.resourceList();
	}

	public static String resolveTemplate(Map<?, ?> parameters) {
		String template = TEMPLATE_URI;
		if (parameters != null) {
			if (parameters.containsKey("id")) {
				template = template.replace("{id}",
						String.valueOf(parameters.get("id")));
			}
			if (parameters.containsKey("qualificationType")) {
				template = template.replace("{qualificationType}",
						String.valueOf(parameters.get("qualificationType")));
			}
			if (parameters.containsKey("maxResults")) {
				template = template.replace(
						"{?maxResults}",
						String.format("?maxResults=%s",
								parameters.get("maxResults")));
			}
		}
		return UriBuilder.fromUri(template).toTemplate();
	}
}
