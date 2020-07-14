package buffer;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;

public class VertexBuffer extends BufferObject<CustomVertex>
{
	public VertexBuffer()
	{
		super(GL_ARRAY_BUFFER);
	}

	@Override
	public boolean setData(List<CustomVertex> data, int usage)
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

	private FloatBuffer createFloatBuffer(List<CustomVertex> data)
	{
		if(data.isEmpty())
		{
			return null;
		}

		FloatBuffer buff = BufferUtils.createFloatBuffer(CustomVertex.SIZE * data.size());
		for(CustomVertex cv : data)
		{
			//positionの格納
			buff.put(cv.getPosition().x);
			buff.put(cv.getPosition().y);
			buff.put(cv.getPosition().z);

			//texCoordの格納
			buff.put(cv.getTexCoord().x);
			buff.put(cv.getTexCoord().y);

			//colorの格納
			buff.put(cv.getColor().x);
			buff.put(cv.getColor().y);
			buff.put(cv.getColor().z);
		}

		buff.flip();

		return buff;
	}
}
