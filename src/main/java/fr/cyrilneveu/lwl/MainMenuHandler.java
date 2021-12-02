package fr.cyrilneveu.lwl;

import fr.cyrilneveu.lwl.gui.LastPlayedButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = LastWorldLoader.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MainMenuHandler {
    @SubscribeEvent
    public static void onGuiInit(ScreenEvent.InitScreenEvent event) {
        Screen screen = event.getScreen();

        if(screen instanceof TitleScreen) {
            Button lastPlayedButton = new LastPlayedButton(screen.width / 2 - (100 + 20 + 4), screen.height / 4 + 48, 20, 20);
            screen.renderables.add(lastPlayedButton);
            event.addListener(lastPlayedButton);
        }
    }
}
