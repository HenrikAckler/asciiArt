# asciiArt
Converts images into ascii art.

# Optimization
It appears the primary cause of delay is getAsciiArt() in asciiGenerator.java, more specifically writing to the string. With my first test image, I was writing to the string twice per pixel, averaging ~3700ms to compute. Without writing to the string at all, this drops to a measly 16ms, enough to render 60 times per second. So I very much need to fix this. 

Implementing a StringBuilder instead pushes the performance down to the ~30ms range, over 2 orders of magnitude faster. My next hurdle was reading the images. I was previously reading the RGB value into a new Color object, then splitting that into RGB and averaging that. By instead using bitwise operators, I was able to greatly optimize the program, now running at around 15-16ms per frame.