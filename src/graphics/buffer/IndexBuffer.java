package graphics.buffer;

import static org.lwjgl.opengl.GL15.*;
import static utils.Helper.*;

import java.nio.IntBuffer;
import java.util.List;

public class IndexBuffer extends BufferObject<Integer>
{
	private int indexNum;

	public IndexBuffer()
	{
		super(GL_ELEMENT_ARRAY_BUFFER);
		this.indexNum = 0;
	}

	@Override
	public boolean setData(List<Integer> data, int usage)
	{
		if(data.isEmpty())
		{
			return false;
		}

		this.indexNum = data.size();

		IntBuffer buff = convertToBuffer(data.toArray(new Integer[data.size()]));
		this.bind();
		glBufferData(this.target, buff, usage);
		this.unbind();

		return false;
	}

	public int getIndexNum()
	{
		return this.indexNum;
	}
}
