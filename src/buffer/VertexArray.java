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
	public void setAttrib(int attribPos, VertexBuffer buffer)
	{
		this.bind();
		{
			buffer.bind();
			{
				glEnableVertexAttribArray(attribPos);
				glVertexAttribPointer(attribPos, 4, GL_FLOAT, false, 0, 0);
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
