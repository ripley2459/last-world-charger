package fr.cyrilneveu.lwl;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent event)
    {
        if(event.getGui() instanceof GuiMainMenu)
        {
            MainMenuHandler.initGui(event.getGui(), event.getButtonList());
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent event)
    {
        if (event.getGui() instanceof GuiMainMenu)
        {
            MainMenuHandler.onActionPerformed(event.getButton());
        }
    }
}
