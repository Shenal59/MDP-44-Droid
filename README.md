Android application for tracking and sending of instructions to the robot

Requirements and background of project:
-We are provided with a robot which is coded by our team to follow basic instructions given such as F/B/L/R. 
-These basic instructions are given through a raspberry pi which acts as a middleman between the robot and our mobile application. 
-On the ground, there will be a grid marked out, with objects placed containing images out of a predetermined set for the robot to navigate towards and identify with the help of its mounted camera and our image recognition algorithm.
-The mobile application is used to place the objects in its virtual grid and an algorithm coded by us will execute the path of the robot.
-The robot will identify each image and send the result back to the application and each obstacle will be marked.

Features:
1. Place obstacles on the grid provided, representative of the grid on the ground
2. Orientate and label obstacles
3. Send and receive messages through the Raspberry Pi to update the interface with the images identified by the robot
