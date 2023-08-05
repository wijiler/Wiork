#version 330 core

in vec4 color;
in vec2 uv;
out vec4 outColor;
out vec2 outuv;

void main() {
    outColor = color;
    outuv = uv;
}