package io.baato.osmosis.bumpids;

import java.util.HashMap;
import java.util.Map;

import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
import org.openstreetmap.osmosis.core.plugin.PluginLoader;

public class BumpIdPluginLoader implements PluginLoader {
	public Map<String, TaskManagerFactory> loadTaskFactories() {
		// the map describes how to load the plugin
		// with the following statement, you can load the task
		// created by MyFactory using:
		// osmosis --read-pbf latest.osm.pbf --my-plugin
		// you can also create multiple factories that create
		// different tasks if needed
		HashMap<String, TaskManagerFactory> map = new HashMap<String, TaskManagerFactory>();
		map.put("bump-ids", new BumpIdTaskFactory());
		return map;
	}
}
