package net.themoviea.frozeniimod.client.gui.screen.ingame.elsaCommunication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.themoviea.frozeniimod.client.gui.screen.Frozen2Screen;
import net.themoviea.frozeniimod.entity.passive.ElsaEntity;
import net.themoviea.frozeniimod.screen.ElsaCommunicationScreenHandler;

public class ElsaCommunicationScreen extends Frozen2Screen<ScreenHandler>
{
	private List<Text> DIALOGUES = new ArrayList<>();
	private ButtonWidget button1;
	private ButtonWidget button2;
	private int value;

	public ElsaCommunicationScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}
	
	private boolean addDialogue(TranslatableText... texts) {
		List<Text> list = Arrays.asList(texts);
		return DIALOGUES.addAll(list);
	}
	
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		super.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
	}
	
	@Override
	protected void init() {
		super.init();
		
		addDialogue(new TranslatableText("Hello there player!"));
		
		this.button1 = (ButtonWidget)this.addButton(new ButtonWidget(width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, new TranslatableText("menu.stuff"), (buttonWidget) -> {
			removeButton(button2);
			
		}));
		
		this.button2 = (ButtonWidget)this.addButton(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, new TranslatableText("menu.quit"), (buttonWidget) -> {
			removeButton(button1);
		}));
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	@Override
	public void onClose() {
		super.onClose();
	}
	
	@Override
	protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
		
	}
	
	static {
	}
}
