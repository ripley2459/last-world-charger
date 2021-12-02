package fr.cyrilneveu.lwl;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(LastWorldLoader.MOD_ID)
public class LastWorldLoader
{
    public static final String MOD_ID = "lwl";
    public static final String MINECRAFT_VERSION = "1.16.5";

    public LastWorldLoader() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
