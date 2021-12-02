package fr.cyrilneveu.lwl;

import fr.cyrilneveu.lwl.gui.LastPlayedButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LastWorldLoader.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MainMenuHandler {
    @SubscribeEvent
    public static void onGuiInit(GuiScreenEvent.InitGuiEvent event) {
        Screen gui = event.getGui();
        if(gui instanceof MainMenuScreen) {
            event.addWidget(new LastPlayedButton(gui.width / 2 - (100 + 20 + 4), gui.height / 4 + 48, 20, 20));
        }
    }
}
