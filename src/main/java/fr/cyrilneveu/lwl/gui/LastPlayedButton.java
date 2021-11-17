package fr.cyrilneveu.lwl.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.cyrilneveu.lwl.LastWorldLoader;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = LastWorldLoader.MOD_ID, value = Dist.CLIENT)
public class LastPlayedButton extends ImageButton {
    private static final ResourceLocation BUTTON = new ResourceLocation(LastWorldLoader.MOD_ID,"textures/gui/last_played_button.png");
    private final int yTexStart;
    private final int yDiffText;
    private final int xTexStart;
    private final int textureWidth;
    private final int textureHeight;

    public LastPlayedButton(int x, int y, int width, int height) {
        super(x, y, width, height, 0, 0, 0, BUTTON, 20, 40, LastPlayedButton::LoadLastWorld, ITextComponent.getTextComponentOrEmpty(""));
        this.xTexStart = 0;
        this.yTexStart = 0;
        this.yDiffText = 20;
        this.textureWidth = 20;
        this.textureHeight = 40;
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft.getInstance().getTextureManager().bindTexture(this.BUTTON);
        int i = this.yTexStart;
        if (this.isHovered()) {
            i += this.yDiffText;
        }

        RenderSystem.enableDepthTest();
        blit(matrixStack, this.x, this.y, (float)this.xTexStart, (float)i, this.width, this.height, this.textureWidth, this.textureHeight);
        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }

    private static void LoadLastWorld(Button button) {
        try {
            Minecraft minecraft = Minecraft.getInstance();
            List<WorldSummary> worldSummaryList = minecraft.getSaveLoader().getSaveList();
            if (worldSummaryList.size() > 0) {
                Collections.sort(worldSummaryList);
                WorldSummary lastWorldSummary = worldSummaryList.get(0);
                // minecraft.launchIntegratedServer(lastWorldSummary.getFileName(), lastWorldSummary.getDisplayName(), null);
                minecraft.loadWorld(lastWorldSummary.getFileName());
            }
        } catch (AnvilConverterException e) {
            e.printStackTrace();
        }
    }
}
