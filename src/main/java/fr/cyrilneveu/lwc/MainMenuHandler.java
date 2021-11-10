package fr.cyrilneveu.lwc;

import fr.cyrilneveu.lwc.gui.LastPlayedButton;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.storage.WorldSummary;

import java.util.Collections;
import java.util.List;

public class MainMenuHandler {
    public static final int LAST_PLAYED_BUTTON_ID = 45;

    /**
     * Add the button to load the last played world in the button list of the opened gui.
     * @param gui The opened gui, the main menu actually.
     * @param buttonList The main menu's button list.
     */
    public static void initGui(GuiScreen gui, List<GuiButton> buttonList) {
        buttonList.add(new LastPlayedButton(LAST_PLAYED_BUTTON_ID, gui.width / 2 - 124, gui.height / 4 + 48));
    }

    /**
     * Load the last played world when the button is pressed.
     * @param gui The gui where the action is performed.
     */
    public static void onActionPerformed(GuiButton gui)
    {
        if (gui.id == LAST_PLAYED_BUTTON_ID)
        {
            try {
                Minecraft minecraft = Minecraft.getMinecraft();
                List<WorldSummary> worldSummaryList = minecraft.getSaveLoader().getSaveList();
                if (worldSummaryList.size() > 0) {
                    Collections.sort(worldSummaryList);
                    WorldSummary lastWorldSummary = worldSummaryList.get(0);
                    minecraft.launchIntegratedServer(lastWorldSummary.getFileName(), lastWorldSummary.getDisplayName(), null);
                }
            } catch (AnvilConverterException e) {
                e.printStackTrace();
            }
        }
    }
}
