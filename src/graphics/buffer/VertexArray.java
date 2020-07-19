package graphics.buffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import graphics.CustomVertex;

public class VertexArray
{
	private int id = 0;


	public VertexArray()
	{
		this.id = glGenVertexArrays();
	}

	public void setAttrib(VertexBuffer buffer)
	{
		this.bind();
		{
			buffer.bind();
			{
				int offset = 0;
				for(CustomVertex.VertexAttrib va : CustomVertex.VertexAttrib.values())
				{
					int attrib = CustomVertex.ATTRIB_MAP.get(va);
					int dim = CustomVertex.DIMENSION_MAP.get(va);
					glEnableVertexAttribArray(attrib);
					glVertexAttribPointer(attrib, dim, GL_FLOAT, false, CustomVertex.STRIDE, offset);
					offset += dim * CustomVertex.FLOAT_SIZE;
				}
			}
			buffer.unbind();
		}
		this.unbind();
	}

	public void bind()
	{
		glBindVertexArray(this.id);
	}

	public void unbind()
	{
		glBindVertexArray(0);
	}

	public void delete()
	{
		glDeleteVertexArrays(this.id);
	}
}
