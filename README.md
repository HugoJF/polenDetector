# [EXPERIMENTAL] Pollen detector

## Current status: DEPRECATED 

Small experimental GUI software developed to test a basic threshold technique to extract single pollen pictures from a single microscopic image. It heavily relies on BoofCV libraries to work and more information about thresholding in BoofCV can be found [here](https://boofcv.org/index.php?title=Example_Thresholding).

This software exposes 6 parameters in a user-friendly UI with automatic updates to the preview window, marking each detection with a precise contour (in red) and a bounding box (in green), each can be used further to improve detection.

## Used in this project
- Java 1.7
- BoofCV library
- Maven

## Usage
Execute with `polen_detector.jar <image_path>` where `<image_path>` is the full path of the image to be analyzed by the software.

## Future
I have no plans on updating this project (there are multiple things that could be improved) since it started just as a proof of concept on thresholding as a segmentation technique in pollen images that showed to be very imprecise.

![Sample image of the software running and results shown ](https://i.imgur.com/dhiWKQB.png)
