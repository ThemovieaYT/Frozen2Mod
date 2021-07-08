package net.themoviea.frozeniimod.client.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class Frozen2Screen<T extends ScreenHandler> extends HandledScreen<T> {
	protected Frozen2Screen(T handler, PlayerInventory inv, Text title) {
		super(handler, inv, title);
	}
	
	public <T extends AbstractButtonWidget> void removeButton(T button) {
		this.buttons.remove(button);
		this.children.remove(button);
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}
}
