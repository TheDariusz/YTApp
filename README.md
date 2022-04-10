# Media application
The application allows checking favorite YouTube videos of logged user.
It uses oauth google login process and YouTube api credentials to get user videos.
There will be possibility to connect to Spotify and synchronize items from these two services.


# Development
### Prerequisites
JDK 17, Docker, Thymeleaf, Spring-Boot

### Running locally in IntelliJ
1. Run java main function inside MediaApplication class
3. Open: [HOME](http://localhost:8080/home)

### Running on server
[https://ytapp-339922-crzmcsu6ea-ew.a.run.app/home](https://ytapp-339922-crzmcsu6ea-ew.a.run.app/home)

[//]: # (1. Run `./run-in-docker`)

[//]: # (2. Open: http://localhost:8080/home)

## Deployment
### Test environment
CI process with  GitHub Actions

### Production environment
Google Cloud Run on Google Cloud Platform

Deployment through CD process with GitHub Actions  

# Technical debt / TODO
- [ ] Spotify API handling
