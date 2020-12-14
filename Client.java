package blackcat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import blackcat.events.Event;
import blackcat.events.listeners.EventKey;
import blackcat.modules.Module;
import blackcat.modules.Module.Category;
import blackcat.modules.movement.*;
import blackcat.modules.player.*;
import blackcat.modules.render.*;
import blackcat.ui.HUD;

public class Client {
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	
	
	public static void StartUp(){
		System.out.println("Starting BlackCat 1.8.8 blackcat.gg");
		Display.setTitle("BlackCat Client");
		
		Client.modules.add(new Fly());
		Client.modules.add(new Sprint());
		Client.modules.add(new FullBright());
		Client.modules.add(new NoFall());
		Client.modules.add(new TabGUI());
	}
	

	public static void onEvent(Event e) {
		for(Module m : Client.modules) {
				if(m.toggled == null) {
					m.toggled = false;
				}
			if(!m.toggled)
				continue;
			m.onEvent(e);;
		}
	}
	
	
	public static void keyPress(int key) {
		Client.onEvent(new EventKey(key));
		for(Module m : Client.modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}
	
	public static List<Module> getModulesByCategory(Category c){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Client.modules) {
			if(m.category == c) {
				modules.add(m);
			}
		}
		return modules;
	}
	
	
	
	
	
	
	
	
	
}
