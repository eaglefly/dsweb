package org.dsweb.config;

import java.util.ArrayList;
import java.util.List;

import org.dsweb.plug.Plugin;

/**
 * Plugins.
 */
public class PluginLoader {

	private List<Plugin> plugins = new ArrayList<Plugin>();

	public PluginLoader add(Plugin plugin) {
		if (plugin != null)
			this.plugins.add(plugin);
		return this;
	}

	public List<Plugin> getPlugins() {
		return plugins;
	}
}
