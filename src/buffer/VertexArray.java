package buffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray
{
	private int id = 0;

	public VertexArray()
	{
		this.id = glGenVertexArrays();
	}

	//バッファオブジェクトが1つで済むようにのちに変更
	public void setAttrib(VertexBuffer buffer)
	{
		this.bind();
		{
			int stride = 4 * CustomVertex.SIZE;

			buffer.bind();
			{
				int offset = 0;
				for(int i = 0; i < CustomVertex.DIMENSION.length; i++)
				{
					glEnableVertexAttribArray(i);
					glVertexAttribPointer(i, CustomVertex.DIMENSION[i], GL_FLOAT, false, stride, offset);
					offset += CustomVertex.DIMENSION[i] * 4;
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
