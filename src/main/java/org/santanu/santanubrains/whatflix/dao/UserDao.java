package org.santanu.santanubrains.whatflix.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.santanu.santanubrains.whatflix.model.UserPreference;
import org.santanu.santanubrains.whatflix.utility.FileConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDao {

	private FileConfig fileConfig;
	private Map<String, UserPreference> mapUserData = null;

	@Inject
	public UserDao(FileConfig fileConfig) {
		super();
		this.fileConfig = fileConfig;
	}

	public Map<String, UserPreference> getAllUsersFromPreferenceFile() {

		mapUserData = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] mapData = null;
		try {
			mapData = Files.readAllBytes(Paths.get(fileConfig.getUserFile()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("User preference file not found.");

		}
		if (mapData != null) {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			List<Map<String, UserPreference>> rootJsonNode = null;
			try {
				rootJsonNode = objectMapper.readValue(mapData,
						new TypeReference<List<HashMap<String, UserPreference>>>() {
						});
			} catch (JsonParseException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} catch (JsonMappingException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}

			for (int i = 0; i < rootJsonNode.size(); i++) {
				Map<String, UserPreference> myMap = rootJsonNode.get(i);
				for (Entry<String, UserPreference> entrySet : myMap.entrySet()) {
					mapUserData.put(entrySet.getKey(), entrySet.getValue());
				}
			}
		}
		return mapUserData;

	}

	public UserPreference getSingleUserFromPreferenceFile(String userId) {

		UserPreference userPreference = null;
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		String[] lanJsonNode = null;
		String[] favActorJsonNode = null;
		String[] favDirectorJsonNode = null;
		try {
			rootNode = objectMapper.readTree((new File(fileConfig.getUserFile())));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("User preference file not found.");

		}
		JsonNode userIdNode = rootNode.findPath(userId);
		if (!userIdNode.isMissingNode()) {
			userPreference = new UserPreference();
			JsonNode preLangArr = userIdNode.path("preferred_languages");
			JsonNode favActArr = userIdNode.path("favourite_actors");
			JsonNode favDirectArr = userIdNode.path("favourite_directors");
			try {
				lanJsonNode = objectMapper.treeToValue(preLangArr, String[].class);
				for (String n : lanJsonNode) {
					userPreference.getPreferred_languages().add(n);
				}
				favActorJsonNode = objectMapper.treeToValue(favActArr, String[].class);
				for (String n : favActorJsonNode) {
					userPreference.getFavourite_actors().add(n);
				}
				favDirectorJsonNode = objectMapper.treeToValue(favDirectArr, String[].class);
				for (String n : favDirectorJsonNode) {
					userPreference.getFavourite_directors().add(n);
				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}

		}
		return userPreference;

	}

}
