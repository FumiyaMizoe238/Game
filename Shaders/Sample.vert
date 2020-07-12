#version 460

layout (location = 0) in vec4 position;
layout (location = 1) in vec4 color;

out vec4 outColor;

void main()
{
	outColor = color;
	gl_Position = position;
}