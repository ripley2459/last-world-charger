package fr.cyrilneveu.lwc;

import java.io.File;

public class ProxyCommon {
    public void preInit(File configFile)
    {
        System.out.println("Common preInit.");
    }

    public void init()
    {
        System.out.println("Common init.");
    }
}
