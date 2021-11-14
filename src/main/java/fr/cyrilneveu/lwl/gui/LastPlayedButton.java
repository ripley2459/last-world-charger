package fr.cyrilneveu.lwl.gui;

import fr.cyrilneveu.lwl.LastWorldLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class LastPlayedButton extends GuiButton {
    private static final ResourceLocation LAST_PLAYED_BUTTON = new ResourceLocation(LastWorldLoader.MOD_ID, "textures/gui/last_played_button.png");
    private static final ResourceLocation LAST_PLAYED_BUTTON_HOVERED = new ResourceLocation(LastWorldLoader.MOD_ID, "textures/gui/last_played_button_hovered.png");
    public static final int BUTTON_SIZE = 20;
    public static final int IMAGE_SIZE = 20;

    public LastPlayedButton(int id, int xPos, int yPos) {
        super(id, xPos, yPos, BUTTON_SIZE, BUTTON_SIZE, "");
    }

    public void drawButton(Minecraft minecraft, int mouseX, int mouseY, float partialTicks)
    {
        if(this.visible)
        {
            boolean isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (isHovered) minecraft.getTextureManager().bindTexture(LAST_PLAYED_BUTTON_HOVERED);
            else minecraft.getTextureManager().bindTexture(LAST_PLAYED_BUTTON);
            Gui.drawScaledCustomSizeModalRect(this.x, this.y, 0, 0, IMAGE_SIZE, IMAGE_SIZE, BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
        }
    }
}
