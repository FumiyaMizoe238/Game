package display;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window
{
	private int width;
	private int height;
	private String title;
	private int majorVersion;
	private int minorVersion;
	private long id;
	private Render render = null;

	public static Window create(int width, int height, String title, int majorVersion, int minorVersion)
	{
		return new Window(width, height, title, majorVersion, minorVersion);
	}

	public String getTitle()
	{
		return this.title;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public int getMajorVersion()
	{
		return this.majorVersion;
	}

	public int getMinorVersion()
	{
		return this.minorVersion;
	}

	public boolean shouldClose()
	{
		return glfwWindowShouldClose(this.id);
	}

	public void setRender(Render render)
	{
		this.render = render;
	}

	public void update()
	{
		if(this.render != null)
		{
			this.render.draw();
		}
		glfwSwapBuffers(this.id);
		glfwPollEvents();
	}

	public void shutdown()
	{
		if(this.render != null)
		{
			this.render.cleanup();
		}
		glfwFreeCallbacks(this.id);
		glfwDestroyWindow(this.id);
		glfwTerminate();
	}

	private Window(int width, int height, String title, int majorVersion, int minorVersion)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.id = 0;
		this.init();
	}

	private void init()
	{
		GLFWErrorCallback.createPrint(System.err).set();

		if(!glfwInit())
		{
			throw new IllegalStateException("Failed to initialize GLFW");
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, this.majorVersion);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, this.minorVersion);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

		this.id = glfwCreateWindow(this.width, this.height, title, NULL, NULL);
		if(this.id == NULL)
		{
			throw new RuntimeException("Failed to create the GLFW window");
		}

		glfwSetFramebufferSizeCallback(this.id, (window, width, height) ->
		{
			this.width = width;
			this.height = height;

		});

		glfwSetKeyCallback(id, (window, key, scancode, action, mods) ->{
			if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
			{
				glfwSetWindowShouldClose(window, true);
			}
		});

		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(
				id,
				(vidMode.width() - this.width) / 2,
				(vidMode.height() - this.height) /2
		);

		glfwMakeContextCurrent(id);

		glfwSwapInterval(1);

		glfwShowWindow(id);

		GL.createCapabilities();
	}
}
