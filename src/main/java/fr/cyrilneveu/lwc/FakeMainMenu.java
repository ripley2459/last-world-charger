package fr.cyrilneveu.lwc;

import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
public class FakeMainMenu extends GuiMainMenu {
    public static final int LAST_PLAYED_BUTTON_ID = 45;

    public FakeMainMenu() {
        super();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new LastPlayedButton(LAST_PLAYED_BUTTON_ID, this.width / 2 - 124, this.height / 4 + 48));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button.id == LAST_PLAYED_BUTTON_ID)
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
