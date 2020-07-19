package display;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import graphics.CustomVertex;
import graphics.ShaderProgram;
import graphics.buffer.IndexBuffer;
import graphics.buffer.VertexArray;
import graphics.buffer.VertexBuffer;

public class Render
{
	private VertexBuffer vBuff = new VertexBuffer();
	private IndexBuffer iBuff = new IndexBuffer();
	private VertexArray vao = new VertexArray();
	private ShaderProgram sp = new ShaderProgram();
	private boolean success = true;

	public Render()
	{
		List<CustomVertex> vertices = new ArrayList<CustomVertex>()
		{
			{
				add(new CustomVertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)));
				add(new CustomVertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f)));
				add(new CustomVertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f)));
				add(new CustomVertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f)));
			}
		};
		this.vBuff.setData(vertices, GL_STATIC_DRAW);

		List<Integer> indices = new ArrayList<Integer>()
		{
			{
				add(0);
				add(1);
				add(2);
				add(2);
				add(3);
				add(0);
			}
		};
		this.iBuff.setData(indices, GL_STATIC_DRAW);

		this.vao.setAttrib(this.vBuff);

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
				this.iBuff.bind();
				glDrawElements(GL_TRIANGLES, this.iBuff.getIndexNum(), GL_UNSIGNED_INT, 0);
				this.iBuff.unbind();
			}
			this.sp.end();
		}
		this.vao.unbind();
	}

	public void cleanup()
	{
		this.vBuff.delete();
		this.iBuff.delete();
		this.vao.delete();
		this.sp.unload();
	}
}
