package graphics.buffer;

import static org.lwjgl.opengl.GL15.*;

import java.util.List;

public abstract class BufferObject<T>
{
	protected int id;
	protected int target;

	public BufferObject(int target)
	{
		this.id = glGenBuffers();
		this.target = target;
	}

	public BufferObject()
	{
		this(GL_ARRAY_BUFFER);
	}

	public void bind()
	{
		glBindBuffer(target, id);
	}

	public void unbind()
	{
		glBindBuffer(target, 0);
	}

	public int getID()
	{
		return this.id;
	}

	public int getTarget()
	{
		return this.target;
	}

	public String getTargetStr()
	{
		switch(this.target)
		{
		case GL_ARRAY_BUFFER:
			return "GL_ARRAY_BUFFER";

		case GL_ELEMENT_ARRAY_BUFFER:
			return "GL_ELEMENR_ARRAY_BUFFER";

		default:
			return "UNKNOWN_TYPE_BUFFER";
		}
	}

	public void delete()
	{
		glDeleteBuffers(this.id);
	}

	public abstract boolean setData(List<T> data, int usage);
}
