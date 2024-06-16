# Smart home frontend
## Launch the project
Warning: the frontend project can be launch independently of the other packages, but the integrity of features may be compromised. 
### Launching from the root project
You can run this project from the [root project](https://github.com/smart-home-controller/smart-home) by following the instructions laid out in its `README.md` file and choosing the `frontend` profile when building the docker image.
### Cloning project
#### With `bash` installed
- Clone this project: `git clone https://github.com/smart-home-controller/front.git`.
- Run the [launch.sh](launch.sh) file located at the project's root.
#### Without `bash`
- Clone this project: `git clone https://github.com/smart-home-controller/front.git`.
- Build the docker image `docker build -t frontend -f frontend.Dockerfile .`.
- Run the docker container with the image previously built `docker run -it frontend`.
