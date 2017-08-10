package net.termer.flpluginsupport;

import java.io.File;
import java.util.Collection;

import net.termer.flps.FLPlugin;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;

public class Plugins {
	private static PluginManager pm = PluginManagerFactory.createPluginManager();
	private static Collection<FLPlugin> plugins = null;
	
	public static void load() {
		pm.addPluginsFrom(new File("plugins").toURI());
		plugins = new PluginManagerUtil(pm).getPlugins(FLPlugin.class);
	}
	
	public static void preInit() {
		for(FLPlugin pl : plugins) {
			try {
				pl.preInit();
			} catch(AbstractMethodError e) {
				System.out.println("Plugin \""+pl.getName()+"\" is missing the preInit element and appears to be old.");
				System.out.println("PreInit will be skipped for this plugin.");
			} catch(Exception ex) {
				System.out.println("Plugin \""+pl.getName()+"\" is throwing an exception in pre init.");
				System.out.println("Plugin will not be enabled.");
				plugins.remove(pl);
			}
		}
	}
	
	public static void enable() {
		for(FLPlugin pl : plugins) {
			pl.start();
		}
	}
	
	public static void disable() {
		for(FLPlugin pl : plugins) {
			pl.end();
		}
	}
	
	public static void init() throws Exception {
		System.out.println("Starting...");
		File pf = new File("plugins");
		if(!pf.exists()) {
			pf.mkdir();
		}
	}
}
