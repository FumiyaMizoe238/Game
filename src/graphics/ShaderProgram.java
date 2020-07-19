package graphics;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static utils.Helper.*;

public class ShaderProgram
{
	private static class Shader
	{
		private int id = 0;

		public Shader()
		{

		}

		public boolean compile(String fileName, int shaderType)
		{
			String source = convertFileToString(fileName);
			if(source == null)
			{
				System.out.println("Failed to load : " + fileName);
				return false;
			}

			this.id = glCreateShader(shaderType);
			glShaderSource(this.id, source);
			glCompileShader(this.id);

			if(glGetShaderi(this.id, GL_COMPILE_STATUS) != GL_TRUE)
			{
				System.out.println(glGetShaderInfoLog(this.id));
				return false;
			}

			return true;
		}

		public int getID()
		{
			return this.id;
		}

		public void delete()
		{
			glDeleteShader(this.id);
		}
	}

	private int id = 0;
	private ShaderProgram.Shader vertShader = new ShaderProgram.Shader();
	private ShaderProgram.Shader fragShader = new ShaderProgram.Shader();

	public ShaderProgram()
	{

	}

	public boolean load(String vertProgramFile, String fragProgramFile)
	{
		if(!this.vertShader.compile(vertProgramFile, GL_VERTEX_SHADER)
				|| !this.fragShader.compile(fragProgramFile, GL_FRAGMENT_SHADER))
		{
			return false;
		}

		this.id = glCreateProgram();
		glAttachShader(this.id, this.vertShader.getID());
		glAttachShader(this.id, this.fragShader.getID());
		glLinkProgram(this.id);

		if(glGetProgrami(this.id, GL_LINK_STATUS) != GL_TRUE)
		{
			System.out.println(glGetShaderInfoLog(this.id));
			return false;
		}

		return true;
	}

	public void begin()
	{
		glUseProgram(this.id);
	}

	public void end()
	{
		glUseProgram(0);
	}

	public void unload()
	{
		this.vertShader.delete();
		this.fragShader.delete();
		glDeleteProgram(this.id);
	}

}
