package display;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import buffer.CustomVertex;
import buffer.VertexArray;
import buffer.VertexBuffer;
import graphics.ShaderProgram;

public class Render
{
	private VertexBuffer buff = new VertexBuffer();
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
				add(new CustomVertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f)));
				add(new CustomVertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f)));
				add(new CustomVertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)));

			}
		};
		this.buff.setData(vertices, GL_STATIC_DRAW);

		this.vao.setAttrib(this.buff);

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
		this.buff.delete();
		this.vao.delete();
		this.sp.unload();
	}
}
