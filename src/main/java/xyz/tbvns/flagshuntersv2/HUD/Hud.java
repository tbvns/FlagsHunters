package xyz.tbvns.flagshuntersv2.HUD;

//import com.mojang.blaze3d.systems.RenderSystem;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.GuiComponent;
//import net.minecraft.client.renderer.GameRenderer;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.client.gui.overlay.IGuiOverlay;
//import xyz.tbvns.flaghunter.FlagsHunters;
//
//public class Hud {
//    public static final ResourceLocation Test = new ResourceLocation(FlagsHunters.MODID, "textures/hud/test.png");
//    public static final IGuiOverlay RedHUD = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
//        int x = 0;
//        int y = 0;
//
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0f, 0.0f, 0.0f, HUDManager.OpacityR);
//        RenderSystem.setShaderTexture(0, Test);
//
//        GuiComponent.blit(poseStack, x * screenWidth, y * screenHeight, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
//
//        HUDManager.OpacityR = (HUDManager.OpacityR - Minecraft.getInstance().getFrameTime()/100);
//    });
//}
//