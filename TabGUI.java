package blackcat.modules.render;

import java.util.List;

import org.lwjgl.input.Keyboard;

import blackcat.Client;
import blackcat.events.Event;
import blackcat.events.listeners.EventKey;
import blackcat.events.listeners.EventRenderGUI;
import blackcat.modules.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;
public class TabGUI extends Module{
	
	public int currentTab, moduleIndex;
	public boolean expanded;

	public TabGUI() {
		super("TabGUI", Keyboard.KEY_NONE, Category.RENDER);
		toggled = true;
	}
	public void onEvent(Event e) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fr = mc.fontRendererObj;
		if(e instanceof EventRenderGUI) {
			Gui.drawRect(5, 31, 80, 30 + Module.Category.values().length * 16 + 2, 0x90000000);
			Gui.drawRect(7, 33 + currentTab * 16, 7 + 71, 33 + currentTab * 16 + 13, 0xff0090ff);
			
			int count = 0;
			for(Category c : Module.Category.values()) {
				fr.drawString(c.name, 11, 36 + count*16, -1);
				
				count++;
			}
			
     		List<Module> modules = Client.getModulesByCategory(Module.Category.values()[currentTab]);
			if(expanded) {
				
				if(modules.size() == 0) {
					return;
				}
				
				
				
			Gui.drawRect(5 + 75, 31, 80 + 75, 30 + modules.size()* 16 + 2, 0x90000000);
			Gui.drawRect(7 + 75, 33 + moduleIndex * 16, 7 + 71 + 75, 33 + moduleIndex * 16 + 13, 0xff0090ff);
			
			count = 0;
			for(Module m : modules) {
				fr.drawString(m.name, 11 + 75, 36 + count*16, -1);
				
				count++;
			}
			}
		}
		if(e instanceof EventKey) {
			
			
		}
			int code = ((EventKey)e).code;
			
			List<Module> modules = Client.getModulesByCategory(Module.Category.values()[currentTab]);
			if(code == Keyboard.KEY_UP) {
				if(expanded) {
					if(moduleIndex <= 0) {
						moduleIndex = modules.size() - 1;
				}else
					    moduleIndex--;
					}else {
						
						if(currentTab <= 0) {
							currentTab = Module.Category.values().length - 1;
				    }else 
				    	currentTab--;
				}
				
			}
			if(code == Keyboard.KEY_DOWN) {
				if(expanded) {
					if(moduleIndex >= modules.size()) {
						moduleIndex = 0;
					}
					    moduleIndex++;
					}else {
						
						if(currentTab >= Module.Category.values().length -1) {
							currentTab = 0;
				    }else 
				    	currentTab++;
				}
			}
			
			if(code == Keyboard.KEY_RIGHT) {
				if(expanded) {
				modules.get(moduleIndex).toggle();
			}else {
				expanded = true;
			}
			
			if(code == Keyboard.KEY_LEFT) {
				expanded = false;
			}
			
		}
	}
}

	

