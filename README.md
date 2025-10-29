Technologies used:

UI : Jetpack compose
Network call: Retrofit+Gson
Dependency Injection: Hilt
Navigation: Navigation library
Caching: Coil
Testing: Mockk+Junit4
Pattern used: MVI
Clean Architecture principles has been adhered to. 


Assumptions made:

1. The given api endpoint has a field for user profile photo. Coil library has been used for loading the image and displaying it in the details screen

2. As image loading is involved, ImageLoaderFactory from coil has been used to implement the caching strategy for optimal performance.
	
	For RAM caching- 10% of available memory space for this app has been configured as the upper limit
	For Disk caching- 3% of the remaining disk space on the device has been configured as the upper limit.

Video demo samples from both positive and negative(no internet) handling has been attached 

https://github.com/user-attachments/assets/6c023064-bede-45d7-b6a0-ce713d8c1166

https://github.com/user-attachments/assets/deb22854-1c5e-46ed-880b-4fefd24c4961


