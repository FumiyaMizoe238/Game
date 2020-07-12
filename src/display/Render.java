package display;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector4f;

import buffer.VertexArray;
import buffer.VertexBuffer;
import graphics.ShaderProgram;

public class Render
{
	private VertexBuffer vertBuf = new VertexBuffer();
	private VertexBuffer colorBuf = new VertexBuffer();
	private VertexArray vao = new VertexArray();
	private ShaderProgram sp = new ShaderProgram();
	private boolean success = true;

	public Render()
	{
		List<Vector4f> vertices = new ArrayList<Vector4f>()
		{
			{
				add(new Vector4f(-0.5f, -0.5f, 0.0f, 1.0f));
				add(new Vector4f(0.5f, -0.5f, 0.0f, 1.0f));
				add(new Vector4f(0.5f, 0.5f, 0.0f, 1.0f));
				add(new Vector4f(0.5f, 0.5f, 0.0f, 1.0f));
				add(new Vector4f(-0.5f, 0.5f, 0.0f, 1.0f));
				add(new Vector4f(-0.5f, -0.5f, 0.0f, 1.0f));
			}
		};
		System.out.println(this.vertBuf.setData(vertices, GL_STATIC_DRAW));

		List<Vector4f> colors = new ArrayList<Vector4f>()
		{
			{
				add(new Vector4f(1.0f, 0.0f, 0.0f, 1.0f));
				add(new Vector4f(0.0f, 1.0f, 0.0f, 1.0f));
				add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
				add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
				add(new Vector4f(1.0f, 1.0f, 0.0f, 1.0f));
				add(new Vector4f(1.0f, 0.0f, 0.0f, 1.0f));
			}
		};
		System.out.println(this.colorBuf.setData(colors, GL_STATIC_DRAW));

		this.vao.setAttrib(0, this.vertBuf);
		this.vao.setAttrib(1, this.colorBuf);

		if(!this.sp.load("Shaders/Sample.vert", "Shaders/Sample.frag"))
		{
			this.success = false;
			System.out.println("シェーダーの読み込みに失敗しました");
		}
	}

	public void draw()
	{
		if(!this.success)
		{
			return;
		}

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		this.vao.bind();
		{
			this.sp.begin();
			{
				glDrawArrays(GL_TRIANGLES, 0, 6);
			}
			this.sp.end();
		}
		this.vao.unbind();
	}

	public void cleanup()
	{
		this.vertBuf.delete();
		this.colorBuf.delete();
		this.vao.delete();
		this.sp.unload();
	}
}
