package crazypants.enderio.machine.obelisk.aversion;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import com.enderio.core.client.gui.button.ToggleButton;
import com.enderio.core.client.gui.widget.GuiToolTip;
import com.enderio.core.client.render.ColorUtil;
import com.google.common.collect.Lists;

import crazypants.enderio.EnderIO;
import crazypants.enderio.gui.IconEIO;
import crazypants.enderio.machine.gui.GuiPoweredMachineBase;

public class GuiAversionObelisk extends GuiPoweredMachineBase<TileAversionObelisk> {

  ToggleButton showRangeB;

  private static final int RANGE_ID = 8738924;

  public GuiAversionObelisk(InventoryPlayer par1InventoryPlayer, TileAversionObelisk te) {
    super(te, new ContainerAversionObelisk(par1InventoryPlayer, te), "attractor");

    int x = getXSize() - 5 - BUTTON_SIZE;
    showRangeB = new ToggleButton(this, RANGE_ID, x, 44, IconEIO.PLUS, IconEIO.MINUS);
    showRangeB.setSize(BUTTON_SIZE, BUTTON_SIZE);
    addToolTip(new GuiToolTip(showRangeB.getBounds(), "null") {
      @Override
      public List<String> getToolTipText() {
        return Lists.newArrayList(EnderIO.lang.localize(showRangeB.isSelected() ? "gui.spawnGurad.hideRange" : "gui.spawnGurad.showRange"));
      }
    });
  }

  @Override
  public void initGui() {
    super.initGui();
    showRangeB.onGuiInit();
    showRangeB.setSelected(getTileEntity().isShowingRange());
    ((ContainerAversionObelisk) inventorySlots).createGhostSlots(getGhostSlots());
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    bindGuiTexture();
    int sx = (width - xSize) / 2;
    int sy = (height - ySize) / 2;

    drawTexturedModalRect(sx, sy, 0, 0, xSize, ySize);

    super.drawGuiContainerBackgroundLayer(par1, par2, par3);

    int range = (int) getTileEntity().getRange();
    drawCenteredString(fontRendererObj, EnderIO.lang.localize("gui.spawnGurad.range") + " " + range, getGuiLeft() + sx / 2 + 9, getGuiTop() + 68,
        ColorUtil.getRGB(Color.white));
  }

  @Override
  protected void actionPerformed(GuiButton b) throws IOException {
    super.actionPerformed(b);
    if (b.id == RANGE_ID) {
      getTileEntity().setShowRange(showRangeB.isSelected());
    }
  }

  @Override
  protected boolean showRecipeButton() {
    return false;
  }

}
