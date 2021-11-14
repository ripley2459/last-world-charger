package fr.cyrilneveu.lwl;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = LastWorldLoader.MOD_ID, name = LastWorldLoader.NAME, version = LastWorldLoader.VERSION)
public class LastWorldLoader {
    public static final String MOD_ID = "lwl";
    public static final String NAME = "Last World Loader";
    public static final String VERSION = "1";
    @SidedProxy(clientSide = "fr.cyrilneveu.lwl.ProxyClient")
    public static ProxyClient proxy;
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
}