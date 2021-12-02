package fr.cyrilneveu.lwl.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.LevelStorageException;
import net.minecraft.world.level.storage.LevelSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = LastWorldLoader.MOD_ID, value = Dist.CLIENT)
public class LastPlayedButton extends ImageButton {
    private static final ResourceLocation BUTTON = new ResourceLocation(LasWorldLoader.MOD_ID,"textures/gui/last_played_button.png");
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
    public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
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
            List<LevelSummary> worlds = minecraft.getLevelSource().getLevelList();
            if (worlds.size() > 0) {
                Collections.sort(worlds);
                LevelSummary lastWorld = worlds.get(0);
                minecraft.loadLevel(lastWorld.getLevelName());
            }
        } catch (LevelStorageException e) {
            e.printStackTrace();
        }
    }
}
