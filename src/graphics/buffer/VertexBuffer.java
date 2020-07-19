package graphics.buffer;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.util.List;

import graphics.CustomVertex;
import utils.Helper;

public class VertexBuffer extends BufferObject<CustomVertex>
{
	public VertexBuffer()
	{
		super(GL_ARRAY_BUFFER);
	}

	@Override
	public boolean setData(List<CustomVertex> data, int usage)
	{
		if(data.isEmpty())
		{
			return false;
		}

		FloatBuffer buff = Helper.convertToBuffer(data.toArray(new CustomVertex[data.size()]));
		this.bind();
		glBufferData(this.target, buff, usage);
		this.unbind();

		return true;
	}
}