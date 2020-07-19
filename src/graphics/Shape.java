package graphics;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.util.List;

import graphics.buffer.IndexBuffer;
import graphics.buffer.VertexArray;
import graphics.buffer.VertexBuffer;

public class Shape
{
	private VertexArray vao = new VertexArray();
	private VertexBuffer vbo = new VertexBuffer();
	private IndexBuffer ibo = new IndexBuffer();

	public VertexArray getVao()
	{
		return this.vao;
	}

	public VertexBuffer getVbo()
	{
		return this.vbo;
	}

	public IndexBuffer getIbo()
	{
		return this.ibo;
	}

	public void draw()
	{
		this.vao.bind();
		{
			this.ibo.bind();
			glDrawElements(GL_TRIANGLES, this.ibo.getIndexNum(), GL_UNSIGNED_INT, 0);
			this.ibo.unbind();
		}
		this.vao.unbind();
	}

	public boolean setVertexAndIndices(List<CustomVertex> vertices, List<Integer> indices)
	{
		return this.vbo.setData(vertices, GL_STATIC_DRAW) && this.ibo.setData(indices, GL_STATIC_DRAW);
	}
}
