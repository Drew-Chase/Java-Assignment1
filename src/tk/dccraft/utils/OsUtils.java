package tk.dccraft.utils;

public final class OsUtils {
	private static String OS = null;
	private static String DesktopEnvironment = null;

	public static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}

	public static String getDesktopEnvironment() {
		if (DesktopEnvironment == null)
			DesktopEnvironment = System.getenv("XDG_CURRENT_DESKTOP");
		return DesktopEnvironment;
	}

	public static boolean isWindows() {
		return getOsName().toLowerCase().startsWith("windows");
	}

	public static boolean isLinux() {
		return getOsName().toLowerCase().startsWith("linux");
	}
	
	public static boolean isMac() {
		return getOsName().toLowerCase().startsWith("mac");
	}

	public static boolean isKDE() {
		return getDesktopEnvironment().toLowerCase().contains("kde");
	}
	
	public static boolean isGNOME() {
		return getDesktopEnvironment().toLowerCase().contains("gnome");
	}
}
