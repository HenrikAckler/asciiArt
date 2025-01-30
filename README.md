# asciiArt
Converts images into ascii art.

# Optimization
It appears the primary cause of delay is getAsciiArt() in asciiGenerator.java, more specifically writing to the string. With my first test image, I was writing to the string twice per pixel, averaging ~3700ms to compute. Without writing to the string at all, this drops to a measly 16ms, enough to render 60 times per second. So I very much need to fix this. 

Implementing a StringBuilder instead pushes the performance down to the ~30ms range, over 2 orders of magnitude faster. 