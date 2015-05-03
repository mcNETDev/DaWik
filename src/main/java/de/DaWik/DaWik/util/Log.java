package de.DaWik.DaWik.util;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class Log {
	public static void log(Level logLevel, Object object) {
		FMLLog.log("DaWik", logLevel, String.valueOf(object));
	}

	public static void all(Object object) {
		Log.log(Level.ALL, object);
	}

	public static void debug(Object object) {
		Log.log(Level.DEBUG, object);
	}

	public static void error(Object object) {
		Log.log(Level.ERROR, object);
	}

	public static void fatal(Object object) {
		Log.log(Level.FATAL, object);
	}

	public static void info(Object object) {
		Log.log(Level.INFO, object);
	}

	public static void off(Object object) {
		Log.log(Level.OFF, object);
	}

	public static void trace(Object object) {
		Log.log(Level.TRACE, object);
	}

	public static void warn(Object object) {
		Log.log(Level.WARN, object);
	}
}