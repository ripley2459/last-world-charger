package fr.cyrilneveu.lwl.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.cyrilneveu.lwl.LastWorldLoader;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = LastWorldLoader.MOD_ID, value = Dist.CLIENT)
public class LastPlayedButton extends ImageButton {
    private static final ResourceLocation BUTTON = new ResourceLocation(LastWorldLoader.MOD_ID, "textures/gui/last_played_button.png");
    private final Screen parent;
    @Nullable
    private final String worldId;

    public LastPlayedButton(int x, int y, int width, int height, Screen parent) {
        super(x, y, width, height, 0, 0, 20, BUTTON, 20, 40, LastPlayedButton::loadLastWorld, LastPlayedButton::renderTooltips, ITextComponent.nullToEmpty(null));
        this.parent = parent;

        List<WorldSummary> worldSummaryList;
        try {
            worldSummaryList = Minecraft.getInstance().getLevelSource().getLevelList();
        } catch (AnvilConverterException e) {
            throw new RuntimeException(e);
        }

        if (!worldSummaryList.isEmpty()) {
            Collections.sort(worldSummaryList);
            WorldSummary lastWorldSummary = worldSummaryList.get(0);
            this.worldId = lastWorldSummary.getLevelId();
        } else this.worldId = null;
    }

    private static void loadLastWorld(Button button) {
        button.playDownSound(Minecraft.getInstance().getSoundManager());

        if (!(button instanceof LastPlayedButton))
            return;

        String worldId = ((LastPlayedButton) button).getWorldId();

        if (worldId == null)
            return;

        Minecraft.getInstance().loadLevel(worldId);
    }

    private static void renderTooltips(Button button, MatrixStack matrixStack, int mouseX, int mouseY) {
        LastPlayedButton button1 = (LastPlayedButton) button;
        List<? extends ITextProperties> tooltips = Collections.singletonList(ITextProperties.of(button1.getWorldId() == null ? "No world to load!" : "World id = " + button1.getWorldId()));
        GuiUtils.drawHoveringText(matrixStack, tooltips, mouseX, mouseY, button1.getParent().width, button1.getParent().height, -1, Minecraft.getInstance().font);
    }

    @Nullable
    private String getWorldId() {
        return worldId;
    }

    private Screen getParent() {
        return parent;
    }
}
