package main;

import display.Render;
import display.Window;

public class Main
{
    public static void main(String[] args)
    {
        Window w = Window.create(1024, 768, "Window", 4, 6);
        w.setRender(new Render());
        while(!w.shouldClose())
        {
        	w.update();
        }
        w.shutdown();
    }
}
