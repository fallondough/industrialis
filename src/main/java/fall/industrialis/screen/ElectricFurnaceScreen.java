package fall.industrialis.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import fall.industrialis.Industrialis;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ElectricFurnaceScreen extends HandledScreen<ElectricCompressorScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Industrialis.MOD_ID, "textures/gui/electric_compressor_gui.png");
    public ElectricFurnaceScreen(ElectricCompressorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(matrices, x, y);
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        int progress = handler.getScaledProgress();
        drawTexture(matrices, x + 79, y + 34, 176, 14, progress + 1, 16);

        if(handler.isCrafting()){
            progress = this.handler.getScaledProgress();
            drawTexture(matrices, x + 56, y + 36 + 12 - progress, 176, 12 - progress, 14, progress + 1);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
