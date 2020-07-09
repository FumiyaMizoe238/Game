package main;

import display.Window;
import graphics.Shader;

public class Main
{
    public static void main(String[] args)
    {
        Window w = Window.create(1024, 768, "Window", 4, 6);
        Shader s = new Shader();
        s.readShaderFile("C:\\Users\\fumiya.mizoe\\Desktop\\SelfDelopment\\Game\\src\\test.txt", "");
        while(!w.shouldClose())
        {
        	w.update();
        }
    }
}
