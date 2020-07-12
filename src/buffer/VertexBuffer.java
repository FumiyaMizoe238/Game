package buffer;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.util.List;

import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

public class VertexBuffer extends BufferObject<Vector4f>
{
	public VertexBuffer()
	{
		super(GL_ARRAY_BUFFER);
	}

	@Override
	public boolean setData(List<Vector4f> data, int usage)
	{
		FloatBuffer buff = this.createFloatBuffer(data);
		if(buff == null)
		{
			return false;
		}

		this.bind();
		glBufferData(this.target, buff, usage);
		this.unbind();

		return true;
	}

	private FloatBuffer createFloatBuffer(List<Vector4f> data)
	{
		if(data.isEmpty())
		{
			return null;
		}

		int size = data.size();
		FloatBuffer buff = BufferUtils.createFloatBuffer(4 * size);
		for(int i = 0; i < size; i++)
		{
			if(data.get(i) != null)
			{
				buff.put(data.get(i).x).put(data.get(i).y).put(data.get(i).z).put(data.get(i).w);
			}
			else
			{
				buff.put(0).put(0).put(0).put(0);
			}
		}
		buff.flip();

		return buff;
	}
}
