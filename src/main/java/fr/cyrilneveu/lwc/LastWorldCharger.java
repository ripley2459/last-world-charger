package fr.cyrilneveu.lwc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = LastWorldCharger.MOD_ID, name = LastWorldCharger.NAME, version = LastWorldCharger.VERSION)
public class LastWorldCharger {
    public static final String MOD_ID = "lwc";
    public static final String NAME = "Last World Charger";
    public static final String VERSION = "1";
    @SidedProxy(clientSide = "fr.cyrilneveu.lwc.ProxyClient")
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