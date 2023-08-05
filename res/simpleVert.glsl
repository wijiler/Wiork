#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 a_color;
layout(location = 2) in vec2 a_uv;

out vec3 color;
out vec2 uv;

void main() {
    gl_Position = vec4(position, 1.0);
    color = a_color;
    uv = a_uv;
}