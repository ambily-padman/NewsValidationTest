package com.news.utils.data;


public enum PlatformType {
    UNKNOWN,
    WINDOWS,
    MAC,
    LINUX;


    /**
     * Gets the operating system from the system property
     * @return  The platform to best match from the detected Operating System
     */
    public static PlatformType fromDetectedOperatingSystem() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.startsWith("windows"))
            return WINDOWS;
        else if (osName.startsWith("linux"))
            return LINUX;
        else if (osName.startsWith("mac"))
            return MAC;
        else
            return UNKNOWN;
    }

}
